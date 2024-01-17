package com.dolph.DolphBank.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
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
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="ime", nullable = false)
    private String name;

    @Column(name="prezime", nullable = false)
    private String surname;

    @Column(name="datumRodjenja", nullable = false)
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

    @Column(name = "aktivan")
    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    private List<String> roles;

    @JsonIgnore
    private String secretKey;

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null) {
            return Collections.singleton(new SimpleGrantedAuthority("UNCONFIRMED"));
        }
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
