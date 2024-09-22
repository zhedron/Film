package zhedron.film.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhedron.film.entity.Actor;
import zhedron.film.exceptions.ActorNotExistException;
import zhedron.film.exceptions.MovieNotExistException;
import zhedron.film.services.ActorService;

import java.util.List;

@RestController
@RequestMapping ("/api")
@AllArgsConstructor
public class ActorController {
    private final ActorService actorService;


    @PostMapping ("/actor/create")
    public ResponseEntity <HttpStatus> create (@RequestBody Actor actor) {
        actorService.create(actor);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping ("/actors")
    public List <Actor> findAll () {
        return actorService.findAll();
    }

    @GetMapping ("/findactor")
    public List<Actor> findActor (@RequestParam (required = false) String name, @RequestParam (required = false) String lastName) throws ActorNotExistException {
        return actorService.findActor(name, lastName);
    }

    @GetMapping ("/actor/{id}")
    public Actor findById (@PathVariable long id) throws ActorNotExistException {
        return actorService.findById(id);
    }

    @PostMapping ("/actor/add/{actorId}/{movieId}")
    public ResponseEntity <String> addActor (@PathVariable long actorId, @PathVariable long movieId) throws ActorNotExistException, MovieNotExistException {
        actorService.addActorToMovie(actorId, movieId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Actor added to Movie");
    }

    @PatchMapping ("/actor/update")
    public ResponseEntity <String> updateAge () {
        actorService.updateAge();

        return ResponseEntity.status(HttpStatus.OK).body("Actor age updated");
    }
}
