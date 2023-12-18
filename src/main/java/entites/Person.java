package entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="osoba")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
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
}
