package org.example.entity;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Casting{

    @EmbeddedId
    private CastingId castingId;

    private Integer salary;

    public Casting() {
    }

    public Casting(CastingId castingId, Integer salary) {
        this.castingId = castingId;
        this.salary = salary;
    }

    public CastingId getCastingId() {
        return castingId;
    }

    public void setCastingId(CastingId castingId) {
        this.castingId = castingId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Casting)) return false;
        Casting casting = (Casting) o;
        return castingId.equals(casting.castingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(castingId);
    }

    @Override
    public String toString() {
        return "Casting{" +
                "castingId=" + castingId +
                ", salary=" + salary +
                '}';
    }
}
