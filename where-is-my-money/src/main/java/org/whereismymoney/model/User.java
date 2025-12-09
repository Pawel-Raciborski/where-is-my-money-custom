package org.whereismymoney.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
    @Column
    private String email;
    @OneToMany
    @Builder.Default
    private Set<Token> tokens = new HashSet<>();
    @OneToMany(mappedBy = "owner")
    @Builder.Default
    private Set<Group> ownedGroups = new HashSet<>();
    @ManyToMany(mappedBy = "members")
    @Builder.Default
    private Set<Group> memberGroups = new HashSet<>();
}
