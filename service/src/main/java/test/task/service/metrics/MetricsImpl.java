package test.task.service.metrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class MetricsImpl implements Metrics {

    private final AtomicInteger getBalanceCallCount;
    private final AtomicInteger changeBalanceCallCount;

    public MetricsImpl() {
        getBalanceCallCount = new AtomicInteger(0);
        changeBalanceCallCount = new AtomicInteger(0);
    }

    @Override
    public void incGetBalance() {
        getBalanceCallCount.getAndIncrement();
    }

    @Override
    public void incChangeBalance() {
        changeBalanceCallCount.getAndIncrement();
    }

    @Scheduled(fixedDelay = 1000)
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        int getBalanceCount = getBalanceCallCount.getAndSet(0);
        int changeBalanceCount = changeBalanceCallCount.getAndSet(0);
        int sum = getBalanceCount + changeBalanceCount;
        if (sum > 0) {
            log.info("getBalance: {}, changeBalance: {}, sum: {}", getBalanceCount, changeBalanceCount, sum);
        }
    }
}
