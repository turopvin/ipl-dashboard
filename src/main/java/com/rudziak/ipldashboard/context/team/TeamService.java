package com.rudziak.ipldashboard.context.team;

import java.util.List;
import java.util.Optional;
import com.rudziak.ipldashboard.context.match.Match;
import com.rudziak.ipldashboard.context.match.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public Optional<Team> getTeamByName(String teamName) {
        final Team team = teamRepository.findTeamByTeamName(teamName);
        if (team == null) {
            return Optional.empty();
        }

        final PageRequest pageRequest = PageRequest.of(0, 4);
        final List<Match> matchList = matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageRequest);
        team.setMatches(matchList);
        return Optional.of(team);
    }
}
