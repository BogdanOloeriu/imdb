package org.example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Movie extends BaseEntity{

    @Column(name = "movie_title")
    private String name;

    @JoinColumn(name = "genre_movie_id")
    @ManyToOne
    private Genre genre;

    public Movie() {
    }

    public Movie(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" + "Id: " + getId() + '\'' +
                "name='" + name + '\'' +
                ", genre=" + genre.getName() +
                '}';
    }
}
