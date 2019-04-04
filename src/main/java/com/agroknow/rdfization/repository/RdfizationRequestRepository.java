package com.agroknow.rdfization.repository;

import com.agroknow.rdfization.model.RdfizationRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RdfizationRequestRepository extends CrudRepository<RdfizationRequest, Long> {

    @Query("SELECT MAX(endingId) from RdfizationRequest ")
    Long findMaxGivenId();

    Optional<RdfizationRequest> findByRequest(String request);

}
