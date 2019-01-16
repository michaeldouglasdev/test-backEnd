package br.com.uol.dao;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.uol.model.Grupo;

public class GrupoDAO extends DAO{
	
	private static final long serialVersionUID = 1L;

	public Grupo buscarPorNome(String nome) {
		
		TypedQuery<Grupo> query = createQuery("SELECT g FROM Grupo g WHERE g.nome = :nome", Grupo.class);
		query.setParameter("nome", nome);
		
		if(!query.getResultList().isEmpty())
		{
			return query.getResultList().get(0);
		}
		else
		{
			return null;
		}
	}
	
	public boolean existePorNome(String nome) {
	    Query query = createQuery("SELECT COUNT(g) FROM Grupo g WHERE g.nome = :nome");
	    query.setParameter("nome", nome);
	    
	    Long count = (Long) query.getSingleResult();
	    
	    return !count.equals(0L);
	}
}
