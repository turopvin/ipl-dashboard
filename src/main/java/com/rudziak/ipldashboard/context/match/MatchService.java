package com.rudziak.ipldashboard.context.match;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;

    public List<Match> getMatchesByTeamNameAndDateRange(String teamName, LocalDate startDate, LocalDate endDate) {
        return repository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }
}
