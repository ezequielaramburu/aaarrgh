package aaarrgh.persistence;

import java.util.List;

import aaarrgh.model.Persona;
import aaarrgh.model.PersonaSeguidor;

public interface PersonaSeguidorDao {

    public void insert(PersonaSeguidor persona) throws PersistenceException;
    
    public void delete(PersonaSeguidor persona) throws PersistenceException;
    
    public void deletePersonaSeguidor(Persona persona, Persona seguidor) throws PersistenceException;

    public void update(PersonaSeguidor persona) throws PersistenceException;
    
    public PersonaSeguidor findById(Integer idPersona) throws PersistenceException;
    
    public List<PersonaSeguidor> findAll() throws PersistenceException;
    
    /* Retorna una lista de las personas que sigo */
    public List<Persona> findPersonasBySeguidor(Integer idSeguidor) throws PersistenceException;
    
    /* Retorna una lista de las personas que me siguen*/
    public List<Persona> findPersonasByPersona(Integer idSeguidor) throws PersistenceException;

    public PersonaSeguidor findByName(String nombre) throws PersistenceException;
    
    
}
