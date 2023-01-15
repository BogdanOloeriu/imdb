package org.example.service.impl;

import org.example.entity.Genre;
import org.example.entity.Movie;
import org.example.entity.Tag;
import org.example.repository.GenreRepository;
import org.example.repository.MovieRepository;
import org.example.service.MovieService;

import java.util.*;
import java.util.stream.Collectors;

public class MovieServiceConsoleImpl implements MovieService {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

    public MovieServiceConsoleImpl(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie read() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Insert the title of movie: ");
        String title = keyBoard.nextLine();

//        System.out.println("Date:");
//        Date date = Date.valueOf(keyBoard.nextLine());

//        Date releaseDate = null;
//        while (releaseDate == null){
//            System.out.println("Data lansarii:");
//            String userInput = keyBoard.nextLine();
//            try{
//                releaseDate = Date.valueOf(userInput);
//            }catch (IllegalArgumentException e){
//                System.out.println("Ati introdus o data incorecta! Respectati formatul yyyy-MM-dd");
//            }
//        }

        List<Genre> genreList = genreRepository.findAll();

        Genre genre = null;
        while (genre == null) {
            System.out.println("Introduceti unul dintre genurile: " +
                    genreList.stream().map(g -> g.getName()).collect(Collectors.toList()));
            String userInput = keyBoard.nextLine().trim(); // sterge spatiile goale inainte sau dupa text "horror " -> "horror"
            try {
                genre = genreList.stream()
                        .filter(g -> g.getName().equalsIgnoreCase(userInput))
                        .findFirst()
                        .get();
            } catch (NoSuchElementException e) {
                System.out.println("Ati introdus un gen care nu se afla in lista noastra!");
            }
        }

        Movie movie = new Movie(title, genre);
        //movie-tag, horror-movie, oscar-2022 sa fie sub forma asta
        System.out.println("Insert tags for movie : " + title + " Under the form tag-movie and ;");
        String allTags = keyBoard.nextLine();
        Arrays.stream(allTags.split(";"))
                .map(t -> t.toLowerCase())
                .forEach(t -> movie.addTag(new Tag(t)));
//        String[] tagsArray = allTags.split(";");
//
//        for(String tags : tagsArray){
//            movie.addTag(new Tag(tags.trim().toLowerCase(Locale.ROOT)));
//        }
        return movie;
    }

    @Override
    public Movie add() {
        //citire
        Movie newMovie = read();
        //stcare
        return movieRepository.create(newMovie);
    }

    @Override
    public void displayAll() {
        System.out.println(movieRepository.findAll());

    }
}
