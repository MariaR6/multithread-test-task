package test.task.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.service.dto.BankAccountDto;
import test.task.service.metrics.Metrics;
import test.task.service.repository.BankAccountRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService{

    private final BankAccountRepository bankAccountRepository;
    private final Metrics metrics;

    @Override
    public Optional<BankAccountDto> getBalance(Long id) {
        metrics.incGetBalance();
        return bankAccountRepository.getAccount(id).map(Utils::bankAccountEntityToDto);
    }

    @Override
    public void changeBalance(Long id, Long amount) {
        metrics.incChangeBalance();
        bankAccountRepository.changeBalance(id, amount);
    }
}
