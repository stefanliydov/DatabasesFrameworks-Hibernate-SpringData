package services.account;

import models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, long id) {
        if (!accountRepository.exists(id)) {
            throw new IllegalArgumentException("There is no account with provided id!");
        }
        Account account = accountRepository.findOne(id);

        BigDecimal newBalance = account.getBalance().subtract(money);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal money, long id) {
        Account account = accountRepository.findOne(id);
        if (account == null) {
            throw new IllegalArgumentException("There is no account with provided id!");
        }
        if (account.getUser() == null) {
            throw new IllegalArgumentException("There is no user to the provided id!");
        }

        if (BigDecimal.ZERO.compareTo(money)>0){
            throw new IllegalArgumentException("Money cannot be negative!");
        }

        BigDecimal newBalance = account.getBalance().add(money);
        account.setBalance(newBalance);
        accountRepository.save(account);

    }
}
