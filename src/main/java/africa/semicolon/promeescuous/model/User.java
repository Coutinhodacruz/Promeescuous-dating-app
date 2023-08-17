package africa.semicolon.promeescuous.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Address address;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String firstName;

    private String lastName;

    private String createdAt;

    @ElementCollection
    private Set<Interest> interest;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;

    private boolean isActive;

    @PrePersist
    public void setCreatedDate() {
        var currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        createdAt = currentTime.format(formatter);
    }
}
