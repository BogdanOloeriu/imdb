package org.example.service;

import org.example.entity.Genre;

public interface GenreService extends GenericsService<Genre>{

    //citeste de la tastatura gener entitie

    //readFromKeyboard()
    //updateById()
    //readFromFile()
    //readFromIMDB()

    Genre read();
}
