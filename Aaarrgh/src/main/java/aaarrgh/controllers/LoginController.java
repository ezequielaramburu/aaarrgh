package aaarrgh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	LoginService loginService = new LoginService();

	@RequestMapping("/auth")
	public ModelAndView authenticate(
			@RequestParam("nombre") String usuario,
			@RequestParam("pass") String pass) throws PersistenceException {

		ModelAndView dispatch = null;

		if (loginService.authenticate(usuario, pass)) {
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + usuario); 
		} else {
			dispatch = new ModelAndView("../../index", "message", "Ingreso incorrecto");
		}

		return dispatch;

	}

	public ModelAndView logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
