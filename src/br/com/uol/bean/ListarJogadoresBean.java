package br.com.uol.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uol.model.Jogador;
import br.com.uol.service.JogadorService;

@Named
@ViewScoped
public class ListarJogadoresBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Jogador> jogadores;
	
	@Inject
	private JogadorService jogadorService;
	
	@PostConstruct
	public void init() {
		jogadores = jogadorService.buscarTodosOsJogadores();
	}
	
	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public String removerJogadorLista(Jogador jogador) {
		jogadores.remove(jogador);
		return null;
	}
}
