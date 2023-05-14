package dbclass.movie.repository;

import dbclass.movie.domain.movie.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CastRepository extends JpaRepository<Cast, Long> {

    @Modifying
    @Query("update Cast c set "
            + "c.name = :#{#cast.name}, "
            + "c.birthDate = :#{#cast.birthDate}, "
            + "c.nationality = :#{#cast.nationality}, "
            + "c.info = :#{#cast.info} "
            + "where c.castId = :#{#cast.castId}")
    void updateWithoutImage(@Param("cast") Cast cast);
}
