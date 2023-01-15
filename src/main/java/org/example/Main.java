package org.example;

import org.example.config.HibernateConfig;
import org.example.entity.*;
import org.example.repository.AppUserRepository;
import org.example.repository.GenreRepository;
import org.example.repository.MovieRepository;
import org.example.repository.ReviewRepository;
import org.example.service.GenreService;
import org.example.service.MovieService;
import org.example.service.ReviewService;
import org.example.service.impl.GenreServiceConsoleImpl;
import org.example.service.impl.MovieServiceConsoleImpl;
import org.example.service.impl.ReviewServiceConsoleImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class Main {
    private static final MovieRepository movieRepository = new MovieRepository(HibernateConfig.getHibernateSessionFactory());
    private static final GenreRepository genreRepository = new GenreRepository(HibernateConfig.getHibernateSessionFactory());
    private static final ReviewRepository reviewRepository = new ReviewRepository(HibernateConfig.getHibernateSessionFactory());
    private static final AppUserRepository appUserRepository = new AppUserRepository(HibernateConfig.getHibernateSessionFactory());

    private static final ReviewService reviewService = new ReviewServiceConsoleImpl(reviewRepository,appUserRepository,movieRepository);
    private static final GenreService genreService = new GenreServiceConsoleImpl(genreRepository);
    private static final MovieService movieService = new MovieServiceConsoleImpl(genreRepository, movieRepository);

    public static void main(String[] args) {


        try {

//            Genre fantasy = new Genre("Fantasy", "Movie based on fantasy!");
//
////            addToTheTables(sessionFactory);
//
//            fantasy = genreRepository.create(fantasy);
//
//            Movie avatar = new Movie("Avatar", fantasy);
//
//            movieRepository.create(avatar);



//            Genre g = genreService.read();
//            genreRepository.create(g);
//
//            Movie m = movieService.read();
//            movieRepository.create(m);

//            AppUser user1 = appUserRepository.create(new AppUser("user1@gmail.com","password1"));
//            AppUser user2 = appUserRepository.create(new AppUser("user2@gmaol.com","password2"));
//            AppUser user3 = appUserRepository.create(new AppUser("user3@gmail.com","password3"))

//            Review review1 = reviewRepository.create(new Review(user1,9, Date.valueOf("2023-01-12"),
//                    "Good movie",m));
            //review1.getMovie().setName("Random"); - nu ar trebui sa mearga asa

            Menu.enterGeneralMenu(genreService, movieService,reviewService);

            //verifyMovieTag(sessionFactory);

//            Genre documentary = new Genre("documentary", "documentary Movie");
//            documentary = genreRepository.create(documentary);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HibernateConfig.closeHibernateSessionFactory();
        }
    }

    private static void verifyMovieTag(SessionFactory sessionFactory) {
        GenreRepository genreRepository = new GenreRepository(sessionFactory);
        MovieRepository movieRepository = new MovieRepository(sessionFactory);
        Genre horror = new Genre("horror", "horror movies");
        genreRepository.create(horror);
        Movie avatar = new Movie("Avatar", horror);
        Tag parentAdvisor = new Tag("parent advisor");
        Tag imdbTag100 = new Tag("imdb top 100");
        Tag horrorMovie = new Tag("horror movie!"); //ar trebui sa l salveze si pe asta daca il adaug la un film din cauza la cascade
        avatar.addTag(parentAdvisor);
        avatar.addTag(imdbTag100);
        movieRepository.create(avatar);

        Movie movie2 = new Movie("movie2", horror);
        movie2.addTag(imdbTag100);
        movie2 = movieRepository.create(movie2); // daca nu l pun asa,o sa mai creeze unul nou.

        movie2.removeTag(horrorMovie);
        movie2 = movieRepository.update(movie2);

        System.out.println(movieRepository.findAll());

        movieRepository.remove(movie2.getId());
        System.out.println(movieRepository.findAll());
    }

    public static void addToTheTables(SessionFactory sessionFactory) {
        Genre fantasy = new Genre("Fantasy", "Movie based on fantasy!");

        Movie avatar = new Movie("Avatar", fantasy);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(fantasy);
        session.persist(avatar);

        transaction.commit();
        session.close();
    }

}