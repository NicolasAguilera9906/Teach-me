package edu.eci.arsw.teachtome.persistence;

import edu.eci.arsw.teachtome.model.*;
import edu.eci.arsw.teachtome.persistence.repositories.ClaseRepository;
import edu.eci.arsw.teachtome.persistence.repositories.RequestRepository;
import edu.eci.arsw.teachtome.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la capa de persistencia de la aplicación TeachToMe
 */
@Service
public class TeachToMePersistenceImpl implements TeachToMePersistence {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Clase getClase(Long id) throws TeachToMePersistenceException {
        Clase clase = null;
        if (id == null) throw new TeachToMePersistenceException("El id no puede ser nulo");
        if (claseRepository.existsById(id)) {
            clase = claseRepository.findById(id).get();
        }
        if (clase == null) throw new TeachToMePersistenceException("No existe la clase con el id " + id);
        return clase;
    }

    @Override
    public void addClase(Clase clase, User user) throws TeachToMePersistenceException {
        if (clase == null) throw new TeachToMePersistenceException("La clase no puede ser nula");
        if (user == null) throw new TeachToMePersistenceException("El usuario no puede ser nulo");
        user.getTeachingClasses().add(clase);
        clase.setProfessor(user);
        User userSaved = userRepository.save(user);
        long claseId = userSaved.getTeachingClasses().get(0).getId();
        clase.setId(claseId);
    }

    @Override
    public void addStudentToAClass(Clase clase, String email) throws TeachToMePersistenceException {
        User user = getUser(email);
        if (clase == null) throw new TeachToMePersistenceException("La clase no puede ser nula");
        if (email.equals(clase.getProfessor().getEmail())) {
            throw new TeachToMePersistenceException("El profesor no puede ser añadido a su propia clase");
        }
        for (User student : clase.getStudents()) {
            if (student.getEmail().equals(email)) {
                throw new TeachToMePersistenceException("El usuario con el email " + email + " ya se encuentra en la clase");
            }
        }
        clase.getStudents().add(user);
        user.getStudyingClasses().add(clase);
        Request request = getRequest(clase.getId(),user.getEmail());
        request.setAccepted(true);
        claseRepository.save(clase);
        requestRepository.save(request);
    }

    @Override
    public List<Clase> getFilteredClassesByName(String nameFilter) throws TeachToMePersistenceException {
        if(nameFilter==null){
            throw new TeachToMePersistenceException("El nombre no puede ser nulo");
        }
        return claseRepository.filterByName(nameFilter);
    }
    @Override
    public void addUser(User user) throws TeachToMePersistenceException {
        if (user == null) throw new TeachToMePersistenceException("El usuario no puede ser nulo");
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) throws TeachToMePersistenceException {
        User user = null;
        if (email == null) throw new TeachToMePersistenceException("El email no puede ser nulo");
        if (userRepository.existsById(email)) {
            user = userRepository.findById(email).get();
        }
        if (user == null) throw new TeachToMePersistenceException("No existe el usuario con el email " + email);
        return user;
    }

    @Override
    public List<Clase> getTeachingClassesOfUser(String email) throws TeachToMePersistenceException {
        User user = getUser(email);
        return user.getTeachingClasses();
    }

    @Override
    public void sendRequest(Request request) throws TeachToMePersistenceException {
        if (request == null) throw new TeachToMePersistenceException("La solicitud no puede ser nula");
        if (request.getRequestId() == null) {
            throw new TeachToMePersistenceException("La solicitud no puede estar vacía");
        }
        User student = getUser(request.getRequestId().getStudent());
        Clase clase = getClase(request.getRequestId().getClase());
        if (clase.getProfessor().getEmail().equals(student.getEmail())) {
            throw new TeachToMePersistenceException("El profesor no puede hacer un request a su misma clase");
        }
        request.setClase(clase);
        request.setStudent(student);
        requestRepository.save(request);
    }

    @Override
    public List<Request> getRequestsOfAClass(long classId, String email) throws TeachToMePersistenceException {
        Clase clase = getClase(classId);
        User professor = getUser(email);
        if (!(clase.getProfessor().getEmail().equals(professor.getEmail()))) {
            throw new TeachToMePersistenceException("No tiene permitido ver los requests a esta clase");
        }
        List<Request> requests = requestRepository.findAll();
        List<Request> requestsOfClass = new ArrayList<>();
        for (Request request : requests) {
            if (request.getRequestId().getClase() == classId) {
                requestsOfClass.add(request);
            }
        }
        return requestsOfClass;
    }

    @Override
    public List<Clase> getClassesOfAStudent(String email) throws TeachToMePersistenceException {
        User user = getUser(email);
        return user.getStudyingClasses();
    }

    @Override
    public Request getRequest(long classId, String emailStudent) throws TeachToMePersistenceException {
        User student = getUser(emailStudent);
        Clase clase = getClase(classId);
        RequestPK requestPK = new RequestPK(student.getEmail(), clase.getId());
        Optional<Request> request = requestRepository.findById(requestPK);
        if (!(request.isPresent())) {
            throw new TeachToMePersistenceException("El usuario con el email " + emailStudent + " no ha solicitado unirse a la clase con el nombre " + clase.getNombre());
        }
        return request.get();
    }

    @Override
    public void updateRequest(Long classId, String email, Request request) throws TeachToMePersistenceException {
        User student = getUser(request.getRequestId().getStudent());
        Clase clase = getClase(request.getRequestId().getClase());
        if(!(email.equals(clase.getProfessor().getEmail()))){
            throw new TeachToMePersistenceException("No tiene permitido actualizar el request de esta clase");
        }
        boolean accepted = request.isAccepted();
        if(accepted){
            addStudentToAClass(clase,student.getEmail());
        }
        else{
            request = getRequest(clase.getId(), student.getEmail());
            request.setAccepted(false);
            requestRepository.save(request);
        }
    }

    @Override
    public List<Draw> getDrawsOfAClass(String className) throws TeachToMePersistenceException {
        return null;
    }

    @Override
    public void addClase(String className, Draw draw) throws TeachToMePersistenceException {

    }


    @Override
    public void sendMessage(Message message) throws TeachToMePersistenceException {

    }

    @Override
    public List<Message> getChat(String className) throws TeachToMePersistenceException {
        return null;
    }

}
