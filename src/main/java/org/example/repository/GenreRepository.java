package org.example.repository;

import org.example.entity.Genre;
import org.example.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class GenreRepository {

    private final SessionFactory sessionFactory;

    public GenreRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Genre saveGenre(Genre genre){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.persist(genre);
            transaction.commit();
            session.close();
            return genre;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Set<Genre> findAll(){

        Session session = sessionFactory.openSession();

        Set<Genre> genres = session.createQuery("select t from Genre t ", Genre.class).getResultStream().collect(Collectors.toSet());

        session.close();

        return genres;

    }
}