package br.com.uol.dao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@RequestScoped
public abstract class DAO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public <T> T find(Class<T> clazz, Object key) {
		return  em.find(clazz, key);
	}
	
	public <T> void persist(T entity) {
		em.persist(entity);
	}

	public <T> void remove(T entity) {
		em.remove(entity);
	}
	
	public <T> void merge(T entity) {
		em.merge(entity);
	}
		
	public Query createQuery(String query) {
		return em.createQuery(query);
	}

	public <T> TypedQuery<T> createQuery(String query, Class<T> clazz) {
		return em.createQuery(query, clazz);
	}
	
	public Query createNativeQuery(String query) {
		return em.createNativeQuery(query);
	}
	
	public <T> Query createNativeQuery(String query, Class<T> clazz) {
		return em.createNativeQuery(query, clazz);
	}
	
}