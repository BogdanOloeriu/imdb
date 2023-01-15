package org.example.repository;

import org.example.entity.Genre;
import org.example.entity.Movie;
import org.example.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenreRepository implements CRUDRepository<Genre> {

    private final SessionFactory sessionFactory;

    public GenreRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Genre create(Genre genre) {
        if (genre.getId() != null) {
            throw new InvalidParameterException("Genre create failed!  Can not create a genre which already has an Id! ");
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Genre newGenre = (Genre) session.merge(genre);
        transaction.commit();
        session.close();
        return newGenre;

    }

    public Genre update(Genre genre) {
        if (genre == null || genre.getId() == null) {
            throw new InvalidParameterException("Genre update failed! Can not update an entity without update!");
        }
        Genre saveGenre = null;
        Session session = sessionFactory.openSession();
        if (session.find(Genre.class, genre.getId()) == null) {
            session.close();
            throw new InvalidParameterException("Genre update failed! Can not find a Genre with Id : " + genre.getId() +
                    "In order to update it");
        }
        Transaction transaction = session.beginTransaction();
        saveGenre = (Genre) session.merge(genre);
        transaction.commit();
        session.close();
        return saveGenre;
    }

    public void remove(Integer genreId) {
        if (genreId == null) {
            throw new InvalidParameterException("Genre delete failed! Can not delete a Genre without Id");
        }
        Session session = sessionFactory.openSession();
        Genre toDeleteGenre = session.find(Genre.class, genreId);
        if (toDeleteGenre == null) {
            session.close();
            throw new InvalidParameterException("Genre to delete failed. There is no genre entity with the given Id: " +
                    genreId);
        }
        Transaction transaction = session.beginTransaction();
        session.remove(toDeleteGenre);
        transaction.commit();
        session.close();
    }

    public List<Genre> findAll() {

        Session session = sessionFactory.openSession();

        List<Genre> genres = session.createQuery("select t from Genre t ", Genre.class).getResultList();

        session.close();

        return genres;

    }
}
