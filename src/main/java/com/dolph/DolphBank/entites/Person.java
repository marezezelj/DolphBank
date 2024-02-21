package com.dolph.DolphBank.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="osoba")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="ime", nullable = false)
    private String name;

    @Column(name="prezime", nullable = false)
    private String surname;

    @Column(name="datum_rodjenja", nullable = false)
    private Date birthDate;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "telefon", nullable = false, unique = true)
    private String phone;

    @Column(name= "password", length = 50)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "adresa", nullable = false)
    private String address;

    @Column(name = "aktivna")
    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private List<PersonRole> roles;

    @JsonIgnore
    @Column(name = "secret_key")
    private String secretKey;

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null) {
            return Collections.singleton(new SimpleGrantedAuthority("CLIENT"));
        }
        return roles.stream().map(role->new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}
