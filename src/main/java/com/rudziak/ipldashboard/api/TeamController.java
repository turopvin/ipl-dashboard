package com.rudziak.ipldashboard.api;

import com.rudziak.ipldashboard.context.team.Team;
import com.rudziak.ipldashboard.context.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/{teamName}")
    public ResponseEntity<Team> get(@PathVariable String teamName) {
        final Team team = teamService.getTeamByName(teamName);
        return ResponseEntity.ok(team);
    }
}
