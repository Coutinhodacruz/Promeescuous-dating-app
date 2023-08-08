package africa.semicolon.promeescuous.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfBirth;
    @OneToOne
    private Address address;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;

    private boolean isActive;

}
