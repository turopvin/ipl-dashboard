package com.rudziak.ipldashboard.data;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Batch job is finished! Verifying...");

            jdbcTemplate.query("SELECT TOP(5) team1, team2, date FROM match",
                (rs, row) -> "Team 1 " + rs.getString(1) + " Team 2 " + rs.getString(2) + " Date " + rs.getString(3))
                .forEach(System.out::println);
        }
    }
}
