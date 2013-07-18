package aaarrgh.services;

import aaarrgh.model.Persona;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.PersonaDao;

public class LoginService {

	public boolean authenticate(String username, String pass) throws PersistenceException {
		PersonaDao dao = DaoFactory.getPersonaDao();
		Persona a = dao.findByNameAndPass(username, pass);
		return (a==null)?false:a.getPass().equals(pass);
	}
	
	public void logout(){
		
	}

}