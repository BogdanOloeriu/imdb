package org.example.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CastingId implements Serializable {

    @Column(name = "actor_id")
    private Integer actorId;
    @Column(name = "movie_id")
    private Integer movieId;

    public CastingId() {
    }

    public CastingId(Integer actorId, Integer movieId) {
        this.actorId = actorId;
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CastingId)) return false;
        CastingId castingId = (CastingId) o;
        return actorId.equals(castingId.actorId) && movieId.equals(castingId.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, movieId);
    }

    @Override
    public String toString() {
        return "CastingId{" +
                "actorId=" + actorId +
                ", movieId=" + movieId +
                '}';
    }
}
