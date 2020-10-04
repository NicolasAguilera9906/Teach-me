package edu.eci.arsw.teachtome.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Clase que representa un usuario dentro de la aplicación TeachToMe
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "professor")
    @JsonManagedReference
    private List<Clase> teachingClasses;

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private List<Clase> studyingClasses = new ArrayList<Clase>();

    public User() {
    }

    public User(String email, String firstName, String lastName, String password, String description, List<Clase> teachingClasses) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.description = description;
        this.teachingClasses = teachingClasses;
    }

    public User(String email, String firstName, String lastName, String password, String description) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.description = description;
        this.teachingClasses = new CopyOnWriteArrayList<>();
    }

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.teachingClasses = new CopyOnWriteArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Clase> getTeachingClasses() {
        return teachingClasses;
    }

    public void setTeachingClasses(List<Clase> teachingClasses) {
        this.teachingClasses = teachingClasses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Clase> getStudyingClasses() {
        return studyingClasses;
    }

    public void setStudyingClasses(List<Clase> studyingClasses) {
        this.studyingClasses = studyingClasses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", teachingClasses=" + teachingClasses +
                ", studyingClasses=" + studyingClasses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, password, description, teachingClasses);
    }
}
