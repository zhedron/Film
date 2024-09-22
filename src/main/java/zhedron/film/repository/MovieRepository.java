package zhedron.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhedron.film.entity.Movie;

public interface MovieRepository extends JpaRepository <Movie, Long> {
    Movie findByName(String name);
}
