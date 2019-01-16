package br.com.uol.service;

import java.util.List;

import javax.inject.Inject;

import br.com.uol.dao.JogadorDAO;
import br.com.uol.model.Jogador;

public class JogadorService extends Service {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private JogadorDAO jogadorDAO;
	
	public void salvar(Jogador jogador) {
		beginTransation();
		jogadorDAO.persist(jogador);
		commitTransaction();
	}
	
	public List<Jogador> buscarTodosOsJogadores() {
		return jogadorDAO.buscarTodosOsJogadores();
	}
	
	public void atualizar(Jogador jogador) {
		beginTransation();
		jogadorDAO.merge(jogador);
		commitTransaction();
	}
	
	public void excluir(Jogador jogador) {
		beginTransation();
		jogadorDAO.remove(buscarJogadorPeloId(jogador.getId()));
		commitTransaction();
	}
	
	public Jogador buscarJogadorPeloId(Long id) {
		return jogadorDAO.find(Jogador.class, id);
	}
}
