package africa.semicolon.promeescuous.repositories;

import africa.semicolon.promeescuous.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
