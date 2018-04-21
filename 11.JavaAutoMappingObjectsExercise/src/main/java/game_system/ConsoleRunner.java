package game_system;

import game_system.models.User;
import game_system.services.api.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;


    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user = new User();

        //||Ivan1|Ivan1|Ivan
        user.setEmail("ivan@ivan.com");
        user.setFullName("Ivan");
        user.setPassword("Ivan123");

        userService.registerUser(user,"Ivan123");

    }
}
