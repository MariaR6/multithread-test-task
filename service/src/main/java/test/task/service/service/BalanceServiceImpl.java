package test.task.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import test.task.service.dto.BankAccountDto;
import test.task.service.entity.BankAccount;
import test.task.service.repository.BankAccountRepository;

import javax.transaction.Transactional;
import java.util.Optional;

import static test.task.service.service.Utils.bankAccountEntityToDto;

@Transactional
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService{

    private final BankAccountRepository bankAccountRepository;

    @Override
    public Optional<BankAccountDto> getBalance(Long id) {
        return bankAccountRepository.getAccount(id).map(Utils::bankAccountEntityToDto);
    }

    @Override
    public void changeBalance(Long id, Long amount) {
        bankAccountRepository.changeBalance(id, amount);
    }
}
