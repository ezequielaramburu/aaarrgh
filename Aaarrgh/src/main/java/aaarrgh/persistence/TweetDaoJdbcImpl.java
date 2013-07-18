package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Tweet;

public class TweetDaoJdbcImpl implements TweetDao{

	private static TweetDao instance = new TweetDaoJdbcImpl();

	public static TweetDao getInstance() {
		return instance;
	}
	
	/*Inserta el tweet que escribamos en la base*/
	@Override
	public void insertTweet(Tweet tweet) throws PersistenceException {
		// TODO Auto-generated method stub
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into tweet (id, texto, Idusuario) values (?, ?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, tweet.getIdTweet());
			statement.setString(2, tweet.getTexto());
			statement.setInt(3, tweet.getIdPersona());
			
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}


	@Override
	public void deleteTweet(Tweet tweet) throws PersistenceException {
		// TODO Auto-generated method stub
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from tweet where id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, tweet.getIdTweet());
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}
		
	@Override
	public void updateTweet(Tweet tweet) throws PersistenceException {
		try {
			String query = "update tweet set id = ?, texto = ?, idusuario = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, tweet.getIdTweet());
			statement.setString(2, tweet.getTexto());
			statement.setInt(3, tweet.getIdPersona());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}
	
	@Override
	public List<Tweet> findAll() throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			String query = "select * from tweet";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

	@Override
	public Tweet findById(Integer id) throws PersistenceException {
		if (id == null) {
			throw new IllegalArgumentException(
					"El id del mensaje no debe ser nulo");
		}
		Tweet tweet = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from tweet where id = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				tweet = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return tweet;
	}

	private Tweet convertOne(ResultSet resultSet) throws SQLException {
		Tweet retorno = new Tweet();

		retorno.setIdTweet(resultSet.getInt("idTweet"));
		retorno.setTexto(resultSet.getString("texto"));
		retorno.setIdPersona(resultSet.getInt("idPersona"));

		return retorno;
	}
	@Override
	public List<Tweet> findAllFromUser(Integer idPersona)
			throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			String query = "select * from tweet where idPersona = " + idPersona ;
			PreparedStatement statement = ConnectionProvider.getInstance().getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;

	}
	
	@Override
	public List<Tweet> findTweetsPersonasQueSigo(Integer idPersona)
			throws PersistenceException {
		List<Tweet> lista = new LinkedList<Tweet>();
		try {
			String query = "select t.* from tweet t, persona_seguidor ps where t.idPersona = ps.idPersona and ps.idSeguidor = " + idPersona ;
			PreparedStatement statement = ConnectionProvider.getInstance().getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;

	}	
}
