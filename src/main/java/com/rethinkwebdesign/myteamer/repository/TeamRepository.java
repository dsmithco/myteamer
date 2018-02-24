package com.rethinkwebdesign.myteamer.repository;

import com.rethinkwebdesign.myteamer.model.Team;
import com.rethinkwebdesign.myteamer.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
//    List<User> findByFirstNameLike(String firstName);
}
