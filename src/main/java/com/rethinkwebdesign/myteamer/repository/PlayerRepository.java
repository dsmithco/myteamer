package com.rethinkwebdesign.myteamer.repository;

import com.rethinkwebdesign.myteamer.model.Player;
import com.rethinkwebdesign.myteamer.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Iterable<Player> findByTeam(Team team);

}
