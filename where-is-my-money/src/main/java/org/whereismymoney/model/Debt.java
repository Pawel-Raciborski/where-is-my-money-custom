package org.whereismymoney.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class Debt {
    @Id
    private Long id;
    @OneToOne
    private User debtor;
    @OneToOne
    private User recipient;
    private BigDecimal amount;
    @ManyToOne
    private Group group;
}
