package edu.eci.arsw.teachtome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Clase que representa una asignatura que va a ser enseñada dentro de la aplicación TeachToMe
 */
@Entity(name = "Clase")
@Table(name = "classes")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", length = 255, nullable = false)
    private String nombre;

    @Column(name = "capacity", length = 50, nullable = false)
    private int capacity;

    @Column(name = "description", length = 255, unique = true, nullable = false)
    private String description;

    @Column(name = "amount_of_students", length = 50, nullable = false)
    private int amountOfStudents;

    @Column(name = "date_of_init")
    private Date dateOfInit;

    @Column(name = "date_of_end")
    private Date dateOfEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor")
    private User professor;


    public Clase() {
    }


    public Clase(String nombre, int capacity, String description, int amountOfStudents, Date dateOfInit, Date dateOfEnd) {
        this.nombre = nombre;
        this.capacity = capacity;
        this.description = description;
        this.amountOfStudents = amountOfStudents;
        this.dateOfInit = dateOfInit;
        this.dateOfEnd = dateOfEnd;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountOfStudents() {
        return amountOfStudents;
    }

    public void setAmountOfStudents(int amountOfStudents) {
        this.amountOfStudents = amountOfStudents;
    }

    public Date getDateOfInit() {
        return dateOfInit;
    }

    public void setDateOfInit(Date dateOfInit) {
        this.dateOfInit = dateOfInit;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", nombre='" + nombre + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", amountOfStudents=" + amountOfStudents +
                ", dateOfInit=" + dateOfInit +
                ", dateOfEnd=" + dateOfEnd +
                '}';
    }
}
