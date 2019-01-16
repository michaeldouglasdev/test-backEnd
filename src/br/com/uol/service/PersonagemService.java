package br.com.uol.service;

import java.util.List;

import javax.inject.Inject;

import br.com.uol.dao.PersonagemDAO;
import br.com.uol.model.Grupo;
import br.com.uol.model.Personagem;

public class PersonagemService extends Service{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PersonagemDAO personagemDAO;
	
	public void salvar(Personagem personagem) {
		beginTransation();
		personagemDAO.persist(personagem);
		commitTransaction();
	}
	
	public Personagem buscarPersonagemPeloNome(String nome) {
		return personagemDAO.buscarPersonagemPeloNome(nome);
	}
	
	public List<Personagem> buscarTodosPersonagensPeloGrupo(Grupo grupo) {
		return personagemDAO.buscarTodosPersonagensPeloGrupo(grupo);
	}
}
