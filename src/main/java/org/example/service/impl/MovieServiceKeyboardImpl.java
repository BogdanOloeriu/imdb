package org.example.service.impl;

import org.example.entity.Genre;
import org.example.entity.Movie;
import org.example.repository.GenreRepository;
import org.example.service.MovieService;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class MovieServiceKeyboardImpl implements MovieService {

    private final GenreRepository genreRepository;

    public MovieServiceKeyboardImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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

        Set<Genre> genreList = genreRepository.findAll();

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

        return new Movie(title, genre);


    }
}
