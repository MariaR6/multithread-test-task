package test.task.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.service.dto.BankAccountDto;
import test.task.service.metrics.Metrics;
import test.task.service.repository.BankAccountRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Transactional
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BankAccountRepository bankAccountRepository;
    private final Metrics metrics;
    private final ConcurrentMap<Long, BankAccountDto> cache = new ConcurrentHashMap<>(100, 0.75f, 16);
    private static final boolean useCache = true;

    @Override
    public Optional<BankAccountDto> getBalance(Long id) {
        metrics.incGetBalance();
        if (useCache) {
            return Optional.ofNullable(cache.computeIfAbsent(id, (x) -> bankAccountRepository.getAccount(id).map(Utils::bankAccountEntityToDto).orElse(null)));
        } else {
            return bankAccountRepository.getAccount(id).map(Utils::bankAccountEntityToDto);
        }
    }

    @Override
    public void changeBalance(Long id, Long amount) {
        metrics.incChangeBalance();
        if (useCache) {
            cache.remove(id);
        }
        bankAccountRepository.changeBalance(id, amount);
    }
}
