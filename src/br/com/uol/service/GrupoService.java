package br.com.uol.service;

import javax.inject.Inject;

import br.com.uol.dao.GrupoDAO;
import br.com.uol.model.Grupo;

public class GrupoService extends Service{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoDAO grupoDAO;
	
	public Grupo buscarPorNome(String nome) {
		return grupoDAO.buscarPorNome(nome);
	}
	
	public void salvar(Grupo grupo) {
		beginTransation();
		grupoDAO.persist(grupo);
		commitTransaction();
	}
	
	public boolean existePorNome(String nome) {
		return grupoDAO.existePorNome(nome);
	}
}
