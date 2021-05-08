package com.rudziak.ipldashboard.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {

    @Id
    private Long id;
    private String teamName;
    private Integer totalMathces;
    private Integer totalWins;
}
