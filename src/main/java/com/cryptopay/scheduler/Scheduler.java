package com.cryptopay.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final BalanceCheckerJob balanceCheckerJob;

    private final ServiceExecutionJob serviceExecutionJob;

    public Scheduler(BalanceCheckerJob balanceCheckerJob, ServiceExecutionJob serviceExecutionJob) {
        this.balanceCheckerJob = balanceCheckerJob;
        this.serviceExecutionJob = serviceExecutionJob;
    }

    @Scheduled(fixedDelayString = "PT1M")
    public void balanceCheckerJobRun() {
        this.balanceCheckerJob.execute();
    }

    @Scheduled(fixedDelayString = "PT1M")
    public void serviceExecutionJobRun() {
        this.serviceExecutionJob.execute();
    }



}
