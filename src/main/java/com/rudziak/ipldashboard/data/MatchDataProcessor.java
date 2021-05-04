package com.rudziak.ipldashboard.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.rudziak.ipldashboard.model.Match;
import lombok.extern.log4j.Log4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Match process(MatchInput input) {
        String firstInningsTeam, secondInningsTeam;
        if ("bat".equals(input.getToss_decision())) {
            firstInningsTeam = input.getToss_winner();
            secondInningsTeam = input.getToss_winner().equals(input.getTeam1())
                ? input.getTeam2() : input.getTeam1();
        } else {
            secondInningsTeam = input.getToss_winner();
            firstInningsTeam = input.getToss_winner().equals(input.getTeam1())
                ? input.getTeam2() : input.getTeam1();
        }

        return Match.builder()
            .id(Long.parseLong(input.getId()))
            .city(input.getCity())
            .date(LocalDate.parse(input.getDate(), FORMAT))
            .playerOfMatch(input.getPlayer_of_match())
            .venue(input.getVenue())
            .neutralVenue(input.getNeutral_venue())
            .team1(firstInningsTeam)
            .team2(secondInningsTeam)
            .tossWinner(input.getToss_winner())
            .tossDecision(input.getToss_decision())
            .matchWinner(input.getWinner())
            .umpire1(input.getUmpire1())
            .umpire2(input.getUmpire2())
            .build();
    }

}
