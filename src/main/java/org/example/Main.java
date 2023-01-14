package org.example;

import org.example.entity.AppUser;
import org.example.entity.Genre;
import org.example.entity.Movie;
import org.example.entity.Review;
import org.example.repository.AppUserRepository;
import org.example.repository.GenreRepository;
import org.example.repository.MovieRepository;
import org.example.repository.ReviewRepository;
import org.example.service.GenreService;
import org.example.service.MovieService;
import org.example.service.impl.GenreServiceKeyboardImpl;
import org.example.service.impl.MovieServiceKeyboardImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class Main {


    public static void main(String[] args) {

        try {

            SessionFactory sessionFactory = new Configuration()
                    .configure("hibernate.config.xml")
                    .addAnnotatedClass(Genre.class)
                    .addAnnotatedClass(Movie.class)
                    .addAnnotatedClass(AppUser.class)
                    .addAnnotatedClass(Review.class)


                    .buildSessionFactory();

            Genre fantasy = new Genre("Fantasy","Movie based on fantasy!");

            Movie avatar = new Movie("Avatar",fantasy);

//            addToTheTables(sessionFactory);

            GenreRepository genreRepository = new GenreRepository(sessionFactory);
            genreRepository.create(fantasy);

            MovieRepository movieRepository = new MovieRepository(sessionFactory);
            movieRepository.create(avatar);


            GenreService genreService = new GenreServiceKeyboardImpl();
            MovieService movieService = new MovieServiceKeyboardImpl(genreRepository);

            Genre g = genreService.read();
            genreRepository.create(g);

            Movie m = movieService.read();
            movieRepository.create(m);


            AppUserRepository appUserRepository = new AppUserRepository(sessionFactory);
            AppUser user1 = appUserRepository.create(new AppUser("user1@gmail.com","password1"));
            AppUser user2 = appUserRepository.create(new AppUser("user2@gmaol.com","password2"));
            AppUser user3 = appUserRepository.create(new AppUser("user3@gmail.com","password3"));

            ReviewRepository reviewRepository = new ReviewRepository(sessionFactory);
            Review review1 = reviewRepository.create(new Review(user1,9, Date.valueOf("2023-01-12"),
                    "Good movie",m));
            //review1.getMovie().setName("Random"); - nu ar trebui sa mearga asa


            System.out.println(movieRepository.findAll());








            sessionFactory.close();


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void addToTheTables(SessionFactory sessionFactory){
        Genre fantasy = new Genre("Fantasy","Movie based on fantasy!");

        Movie avatar = new Movie("Avatar",fantasy);

        Session session =sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(fantasy);
        session.persist(avatar);

        transaction.commit();
        session.close();
    }

}