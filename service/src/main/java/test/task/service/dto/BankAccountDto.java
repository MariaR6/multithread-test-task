package test.task.service.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountDto {

        private long id;
        private long amount;
}
