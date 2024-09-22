package zhedron.film.services;

import zhedron.film.entity.Movie;
import zhedron.film.exceptions.MovieNotExistException;

import java.util.List;

public interface MovieService {
    void create (Movie movie);

    void delete (long id) throws MovieNotExistException;

    Movie findByName (String name) throws MovieNotExistException;

    Movie findById (long id) throws MovieNotExistException;

    List<Movie> findAll ();
}
