package ma.banking.ebanking.services;


import ma.banking.ebanking.entities.BankAccount;
import ma.banking.ebanking.entities.CurrentAccount;
import ma.banking.ebanking.entities.SavingAccount;
import ma.banking.ebanking.repositories.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional
public class BankAccountService {

    @Autowired
    private BankAccountRepo bankAccountRepo;

    public void consulting(){
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

    }


    public List<BankAccount> bankAccounts(){
        return bankAccountRepo.findAll();
    }

}
