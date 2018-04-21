package application;

import models.Account;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import services.account.AccountServiceImpl;
import services.user.UserServiceImpl;

import java.math.BigDecimal;

@SpringBootApplication
@ComponentScan({"services.user","services.account"})
@EntityScan({"models"})
@EnableJpaRepositories("repositories")
@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... strings) throws Exception {
        User user = new User();

        user.setAge(20);
        user.setUsername("mimok_stz2");
        Account account = new Account();
        account.setBalance(new BigDecimal(200));
        account.setUser(user);
        user.addAccount(account);

        userService.registerUser(user);


        }
}
