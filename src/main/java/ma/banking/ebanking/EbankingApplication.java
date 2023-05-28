package ma.banking.ebanking;

import ma.banking.ebanking.entities.*;
import ma.banking.ebanking.enumes.AccountStatus;
import ma.banking.ebanking.enumes.OperationType;
import ma.banking.ebanking.repositories.AccountOperationRepo;
import ma.banking.ebanking.repositories.BankAccountRepo;
import ma.banking.ebanking.repositories.CustomerRepo;
import ma.banking.ebanking.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args ->  bankAccountService.consulting();
    }

//    @Bean
    CommandLineRunner start (CustomerRepo customerRepo,
                             BankAccountRepo bankAccountRepo,
                             AccountOperationRepo accountOperationRepo){

        return args -> {
            Stream.of("ayman", "hamza", "mahjouba").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepo.save(customer);
            });

            customerRepo.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setCustomer(customer);
                currentAccount.setStatus(AccountStatus.Created);
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCurrency("DH");
                currentAccount.setOverdraft(9000);
                bankAccountRepo.save(currentAccount);

                SavingAccount saveAccount = new SavingAccount();
                saveAccount.setCustomer(customer);
                saveAccount.setStatus(AccountStatus.Created);
                saveAccount.setBalance(Math.random() * 90000);
                saveAccount.setCurrency("DH");
                saveAccount.setInterestRate(9000);
                bankAccountRepo.save(saveAccount);

            });

            bankAccountRepo.findAll().forEach(account -> {
                for (int i = 0; i <= 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setBankAccount(account);
                    accountOperation.setDate(new Date());
                    accountOperation.setType(Math.random() > 0.5? OperationType.Debit : OperationType.Credit);
                    accountOperation.setAmount(Math.random()*9000);
                    accountOperationRepo.save(accountOperation);
                }
            });

            BankAccount account = bankAccountRepo.findById(3l).orElse(null);

            System.out.println("************************************************");
            System.out.println(account.getId());
            System.out.println(account.getBalance());
            System.out.println(account.getCurrency());
            System.out.println(account.getCustomer().getName()  );
            System.out.println("class name : " + account.getClass().getSimpleName());
            if(account instanceof CurrentAccount){
                System.out.println("Draft => "+((CurrentAccount) account).getOverdraft());
            }else if(account instanceof SavingAccount){
                System.out.println("Rate => "+((SavingAccount)account).getInterestRate());
            }

            account.getOperations().forEach(acc ->{
                    System.out.println(acc.getId());
                    System.out.println(acc.getType());
                    System.out.println(acc.getBankAccount());
                    System.out.println(acc.getDate());
            });


        };
    }

}
