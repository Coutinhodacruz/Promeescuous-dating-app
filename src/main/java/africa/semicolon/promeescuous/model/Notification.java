package africa.semicolon.promeescuous.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT", length = 1000)

    private String content;

    private Long sender;
    @ManyToOne
    private User user;

}
