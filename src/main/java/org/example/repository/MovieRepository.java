package org.example.repository;

import org.example.entity.Genre;
import org.example.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MovieRepository {

    private final SessionFactory sessionFactory;

    public MovieRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Movie saveMovie(Movie movie){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.persist(movie);
            transaction.commit();
            session.close();
            return movie;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findAll(){
        try{
            Session session = sessionFactory.openSession();

            List<Movie> movies = session.createQuery("from Movie", Movie.class).getResultList();

            session.close();

            return movies;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
