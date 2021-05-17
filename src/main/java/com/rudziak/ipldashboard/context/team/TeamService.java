package com.rudziak.ipldashboard.context.team;

import java.util.List;
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

    public Team getTeamByName(String teamName) {
        final Team team = teamRepository.findTeamByTeamName(teamName);

        final PageRequest pageRequest = PageRequest.of(0, 4);
        final List<Match> matchList = matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageRequest);
        team.setMatches(matchList);
        return team;
    }
}
