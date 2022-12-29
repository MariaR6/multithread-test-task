package test.task.service.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.service.dto.BankAccountDto;
import test.task.service.service.BalanceService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BankAccountController {

    private final BalanceService balanceService;

    @GetMapping("/getBalance")
    public Optional<BankAccountDto> getBalance(@RequestParam long id) {
        return balanceService.getBalance(id);
    }

    @PostMapping("/changeBalance")
    public void changeBalance(@RequestParam Long id, @RequestParam Long amount) {
        balanceService.changeBalance(id, amount);
    }

}
