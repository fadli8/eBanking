package ma.banking.ebanking.controllers;

import ma.banking.ebanking.entities.BankAccount;
import ma.banking.ebanking.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/accounts")
public class BankAccountController{

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/liste")
    public List<BankAccount> bankAccounts(){
        return bankAccountService.bankAccounts();
    }

    @PostMapping(path = "/save")
    public void save(){

    }

}
