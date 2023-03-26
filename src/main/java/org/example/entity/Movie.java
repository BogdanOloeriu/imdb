package org.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Movie extends BaseEntity{

    @Column(name = "movie_title")
    private String name;

    //FetchType.EAGER e varianta default pt ManyToOne.
    @JoinColumn(name = "genre_movie_id")
    @ManyToOne
    private Genre genre;


//cascade_Persist -> cand o entitate movie va fi persistata, va cascada (propaga) actiunea PERIST si catre toate entitatile Tag din Set(va incerca sa le salveze si pe ele)
    //cascadeType.MERGE -> cand o entitate movie va fi MERGED va cascada(propaga) actiunea MERGE si catre toate entitatie Tag din Set(va incerca sa le faca update sau create)
    //FetchType.EAGER -> cand incarca o entitate Movie din baza de date, va aduc si oiectele de tipul Tag asociate ei
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
            @JoinTable(name = "movie_tag",
                    joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name"))
    Set<Tag> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "casting",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", genre=" + genre +
                ", tags=" + tags +
                '}';
    }
}
