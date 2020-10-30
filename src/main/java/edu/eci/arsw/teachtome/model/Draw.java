package edu.eci.arsw.teachtome.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un dibujo de una sesión dentro de la aplicación TeachToMe
 */
@Entity(name = "Draw")
@Table(name = "draws")
public class Draw {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "date_of_draw")
    private Timestamp dateOfDraw;

    @ManyToOne
    @JoinColumn(name = "session")
    @JsonBackReference
    private Session session;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "draw")
    @JsonManagedReference
    private List<Point> points = new ArrayList<Point>();

    public Draw() {
    }

    public Draw(Timestamp dateOfDraw, List<Point> points) {
        this.dateOfDraw = dateOfDraw;
        this.points = points;
    }

    public Draw(List<Point> points) {
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateOfDraw() {
        return dateOfDraw;
    }

    public void setDateOfDraw(Timestamp dateOfDraw) {
        this.dateOfDraw = dateOfDraw;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Draw draw = (Draw) o;
        return Objects.equals(points, draw.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "Draw{" +
                "id=" + id +
                ", dateOfDraw=" + dateOfDraw +
                ", session=" + session +
                ", points=" + points +
                '}';
    }
}
