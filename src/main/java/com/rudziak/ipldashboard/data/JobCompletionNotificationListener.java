package com.rudziak.ipldashboard.data;

import com.rudziak.ipldashboard.model.Team;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Batch job is finished! Verifying...");

            final Map<String, Team> teamData = new HashMap<>();

            em.createQuery("select m.team1, count(m) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(m -> new Team((String) m[0], (long) m[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            em.createQuery("select m.team2, count(m) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .stream()
                    .map(m -> new Team((String) m[0], (long) m[1]))
                    .forEach(team -> {
                        if (teamData.containsKey(team.getTeamName())) {
                            final Team exTeam = teamData.get(team.getTeamName());
                            exTeam.increaseTotalMatchesCount(team.getTotalMatches());
                        } else {
                            teamData.put(team.getTeamName(), team);
                        }
                    });

            em.createQuery("select m.matchWinner, count(m) from Match m group by m.matchWinner", Object[].class)
                    .getResultList()
                    .forEach(objects -> {
                        final String matchWinner = (String) objects[0];
                        final long count = (long) objects[1];
                        final Team team = teamData.get(matchWinner);
                        if (team != null) {
                            team.setTotalWins(count);
                        }
                    });

            teamData.values().forEach(em::persist);
        }
    }
}
