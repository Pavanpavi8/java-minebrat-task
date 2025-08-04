package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("Select a From Adress a where a.id = :userId ")
	Address findByUserId(long userId);
}
