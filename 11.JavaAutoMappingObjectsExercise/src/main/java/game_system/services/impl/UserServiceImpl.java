package game_system.services.impl;

import game_system.models.User;
import game_system.repositories.UserRepository;
import game_system.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.validation.Validator;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    private void getValidator(){
    }

    @Override
    public void registerUser(User user, String confirmPassword) {
        if(!user.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match!");
        }
       if(!doesEmailExists(user.getEmail())) {
           this.userRepository.save(user);
       }else {

           System.out.println("Dublicate entry, cannot add user with such email, please enter a different one!");
       }

    }
    private boolean doesEmailExists(String email){
        return userRepository.findByEmail(email) !=null;
    }
}
