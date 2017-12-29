package com.rethinkwebdesign.myteamer.repository;

import com.rethinkwebdesign.myteamer.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
