package com.alfred.alfred.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.alfred.alfred.models.User;

@Repository
public interface authReponsitory extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

}
