package test.task.service.service;

import java.util.Optional;

public interface BalanceService {

    Optional<Long> getBalance(Long id);

    void changeBalance(Long id, Long amount);
}
