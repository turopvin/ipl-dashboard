package com.rudziak.ipldashboard.context.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
import com.rudziak.ipldashboard.context.match.Match;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    private Long totalMatches;
    private Long totalWins;
    @Transient
    private List<Match> matches;

    public Team(String teamName, Long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }

    public void increaseTotalMatchesCount(long delta) {
        this.totalMatches = this.totalMatches + delta;
    }
}
