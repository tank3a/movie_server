package dbclass.movie.repository;

import dbclass.movie.domain.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByLoginId(String loginId);
    Optional<Admin> findByLoginId(String loginId);
}
