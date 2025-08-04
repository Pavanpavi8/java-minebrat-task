package repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE (:name IS NULL OR u.username LIKE %:name%) AND (:pinCode IS NULL OR u.address.pinCode = :pinCode) AND (u.registrationDate BETWEEN :startDate AND :endDate)")
	Page<User> search(@Param("name") String name, @Param("pinCode") String pinCode,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);

	Optional<User> findByUsernameAndPassword(String username, String password);
}
