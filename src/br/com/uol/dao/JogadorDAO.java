package br.com.uol.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.uol.model.Jogador;

public class JogadorDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public List<Jogador> buscarTodosOsJogadores() {
		TypedQuery<Jogador> query = createQuery("SELECT j FROM Jogador j", Jogador.class);
		
		return query.getResultList(); 
	}
}
