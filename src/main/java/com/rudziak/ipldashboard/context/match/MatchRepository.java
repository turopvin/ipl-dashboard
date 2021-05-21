package com.rudziak.ipldashboard.context.match;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    @Query("select m from Match m where (m.team1 =:teamName or m.team2 =:teamName) and m.date between :startDate and :endDate order by m.date desc")
    List<Match> getMatchesByTeamBetweenDates(
        @Param("teamName") String teamName,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate EndDate
    );
}
