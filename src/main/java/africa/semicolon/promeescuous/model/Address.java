package africa.semicolon.promeescuous.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String houseNumber;
    private String street;
    private String state;
    private String country;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(houseNumber)
                .append(",")
                .append(street)
                .append(",")
                .append(state)
                .append(",")
                .append(country);

        return builder.toString();
    }
}
