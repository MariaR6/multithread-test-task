package test.task.service.service;

import test.task.service.dto.BankAccountDto;

import java.util.Optional;

public interface BalanceService {

    Optional<BankAccountDto> getBalance(Long id);

    void changeBalance(Long id, Long amount);
}
