package org.example.repository;

import org.example.entity.Genre;
import org.example.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.security.InvalidParameterException;
import java.util.List;

public class MovieRepository implements CRUDRepository<Movie> {

    private final SessionFactory sessionFactory;

    public MovieRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Movie create(Movie movie) {
        if (movie.getId() != null) {
            throw new InvalidParameterException("Movie create failed!. Can not create a movie which already has an Id!");
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Movie saveMovie = (Movie) session.merge(movie); //sa ne faca update cand duplicam un tag, il vede ca exista deja si el face update, merge va returna un nou obiect
        transaction.commit(); // mai sus am facut asta ca sa ne genereze si id ul, ca merge il lasa cu id null
        session.close();
        return saveMovie;
    }


    //ca sa facem update trebuie sa ne asiguram ca are:
    //are id
    //id-ul se gaseste in baza de date
    public Movie update(Movie movie) {
        if (movie == null || movie.getId() == null) {
            throw new InvalidParameterException("Movie update failed. Can not update an entity without Id!");
        }
        Movie saveMovie = null;
        Session session = sessionFactory.openSession();
        if (session.find(Movie.class, movie.getId()) == null) {
            session.close();
            throw new InvalidParameterException("Movie update failed. Can not find movie entity with Id " + movie.getId() +
                    " In order to update it.");
        }
        Transaction transaction = session.beginTransaction();

        saveMovie = (Movie) session.merge(movie); //sa ne faca update cand duplicam un tag, il vede ca exista deja si el face update, merge va returna un nou obiect
        transaction.commit(); // mai sus am facut asta ca sa ne genereze si id ul, ca merge il lasa cu id null
        session.close();

        return saveMovie;
    }

    public void remove(Integer movieId){
        if(movieId == null){
            throw new InvalidParameterException("Movie delete failed! Must specify a movie Id!");
        }
        Session session = sessionFactory.openSession();
        Movie toDelete = session.find(Movie.class,movieId); //o sa ne returneze direct filmul'
        if(toDelete == null){
            session.close();
            throw new InvalidParameterException("Movie to delete failed. There is no movie entity with the given Id: " + movieId);
        }
        Transaction transaction = session.beginTransaction();

        session.remove(toDelete);

        transaction.commit();
        session.close();

    }


    public List<Movie> findAll() {
        try {
            Session session = sessionFactory.openSession();

            List<Movie> movies = session.createQuery("from Movie", Movie.class).getResultList();

            session.close();

            return movies;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
