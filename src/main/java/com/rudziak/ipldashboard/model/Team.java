package com.rudziak.ipldashboard.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {

    public Team(String teamName, Integer totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }

    @Id
    private Long id;
    private String teamName;
    private Integer totalMatches;
    private Integer totalWins;

    public void increaseTotalMatchesCount(int delta) {
        this.totalMatches = this.totalMatches + delta;
    }
}
