package ma.banking.ebanking.repositories;

import ma.banking.ebanking.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepo extends JpaRepository<AccountOperation, Long> {
}
