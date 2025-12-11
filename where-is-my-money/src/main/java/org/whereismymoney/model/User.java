package org.whereismymoney.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    private String email;
    @OneToOne(mappedBy = "user")
    private Token token;
    @OneToMany(mappedBy = "owner")
    private Set<Group> ownedGroups = new HashSet<>();
    @ManyToMany(mappedBy = "members")
    private Set<Group> memberGroups = new HashSet<>();
}
