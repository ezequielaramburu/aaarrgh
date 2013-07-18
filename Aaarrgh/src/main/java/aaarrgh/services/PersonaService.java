package aaarrgh.services;

import java.util.List;

import aaarrgh.model.Persona;
import aaarrgh.model.PersonaSeguidor;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.PersonaDao;
import aaarrgh.persistence.PersonaSeguidorDao;

public class PersonaService {
	
	/* Retorna los datos de la persona logueada o pasada por parametro (PERFIL) */
	public Persona obtenerPersona(Integer idPersona) throws PersistenceException{
		
		PersonaDao dao = DaoFactory.getPersonaDao();
		Persona p = dao.findById(idPersona);
		return p;
		
	}

	/* Lista de personas que sigue la persona logueada o pasada por parametro */
	public List<Persona> personasQueSigo(Persona persona) throws PersistenceException{
		PersonaSeguidorDao dao = DaoFactory.getPersonaSeguidorDao();
		List<Persona> personasQueSigo = dao.findPersonasBySeguidor(persona.getId());
		return personasQueSigo;
		
	}
	
	/* Lista de personas que siguen a la persona logueada o pasada por parametro */
	public List<Persona> personasQueMeSiguen(Persona persona) throws PersistenceException{
		PersonaSeguidorDao dao = DaoFactory.getPersonaSeguidorDao();
		List<Persona> personasQueMeSiguen = dao.findPersonasByPersona(persona.getId());
		return personasQueMeSiguen;
		
	}
	
	/* Registra que una persona sigue a otra, genera una instancia de PersonaSeguidor y la persiste en la BD*/
	public void seguirA(Persona persona, Persona seguidor) throws PersistenceException{
		PersonaSeguidor ps = new PersonaSeguidor();
		ps.setIdPersona(persona.getId());
		ps.setIdSeguidor(seguidor.getId());
		PersonaSeguidorDao dao = DaoFactory.getPersonaSeguidorDao();
		dao.insert(ps);
	}
	
	/* Elimina la relacion entre una persona y su seguidor*/
	public void noSeguirA(Persona persona, Persona seguidor) throws PersistenceException{
		PersonaSeguidorDao dao = DaoFactory.getPersonaSeguidorDao();
		dao.deletePersonaSeguidor(persona, seguidor);
	}	
	

}
