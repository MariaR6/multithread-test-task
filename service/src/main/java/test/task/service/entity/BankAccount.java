package test.task.service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Long amount;
}
