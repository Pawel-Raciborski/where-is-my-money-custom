package org.spring_security.whereismymoney.model;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Group {
    private UUID id;
    private String name;
    private LocalDateTime creationDate;
    private Owner owner;
    private Set<Member> members;
}
