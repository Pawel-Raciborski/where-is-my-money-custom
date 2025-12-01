package org.spring_security.whereismymoney.model;


import java.time.LocalDateTime;

public class Token {
    private Integer id;
    private String token;
    private LocalDateTime expireDateTime;
    private Group group;
}
