package zhedron.film.services;

import zhedron.film.entity.Actor;
import zhedron.film.exceptions.ActorNotExistException;
import zhedron.film.exceptions.MovieNotExistException;

import java.util.List;


public interface ActorService {
    void create (Actor actor);

    void updateAge ();

    List<Actor> findAll ();

    Actor findById (long id) throws ActorNotExistException;

    List<Actor> findActor (String name, String lastName) throws ActorNotExistException;

    int convertStringToLocalDate (String birthDay);

    void addActorToMovie (long actorId, long movieId) throws MovieNotExistException, ActorNotExistException;
}
