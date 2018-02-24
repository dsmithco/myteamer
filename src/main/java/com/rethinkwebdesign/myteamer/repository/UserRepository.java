package com.rethinkwebdesign.myteamer.repository;

import com.rethinkwebdesign.myteamer.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    public User findOneByUsername(String username);
    public User findByUsername(String username);

}