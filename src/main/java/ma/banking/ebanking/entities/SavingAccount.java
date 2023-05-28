package ma.banking.ebanking.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@DiscriminatorValue("SA")
@Data @Table @AllArgsConstructor @NoArgsConstructor
public class SavingAccount extends BankAccount {
    private double interestRate;
}
