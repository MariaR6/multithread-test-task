package test.task.service.metrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class MetricsImpl implements Metrics {

    private final AtomicInteger getBalanceCallCount;
    private final AtomicInteger changeBalanceCallCount;

    public MetricsImpl() {
        getBalanceCallCount = new AtomicInteger(0);
        changeBalanceCallCount =  new AtomicInteger(0);
    }

    @Override
    public void incGetBalance(){
        getBalanceCallCount.getAndIncrement();
    }
    @Override
    public void incChangeBalance(){
        changeBalanceCallCount.getAndIncrement();
    }

    @PostConstruct
    public void init() {
        Timer reporter = new Timer("reporter", false);
        reporter.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int getBalanceCount = getBalanceCallCount.getAndSet(0);
                int changeBalanceCount = changeBalanceCallCount.getAndSet(0);
                int sum = getBalanceCount + changeBalanceCount;
                if(sum > 0) {
                    log.info("getBalance: {}, changeBalance: {}, sum: {}",getBalanceCount, changeBalanceCount, sum);
                }
            }
        }, 1000, 1000);
    }
}
