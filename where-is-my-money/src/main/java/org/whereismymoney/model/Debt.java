package org.whereismymoney.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User debtor;
    @OneToOne
    private User creditor;
    private BigDecimal amount;
    @ManyToOne
    private Group group;
}
