package zhedron.film.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zhedron.film.entity.Actor;
import zhedron.film.entity.Movie;
import zhedron.film.exceptions.ActorNotExistException;
import zhedron.film.exceptions.MovieNotExistException;
import zhedron.film.repository.ActorRepository;
import zhedron.film.services.ActorService;
import zhedron.film.services.MovieService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;
    private final MovieService movieService;

    @Override
    public void create (Actor actor) {
        int age = convertStringToLocalDate(actor.getBirthDay());

        actor.setAge(age);

        actorRepository.save(actor);
    }

    @Override
    public void updateAge () {
        List<Actor> actors = findAll();

        for (Actor actor : actors) {
            int age = convertStringToLocalDate(actor.getBirthDay());

            actor.setAge(age);

            actorRepository.save(actor);
        }
    }

    @Override
    public List<Actor> findAll () {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById (long id) throws ActorNotExistException {
        return actorRepository.findById(id).orElseThrow(() -> new ActorNotExistException ("Actor not found with " + id));
    }

    @Override
    public List<Actor> findActor (String name, String lastName) throws ActorNotExistException {
        if (name == null && lastName == null) {
            throw new RuntimeException("Name and lastName cannot be null");
        }

        List <Actor> actor;

        if (name != null && lastName != null) {
            actor = actorRepository.findByNameAndLastName(name, lastName);
        } else if (name != null) {
            actor = actorRepository.findByName(name);
        } else {
            actor = actorRepository.findByLastName(lastName);
        }

        if (actor == null || actor.isEmpty()) {
            throw new ActorNotExistException("Actor not found in database");
        }

        return actor;
    }

    @Override
    public int convertStringToLocalDate (String birthDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate birth = LocalDate.parse(birthDay, formatter);

        LocalDate now = LocalDate.now();

        int age = now.getYear() - birth.getYear();

        if (now.getMonthValue() < birth.getMonthValue() || (now.getMonthValue() == birth.getMonthValue() && now.getDayOfMonth() < birth.getDayOfMonth())) {
            age--;
        }

        return age;
    }

    @Override
    public void addActorToMovie (long actorId, long movieId) throws MovieNotExistException, ActorNotExistException {
        Movie movie = movieService.findById(movieId);
        Actor actor = findById(actorId);

        if (actor != null && movie != null) {
            actor.getMovies().add(movie);

            actorRepository.save(actor);
        }
    }
}
