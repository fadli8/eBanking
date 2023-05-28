package ma.banking.ebanking.repositories;

import ma.banking.ebanking.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount,Long> {
}
