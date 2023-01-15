package org.example.repository;

import org.example.entity.Actor;
import org.example.entity.Casting;
import org.example.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class CastingRepository {

    private final SessionFactory sessionFactory;

    public CastingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Casting createCasting(Casting casting){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(casting);

        transaction.commit();
        session.close();

        return casting;
    }

    public Casting updateCasting(Casting casting){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Casting newCasting = null;
        newCasting = (Casting) session.merge(casting);

        transaction.commit();
        session.close();

        return newCasting;
    }

    public List<Movie> getMoviesOfActor(Integer actorId){
        Session session = sessionFactory.openSession();

        List<Movie> movies = session.createQuery("select m from Movie m " +
                 "join Casting c on c.castingId.movieId = m.id " +
                "join Actor a on c.castingId.actorId = a.id " +
                "where a.id = :actorId", Movie.class).setParameter("actorId",actorId).getResultList();

        session.close();

        return movies;
    }

    public List<Actor> getAllActorsFromASpecificMovie(Integer movieId){
        Session session = sessionFactory.openSession();

        List<Actor> actors = session.createQuery("select a from Actor a " +
                "join Casting c on c.castingId.actorId = a.id " +
                "join Movie m on c.castingId.movieId = m.id " +
                "where m.id = :movieId ", Actor.class).setParameter("movieId",movieId).getResultList();

        session.close();

        return actors;
    }
}
