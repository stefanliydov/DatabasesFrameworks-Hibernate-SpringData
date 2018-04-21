package services.user;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Long id = user.getId();
        if(id!=null){
            if(userRepository.exists(id)){
                throw new IllegalArgumentException("User already exists");
            }
        }
        String username = user.getUsername();
        if(username!=null){
            User dbUser = userRepository.findOne(id);
            if(dbUser!=null){
                throw new IllegalArgumentException();
            }
        }
            userRepository.save(user);
    }
}
