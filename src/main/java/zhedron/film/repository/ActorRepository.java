package zhedron.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhedron.film.entity.Actor;

import java.util.List;

public interface ActorRepository extends JpaRepository <Actor, Long> {
    List<Actor> findByNameAndLastName(String name, String lastName);

    List<Actor> findByName(String name);

    List<Actor> findByLastName(String lastName);
}
