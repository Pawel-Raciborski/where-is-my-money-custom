package org.whereismymoney.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserExpense {
    @Id
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    private BigDecimal amount;
}
