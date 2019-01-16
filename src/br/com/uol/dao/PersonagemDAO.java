package br.com.uol.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.uol.model.Grupo;
import br.com.uol.model.Personagem;

public class PersonagemDAO extends DAO{

	private static final long serialVersionUID = 1L;

	public List<Personagem> buscarTodosPersonagensPeloGrupo(Grupo grupo) {
		
		TypedQuery<Personagem> query = createQuery("SELECT p FROM Personagem p WHERE p.grupo.id = :idGrupo", Personagem.class);
		
		query.setParameter("idGrupo", grupo.getId());
		
		return query.getResultList();
	}

	public Personagem buscarPersonagemPeloNome(String nome) {

		TypedQuery<Personagem> query = createQuery("SELECT p FROM Personagem p WHERE p.nome = :nome", Personagem.class);
		
		query.setParameter("nome", nome);
		
		return query.getSingleResult();
	}
}
