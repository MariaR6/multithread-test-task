package test.task.service.repository;


import org.springframework.stereotype.Repository;
import test.task.service.entity.BankAccount;

import java.util.Optional;

@Repository
public interface BankAccountRepository{

    Optional<BankAccount> getAccount(long id);

    long changeBalance(long id, long amount);
}
