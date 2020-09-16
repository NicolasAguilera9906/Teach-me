package edu.eci.arsw.teachtome.persistence;

import edu.eci.arsw.teachtome.model.Clase;
import edu.eci.arsw.teachtome.model.Draw;
import edu.eci.arsw.teachtome.model.Message;
import edu.eci.arsw.teachtome.model.Request;
import edu.eci.arsw.teachtome.model.User;

import java.util.List;

public interface TeachToMePersistence {
    /**
     * Consulta una clase dentro de la base de datos
     *
     * @param id - El identificador de la clase
     * @return La clase con su respectivo identificador
     * @throws TeachToMePersistenceException - Cuando no existe la clase dentro de la base de datos
     */
    Clase getClase(Long id) throws TeachToMePersistenceException;

    /**
     * Agrega una nueva clase de un usuario dentro de la base de datos
     *
     * @param clase - La nueva clase que se va a agregar
     * @param user  - El usuario que va a dictar esa clase
     * @throws TeachToMePersistenceException - Cuando el usuario no exista en la base de datos o falte información obligatoria de la clase
     */

    void addClase(Clase clase, User user) throws TeachToMePersistenceException;

    /**
     * Obtiene las clases que enseña un usuario
     *
     * @param email  - El mail del usuario del cual se van a obtener las clases
     * @return Las clases que dicta el usuario
     * @throws TeachToMePersistenceException - Cuando el usuario no exista en la base de datos
     */
    List<Clase> getTeachingClassesOfUser(String email)throws TeachToMePersistenceException;

    /**
     * Agrega una nueva clase de un usuario dentro de la base de datos
     *
     * @param clase - La clase a la cual el usuario se va a agregar
     * @param email  - El mail del usuario del cual se van a obtener las clases
     * @throws TeachToMePersistenceException - Cuando el usuario no exista en la base de datos o la clase sea nula
     */

    void addStudentToAClass(Clase clase, String email) throws TeachToMePersistenceException;

    /**
     * Obtiene las clases que aprende un usuario
     *
     * @param email  - El mail del usuario del cual se van a obtener las clases
     * @return Las clases que dicta el usuario
     * @throws TeachToMePersistenceException - Cuando el usuario no exista en la base de datos
     */

    List<Clase> getClassesOfAStudent(String email) throws TeachToMePersistenceException;

    List<Draw> getDrawsOfAClass(String className) throws TeachToMePersistenceException;

    void addClase(String className, Draw draw) throws TeachToMePersistenceException;

    void sendRequest(Request request) throws TeachToMePersistenceException;

    void updateRequest(Request request) throws TeachToMePersistenceException;

    void sendMessage(Message message) throws TeachToMePersistenceException;

    List<Message> getChat(String className) throws TeachToMePersistenceException;

    void addUser(User user) throws TeachToMePersistenceException;

    User getUser(String email) throws TeachToMePersistenceException;

}
