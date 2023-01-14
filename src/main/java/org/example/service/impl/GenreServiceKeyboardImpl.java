package org.example.service.impl;

import org.example.entity.Genre;
import org.example.service.GenreService;

import java.util.Scanner;

public class GenreServiceKeyboardImpl implements GenreService {

    @Override
    public Genre read() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Insert the name for the genre :");
        String name = keyBoard.nextLine();

        System.out.println("Insert a description for genre " +  name + ": ");
        String description = keyBoard.nextLine();

        return new Genre(name,description);
    }
}
