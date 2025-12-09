package org.whereismymoney.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastVisitedDate;
    private boolean isActive;
    @ManyToOne
    private User owner;
    @ManyToMany
    @Builder.Default
    private Set<User> members = new HashSet<>();
    @OneToOne(mappedBy = "group")
    private Token token;
}
