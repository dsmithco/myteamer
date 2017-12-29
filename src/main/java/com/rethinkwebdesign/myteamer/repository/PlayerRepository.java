package com.rethinkwebdesign.myteamer.repository;

import com.rethinkwebdesign.myteamer.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
