package com.rudziak.ipldashboard.api;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.rudziak.ipldashboard.context.match.Match;
import com.rudziak.ipldashboard.context.match.MatchService;
import com.rudziak.ipldashboard.context.team.Team;
import com.rudziak.ipldashboard.context.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@CrossOrigin
public class TeamController {

    private final TeamService teamService;
    private final MatchService matchService;

    @GetMapping("/{teamName}")
    public ResponseEntity<Team> getTeam(@PathVariable String teamName) {
        final Optional<Team> team = teamService.getTeamByName(teamName);
        if (team.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(team.get());
    }

    @GetMapping("/{teamName}/matches")
    public ResponseEntity<List<Match>> getMatches(@PathVariable String teamName, @RequestParam int year) {
        final LocalDate startDate = LocalDate.of(year, 1, 1);
        final LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        final List<Match> matchList = matchService.getMatchesByTeamNameAndDateRange(teamName, startDate, endDate);
        return ResponseEntity.ok(matchList);
    }
}
