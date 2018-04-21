package game_system.services.api;

import game_system.models.User;


public interface UserService {

    void registerUser(User user, String confirmPassword);
}
