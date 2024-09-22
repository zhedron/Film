package zhedron.film.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zhedron.film.entity.Movie;
import zhedron.film.exceptions.MovieNotExistException;
import zhedron.film.repository.MovieRepository;
import zhedron.film.services.MovieService;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public void create (Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void delete (long id) throws MovieNotExistException {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotExistException("Movie not found with " + id  + " and cannot be deleted.");
        }

        movieRepository.deleteById(id);
    }

    @Override
    public Movie findByName(String name) throws MovieNotExistException {
        Movie movie = movieRepository.findByName(name);

        if (movie == null) {
            throw new MovieNotExistException("Movie not found with " + name);
        }

        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById (long id) throws MovieNotExistException {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotExistException("Movie not found with " + id));
    }
}
