package com.localoge.rentalService.repository;

import com.localoge.rentalService.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // JPA annotation to mark this interface as a repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    // There is no need to implement this interface. Spring Data JPA will create an implementation automatically using
    // Rental model and Integer as the primary key type

    // We can add custom methods to this interface to query the database
    boolean existsByPropertyId(Integer propertyId);
    // Here, JPA will generate a query to check if there is a rental with the given propertyId

    Rental findByPropertyId(Integer propertyId);
}
