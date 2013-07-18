package aaarrgh.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import aaarrgh.model.Persona;
import aaarrgh.model.PersonaSeguidor;

public class PersonaSeguidorDaoJdbcImpl implements PersonaSeguidorDao {

	private static PersonaSeguidorDao instance = new PersonaSeguidorDaoJdbcImpl();

	public static PersonaSeguidorDao getInstance() {
		return instance;
	}

	@Override
	public void insert(PersonaSeguidor persona) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into Persona_Seguidor (idPersona, idSeguidor) values (?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setInt(1, persona.getIdPersona());
			statement.setInt(2, persona.getIdSeguidor());

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
	public void delete(PersonaSeguidor persona) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from Persona_Seguidor where idPersonaSeguidor = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, persona.getIdPersonaSeguidor());
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
	public void deletePersonaSeguidor(Persona persona, Persona seguidor) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete * from Persona_Seguidor where idPersona = ? and idSeguidor = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, persona.getId());
			statement.setInt(2, seguidor.getId());
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
	public void update(PersonaSeguidor persona) throws PersistenceException {

	}

	public List<PersonaSeguidor> findAll() throws PersistenceException {
		List<PersonaSeguidor> lista = new LinkedList<PersonaSeguidor>();
		try {
			String query = "select * from Persona_Seguidor";
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
	public PersonaSeguidor findById(Integer idPersona) throws PersistenceException {
		if (idPersona == null) {
			throw new IllegalArgumentException(
					"El id de PersonaSeguidor no debe ser nulo");
		}
		PersonaSeguidor personaSeguidor = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from Persona_Seguidor where idPersonaSeguidor = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idPersona);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				personaSeguidor = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return personaSeguidor;
	}
	 @Override
	public PersonaSeguidor findByName(String nombre) throws PersistenceException {
		return null;

	}
	 

	 	 
	@Override
	public List<Persona> findPersonasBySeguidor(Integer idSeguidor)
			throws PersistenceException {
		List<Persona> lista = new LinkedList<Persona>();
		try {
			String query = "select * from Persona p, Persona_Seguidor ps where p.idPersona = ps.idPersona and ps.idSeguidor = " + idSeguidor;
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOnePersona(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;		
	}

	@Override
	public List<Persona> findPersonasByPersona(Integer idPersona)
			throws PersistenceException {
		List<Persona> lista = new LinkedList<Persona>();
		try {
			String query = "select * from Persona p, Persona_Seguidor ps where p.idPersona = ps.idPersona and ps.idPersona = " + idPersona;
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOnePersona(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;		
	}	
	private PersonaSeguidor convertOne(ResultSet resultSet) throws SQLException {
		PersonaSeguidor retorno = new PersonaSeguidor();

		retorno.setIdPersona(resultSet.getInt("idPersona"));
		retorno.setIdPersonaSeguidor(resultSet.getInt("idPersonaSeguidor"));
		retorno.setIdSeguidor(resultSet.getInt("idSeguidor"));

		return retorno;
	}
	private Persona convertOnePersona(ResultSet resultSet) throws SQLException {
		Persona retorno = new Persona();

		retorno.setId(resultSet.getInt("id"));
		retorno.setNombre(resultSet.getString("nombre"));
		retorno.setApellido(resultSet.getString("apellido"));
		retorno.setEdad(resultSet.getInt("edad"));
		retorno.setPass(resultSet.getString("pass"));

		return retorno;
	}

}
