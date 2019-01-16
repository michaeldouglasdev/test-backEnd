package br.com.uol.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uol.model.Jogador;
import br.com.uol.service.JogadorService;

@Named
@ViewScoped
public class AtualizarJogadorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Jogador jogador;

	@Inject
	private JogadorService jogadorService;
	
	public void atualizarRegistro() {
		jogadorService.atualizar(jogador);
	}
	
	public void excluir() {
		jogadorService.excluir(jogador);
	}
	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
}
