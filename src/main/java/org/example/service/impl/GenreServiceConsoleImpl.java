package org.example.service.impl;

import org.example.entity.Genre;
import org.example.repository.GenreRepository;
import org.example.service.GenreService;

import java.util.Scanner;

public class GenreServiceConsoleImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceConsoleImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre read() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Insert the name for the genre :");
        String name = keyBoard.nextLine();

        System.out.println("Insert a description for genre " + name + ": ");
        String description = keyBoard.nextLine();

        return new Genre(name, description);
    }

    @Override
    public Genre add() {
        //citire
        Genre newGenre = read();
        //stocare
        return genreRepository.create(newGenre);
    }

    @Override
    public void displayAll() {
        System.out.println(genreRepository.findAll());
    }
}
