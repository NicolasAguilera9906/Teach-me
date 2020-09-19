package edu.eci.arsw.teachtome.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Clase que representa una solicitud de un usuario dentro de una clase en la aplicación TeachToMe
 */

@Entity(name = "Request")
@Table(name = "requests")
public class Request {

    @EmbeddedId
    private RequestPK requestId;

    @Column(name = "accepted")
    private Boolean accepted;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "class")
    private Clase clase = new Clase();

    @ManyToOne
    @MapsId("email")
    @JoinColumn(name = "student")
    private User student = new User();


    public Request() {
    }

    public Request(RequestPK requestId) {
        this.requestId = requestId;
    }

    public Request(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public RequestPK getRequestId() {
        return requestId;
    }

    public void setRequestId(RequestPK requestId) {
        this.requestId = requestId;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "{accepted=" + accepted +
                ", clase=" + clase +
                ", student=" + student +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestId, request.requestId) &&
                Objects.equals(accepted, request.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, accepted);
    }
}
