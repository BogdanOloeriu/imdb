package org.example.service.impl;

import org.example.entity.AppUser;
import org.example.entity.Movie;
import org.example.entity.Review;
import org.example.repository.AppUserRepository;
import org.example.repository.MovieRepository;
import org.example.repository.ReviewRepository;
import org.example.service.ReviewService;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReviewServiceConsoleImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AppUserRepository appUserRepository;
    private final MovieRepository movieRepository;

    public ReviewServiceConsoleImpl(ReviewRepository reviewRepository, AppUserRepository appUserRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.appUserRepository = appUserRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Review read() {
        Scanner keyBoard = new Scanner(System.in);
        List<Movie> movieList = movieRepository.findAll();
        System.out.println("Write the Id of movie for the one you want to place a Review:");
        movieList.stream()
                .forEach(m -> System.out.println(m.getId() + "\t" + m.getName() + " "));
        Integer movieId = Integer.valueOf(keyBoard.nextLine());

        Movie movie = movieList.stream()
                .filter(m -> m.getId() == movieId)
                .findFirst()
                .get();
        System.out.println("Please insert a rating for the movie choosed:" + movie.getName());
        Integer ratting = Integer.valueOf(keyBoard.nextLine());

        System.out.println("Please insert a Comment for the rating: " + ratting);
        String content = keyBoard.nextLine();

        AppUser user = appUserRepository.create(new AppUser("user@gmail.com","123"));

        return new Review(user,ratting,new Date(System.currentTimeMillis()),content,movie);
    }

    @Override
    public Review add() {
        return reviewRepository.create(read());
    }

    @Override
    public void displayAll() {
        System.out.println(reviewRepository.findAll());
    }
}
