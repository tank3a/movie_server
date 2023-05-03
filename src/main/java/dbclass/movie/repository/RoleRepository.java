package dbclass.movie.repository;

import dbclass.movie.domain.movie.Role;
import dbclass.movie.domain.movie.RoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, RoleId> {
}
