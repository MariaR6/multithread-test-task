package test.task.benchmark;


import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {
    private static final int THREAD_COUNT = 10;
    private static final int READ_QUOTA = 100;
    private static final int WRITE_QUOTA = 1;
    private static final List<Long> readIdList = LongStream.range(1, 1000).boxed().collect(Collectors.toList());
    private static final List<Long> writeIdList = LongStream.range(1, 1000).boxed().collect(Collectors.toList());
    private static final long TIMEOUT = 30000;

    public static void main(String[] args) {

        Client client = new Client("http://localhost:8080");
        long endTime = System.currentTimeMillis() + TIMEOUT;
        List<Thread> threadList = new ArrayList<>(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                while (System.currentTimeMillis() < endTime) {
                    // вероятность вызова метода getBalance
                    double readProbability = (double) READ_QUOTA / (double) (READ_QUOTA + WRITE_QUOTA);
                    try {
                        if (ThreadLocalRandom.current().nextDouble() < readProbability) {
                            client.getBalance(randomFromList(readIdList));
                        } else {
                            client.changeBalance(randomFromList(writeIdList), 1L);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
            threadList.add(thread);
        }
        try {
            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static <T> T randomFromList(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
