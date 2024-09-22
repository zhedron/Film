package zhedron.film.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhedron.film.entity.Movie;
import zhedron.film.exceptions.MovieNotExistException;
import zhedron.film.services.MovieService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/api")
public class MovieController {
    private MovieService movieService;

    @PostMapping ("/movie/create")
    public ResponseEntity <HttpEntity> create (@RequestBody Movie movie) {
        movieService.create(movie);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping ("/movie/delete/{id}")
    public ResponseEntity <HttpEntity> delete (@PathVariable long id) throws MovieNotExistException {
        movieService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("/movie/{name}")
    public Movie findMovie (@PathVariable String name) throws MovieNotExistException {
        return movieService.findByName(name);
    }

    @GetMapping ("/movies")
    public List<Movie> findAll() {
        return movieService.findAll();
    }
}
