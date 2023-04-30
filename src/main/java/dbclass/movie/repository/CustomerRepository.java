package dbclass.movie.repository;

import dbclass.movie.domain.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByLoginId(String loginId);
    Optional<Customer> findByLoginId(String loginId);
    boolean existsByEmail(String email);
}
