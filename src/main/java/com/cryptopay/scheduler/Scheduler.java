package com.cryptopay.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Scheduler {

    private final BalanceCheckerJob balanceCheckerJob;

    private final ServiceExecutionJob serviceExecutionJob;

    @Scheduled(fixedDelayString = "PT1M")
    public void balanceCheckerJobRun() {
        this.balanceCheckerJob.execute();
    }

    @Scheduled(fixedDelayString = "PT1M")
    public void serviceExecutionJobRun() {
        this.serviceExecutionJob.execute();
    }


}
