package org.whereismymoney.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private User owner;
    private BigDecimal totalAmount;
    @OneToMany
    private Set<UserExpense> expenseMembers;
    @ManyToOne
    private Group group;
    private boolean isCustom;
    private LocalDateTime time;
}
