package test.task.service.service;

import test.task.service.dto.BankAccountDto;
import test.task.service.entity.BankAccount;

public class Utils {

    public static BankAccountDto bankAccountEntityToDto(BankAccount bankAccount) {
        return BankAccountDto.builder()
                .id(bankAccount.getId())
                .amount(bankAccount.getAmount())
                .build();
    }
}
