package br.com.uol.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class NavegacaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String homePage = "home.xhtml?faces-redirect=true";
	private String paginaAtual = homePage;
	private final String cadastroJogadorPage = "cadastro-jogador.xhtml?faces-redirect=true";
	private final String listarJogadoresPage = "listar-jogadores.xhtml?faces-redirect=true";
			
	private Map<String, String> breadCrumbItem = new LinkedHashMap<>();
	
	public String irParaHome() {
		paginaAtual = homePage;
		
		preencherBreadCrumb();
		return homePage;
	}
	
	public String irParaCadastroJogador() {
		paginaAtual = cadastroJogadorPage;
		
		preencherBreadCrumb();
		return cadastroJogadorPage;
	}
	
	public String irParaListarJogadores() {
		paginaAtual = listarJogadoresPage;
		
		preencherBreadCrumb();
		return listarJogadoresPage;
	}

	public String navegacaoPeloBreadCrumb(String url) {
		
		switch(url) {
		
			case homePage:
				return irParaHome();
				
			case cadastroJogadorPage:
				return irParaCadastroJogador();
			
			case listarJogadoresPage:
				return irParaListarJogadores();
		}
		
		return null;
	}
	public void preencherBreadCrumb() {
		breadCrumbItem.clear();
		
		breadCrumbItem.put("Home", homePage);
		
		String key = null;
		
		switch(paginaAtual) {
		
			case cadastroJogadorPage:
				key = "Cadastrar Jogador";
				break;
			
			case listarJogadoresPage:
				key = "Listar Jogadores";
				break;
		}
		
		breadCrumbItem.put(key, paginaAtual);
	}
	
	public boolean renderizarBreadCrumb() {
		return !paginaAtual.equals(homePage);
	}
	public Map<String, String> getBreadCrumbItem() {
		return breadCrumbItem;
	}

	public void setBreadCrumbItem(Map<String, String> breadCrumbItem) {
		this.breadCrumbItem = breadCrumbItem;
	}

	public String getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(String paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
}
