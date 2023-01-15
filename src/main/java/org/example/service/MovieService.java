package org.example.service;

import org.example.entity.Movie;

public interface MovieService extends GenericsService<Movie> {

    Movie read();
}
