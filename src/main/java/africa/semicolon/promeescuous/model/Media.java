package africa.semicolon.promeescuous.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection

    private List<Reaction> reactions;
    @Column(unique = true, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String url;
    @ManyToOne
    private User user;
}
