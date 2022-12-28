package test.task.service.repository;

import org.springframework.stereotype.Repository;
import test.task.service.entity.BankAccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<BankAccount> getAccount(long id) {
        return entityManager
                .createNativeQuery("select id, amount from bank_account where id = :id", BankAccount.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public BankAccount changeBalance(long id, long amount) {
        return (BankAccount) entityManager
                .createNativeQuery("insert into bank_account (id, amount) " +
                        "values (:id, :amount) " +
                        "on conflict (id) do update set " +
                        "amount = excluded.amount + bank_account.amount " +
                        "returning id, amount", BankAccount.class)
                .setParameter("id", id)
                .setParameter("amount", amount)
                .getSingleResult();
    }
}
