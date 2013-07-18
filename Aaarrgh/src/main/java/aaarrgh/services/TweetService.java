package aaarrgh.services;

import java.util.List;

import aaarrgh.model.Persona;
import aaarrgh.model.Tweet;
import aaarrgh.persistence.DaoFactory;
import aaarrgh.persistence.PersistenceException;
import aaarrgh.persistence.TweetDao;

public class TweetService {
	
	/* Tweets en donde persona es igual a la persona logueada o pasada por parametro */
	public List<Tweet> misTweets(Persona persona) throws PersistenceException{
		TweetDao dao = DaoFactory.getTweetDao();
		List<Tweet> misTweets = dao.findAllFromUser(persona.getId());
		return (misTweets==null)?null:misTweets;		
	}
	
	/* Tweets de las personas que sigue la persona logueada o pasada por parametro */
	public List<Tweet> tweetsDePersonasQueSigo(Persona persona) throws PersistenceException{
		TweetDao dao = DaoFactory.getTweetDao();
		List<Tweet> tweets = dao.findTweetsPersonasQueSigo(persona.getId());
		return tweets;
			
	}

}
