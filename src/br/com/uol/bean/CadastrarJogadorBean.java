package br.com.uol.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.uol.model.Grupo;
import br.com.uol.model.Jogador;
import br.com.uol.model.Personagem;
import br.com.uol.service.GrupoService;
import br.com.uol.service.JogadorService;
import br.com.uol.service.PersonagemService;
import br.com.uol.webservice.ConsomeWebService;

@Named
@ViewScoped
public class CadastrarJogadorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Jogador jogador;
	private List<Personagem> personagens = new ArrayList<>();
	private String grupoSelecionado;
	private Grupo grupo;
	
	private List<Personagem> personagensIndisponiveis;
	
	@Inject
	private GrupoService grupoService;
	
	@Inject
	private JogadorService jogadorService;
	
	@Inject
	private PersonagemService personagemService;
	
	@PostConstruct
	public void init() {
		jogador = new Jogador();
	}
	
	public void consumirVingadores() {
        	
		String urlPersonagens = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
		
        JSONObject json = new JSONObject(ConsomeWebService.getResponseStringFormat(urlPersonagens));
        JSONArray nomeArray = json.names();
        JSONArray valArray = json.toJSONArray(nomeArray).getJSONArray(0);
        
        grupo = grupoService.buscarPorNome(this.grupoSelecionado);
        
        for(int i = 0; i < valArray.length(); i++) {
            personagens.add(new Personagem(valArray.getJSONObject(i).get("codinome").toString(), grupo));
        }
	}
	
	public void consumirLigaDaJustica() {
		try {
			String urlPersonagens = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";
	       

	        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder;
			try {
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(new InputSource(new StringReader(ConsomeWebService.getResponseStringFormat(urlPersonagens))));
				
				NodeList codinomes =  document.getElementsByTagName("codinome");
				
	            grupo = grupoService.buscarPorNome(this.grupoSelecionado);
	            
				for(int i = 0; i < codinomes.getLength(); i++) {
					Element element = (Element)codinomes.item(i);
	                personagens.add(new Personagem(element.getTextContent(), grupo));
				}
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar() {
		
		if(!grupoService.existePorNome(grupoSelecionado)) {
			
			grupo = new Grupo();
			grupo.setNome(grupoSelecionado);
			grupoService.salvar(grupo);
		}
		
		if(grupoSelecionado.equals("Os Vingadores")) {
			consumirVingadores();
			
		}
		else {
			consumirLigaDaJustica();
		}

		personagensIndisponiveis = personagemService.buscarTodosPersonagensPeloGrupo(grupo);

		personagensIndisponiveis.forEach(indisponiveis -> {
			personagens.removeIf(e -> e.getNome().equals(indisponiveis.getNome()));
		});

		
		if(!personagens.isEmpty()) {
			int index = new Random().nextInt(personagens.size());
			
			Personagem personagem = personagens.get(index);
			jogador.setPersonagem(personagem);
			personagemService.salvar(personagem);
			personagem = personagemService.buscarPersonagemPeloNome(personagem.getNome());
			
			jogadorService.salvar(jogador);
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não há personagens disponíveis para o grupo selecionado!"));
			return;
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "O Jogador " + jogador.getNome() + " foi cadastrado <br>Codinome: " + jogador.getPersonagem().getNome()));
		limpar();

	}

	public void limpar() {
		jogador = new Jogador();
		personagens = new ArrayList<>();
		grupoSelecionado = null;
		grupo = null;
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public String getGrupoSelecionado() {
		return grupoSelecionado;
	}
	public void setGrupoSelecionado(String grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}
}
