package com.rudziak.ipldashboard.context.team;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findTeamByTeamName(String teamName);
}
