package dbclass.movie.repository;

import dbclass.movie.domain.user.UserAuthority;
import dbclass.movie.domain.user.UserAuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityId> {

    Optional<UserAuthority> findByLoginId(String loginId);
}
