package dbclass.movie.repository;

import dbclass.movie.domain.movie.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean existsByName(String name);
}
