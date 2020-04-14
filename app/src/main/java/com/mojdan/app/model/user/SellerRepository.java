package com.mojdan.app.model.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {

	@Query(value = "SELECT * FROM Seller ORDER BY id", countQuery = "SELECT count(*) FROM Seller", nativeQuery = true)
	List<Seller> findAll(Pageable pageable);

	List<Seller> findByFirstName(String firstName);

	Optional<Seller> findById(Long id);

	@Query(value = "SELECT * FROM Seller s JOIN User e WHERE e.username LIKE ?", nativeQuery = true)
	Optional<Seller> findSellerByUsername(String username);

	@Query(value = "SELECT * FROM Seller s WHERE s.user_id LIKE ?", nativeQuery = true)
	Optional<Seller> findSellerByUserId(Long userid);
	
	String getNameById(Long id);

}
