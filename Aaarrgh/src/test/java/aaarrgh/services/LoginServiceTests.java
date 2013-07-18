package aaarrgh.services;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import aaarrgh.persistence.PersistenceException;

public class LoginServiceTests {

	LoginService service = new LoginService();

	@Test
	public void testThatAuthenticates() throws SQLException, PersistenceException {
		String nombre = "jack";
		String pass = "jack";
		Assert.assertTrue("username equals password must authenticate",
				service.authenticate(nombre, pass));
	}
	
	@Test
	public void testThatRefusesAuthentication() throws SQLException, PersistenceException {
		String nombre = "jack";
		String pass = "sparrow";
		Assert.assertFalse("username not equals password must refuse authentication",
				service.authenticate(nombre, pass));
	}
	
}
