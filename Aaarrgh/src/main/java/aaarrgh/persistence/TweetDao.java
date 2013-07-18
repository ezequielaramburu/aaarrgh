package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Tweet;

public interface TweetDao {
	
	  public void insertTweet(Tweet tweet) throws PersistenceException;
	  
	  public void deleteTweet(Tweet tweet) throws PersistenceException;
	  
	  public List<Tweet> findAll() throws PersistenceException;/*Como traer un tweet de una persona,relacionando el id de la persona con la del tweet*/

	  public void updateTweet(Tweet tweet) throws PersistenceException;

	  public Tweet findById(Integer id) throws PersistenceException;
	  
	  public List<Tweet> findAllFromUser(Integer idPersona) throws PersistenceException;
	  public List<Tweet> findTweetsPersonasQueSigo(Integer idPersona) throws PersistenceException;
}
