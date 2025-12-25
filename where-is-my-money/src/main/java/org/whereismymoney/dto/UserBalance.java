package org.whereismymoney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.whereismymoney.model.User;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class UserBalance {
    User user;
    BigDecimal amount;
}

