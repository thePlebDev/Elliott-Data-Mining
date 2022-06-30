package com.example.EDMWebsite.Services;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return this.userRepository.save(user);
    }

    public List<Calf> getAllCalves(String username){
        User user = userRepository.findByUsername(username).get();
        return user.getCalves();

    }
    public String saveCalfToUser(Calf calf, String username){
        User userFound = userRepository.findByUsername(username).get();
        userFound.setCalves(calf);
        userRepository.save(userFound);
        return calf.getTagNumber();
    }
}
