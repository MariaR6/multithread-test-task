package test.task.service.repository;


import test.task.service.entity.BankAccount;

import java.util.Optional;


public interface BankAccountRepository{

    Optional<BankAccount> getAccount(long id);

    BankAccount changeBalance(long id, long amount);
}
