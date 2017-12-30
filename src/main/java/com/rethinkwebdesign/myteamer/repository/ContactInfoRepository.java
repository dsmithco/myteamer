package com.rethinkwebdesign.myteamer.repository;

import com.rethinkwebdesign.myteamer.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface ContactInfoRepository extends CrudRepository<Team, Long> {
}
