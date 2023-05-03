package dbclass.movie.repository;

import dbclass.movie.domain.movie.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    boolean existsByName(String name);
}
