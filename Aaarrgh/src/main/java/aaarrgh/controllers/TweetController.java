package aaarrgh.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import aaarrgh.model.Persona;
import aaarrgh.model.Tweet;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.services.TweetService;

@Controller
@RequestMapping("/tweet")
public class TweetController {

	TweetService tweetService = new TweetService();

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws PersistenceException {

		ModelAndView dispatch = null;

		Persona user = (Persona) request.getSession().getAttribute("user");
		List<Tweet> lista = tweetService.tweetsDePersonasQueSigo(user);

		Map<String, List<Tweet>> mapa = new HashMap<String, List<Tweet>>();
		mapa.put("listado", lista);
		return new ModelAndView("tweet/list", mapa);

	}

	public ModelAndView logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
