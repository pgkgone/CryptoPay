package com.cryptopay.repository;

import com.cryptopay.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}