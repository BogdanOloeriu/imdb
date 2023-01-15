package org.example;

import org.example.config.HibernateConfig;
import org.example.entity.*;
import org.example.repository.*;
import org.example.service.ActorService;
import org.example.service.GenreService;
import org.example.service.MovieService;
import org.example.service.ReviewService;
import org.example.service.impl.ActorServiceConsoleImpl;
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

    private static final ActorRepository actorRepository = new ActorRepository(HibernateConfig.getHibernateSessionFactory());
    private static final CastingRepository castingRepository = new CastingRepository(HibernateConfig.getHibernateSessionFactory());

    private static final ReviewService reviewService = new ReviewServiceConsoleImpl(reviewRepository, appUserRepository, movieRepository);
    private static final GenreService genreService = new GenreServiceConsoleImpl(genreRepository);
    private static final MovieService movieService = new MovieServiceConsoleImpl(genreRepository, movieRepository);
    private static final ActorService actorService = new ActorServiceConsoleImpl(actorRepository);

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

            //Menu.enterGeneralMenu(genreService, movieService,reviewService,actorService);

            //verifyMovieTag(sessionFactory);

//            Genre documentary = new Genre("documentary", "documentary Movie");
//            documentary = genreRepository.create(documentary);

            initializeDateBase(HibernateConfig.getHibernateSessionFactory());


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

    private static void initializeDateBase(SessionFactory sessionFactory) {
        Genre fantasy = new Genre("fantasy", "fantasy movie");
        Genre action = new Genre("action", "action movie");
        Genre horror = new Genre("horror", "horror movie");
        fantasy = genreRepository.create(fantasy);
        action = genreRepository.create(action);
        horror = genreRepository.create(horror);

        Movie movie1 = new Movie("movie1", fantasy);
        Movie movie2 = new Movie("movie2", fantasy);
        Movie movie3 = new Movie("movie3", horror);
        movie1 = movieRepository.create(movie1);
        movie2 = movieRepository.create(movie2);
        movie3 = movieRepository.create(movie3);

        Tag fantasyTag = new Tag("fantasy movie");
        Tag horrorTag = new Tag("horror movie");
        Tag actionTag = new Tag("action movie");
        movie1.addTag(fantasyTag);
        movie2.addTag(fantasyTag);
        movie3.addTag(horrorTag);

        AppUser user1 = new AppUser("user1@gmail.com", "1234");
        user1 = appUserRepository.create(user1);
        AppUser user2 = new AppUser("user2@gmail.com", "5667");
        user2 = appUserRepository.create(user2);

        Review review1 = new Review(user1, 9, Date.valueOf("2023-01-10"), "Good Movie", movie1);
        Review review2 = new Review(user2, 7, Date.valueOf("2023-02-01"), "Good", movie2);
        review1 = reviewRepository.create(review1);
        review2 = reviewRepository.create(review2);

        Actor tomCruise = new Actor("Tom", "Cruise", Date.valueOf("1890-01-12"));
        Actor sandraBullok = new Actor("Sandra", "Bullok", Date.valueOf("1870-05-12"));
        tomCruise = actorRepository.create(tomCruise);
        sandraBullok = actorRepository.create(sandraBullok);

        Casting casting1 = new Casting(new CastingId(tomCruise.getId(), movie1.getId()), 1_000_000);
        Casting casting2 = new Casting(new CastingId(sandraBullok.getId(), movie3.getId()), 22_000_000);
        casting1 = castingRepository.createCasting(casting1);
        casting2 = castingRepository.createCasting(casting2);

        System.out.println("Tom cruise paied in: " + castingRepository.getMoviesOfActor(tomCruise.getId()));

        System.out.println("Movie1 actors: " + castingRepository.getAllActorsFromASpecificMovie(movie1.getId()));

        Movie movieWithActors = movieRepository.getMovieWithActors(movie1.getId());
        System.out.println(movieWithActors + " " + movieWithActors.getActors());
        System.out.println("Finished initializing");


    }

}