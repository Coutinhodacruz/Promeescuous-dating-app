package africa.semicolon.promeescuous.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
@Entity
@Setter
@Getter
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection

    private List<Reaction> reactions;
    @Column(name = "url", unique = true, length = 255)
    private String url;
    @ManyToOne
    private User user;
}
