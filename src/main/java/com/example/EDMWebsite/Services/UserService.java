package com.example.EDMWebsite.Services;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Repositories.CalfRepository;
import com.example.EDMWebsite.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private CalfRepository calfRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,CalfRepository calfRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.calfRepository = calfRepository;
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
        calf.setDateOfBirth(new Date());
        userFound.setCalves(calf);
        calf.setUser(userFound);
        userRepository.save(userFound);
        return calf.getTagNumber();
    }
    //TODO: HANDLE ALL OPTIONAL ERROR
    //TODO: NEED TO PROTECT AGAINST RANDOM DELETIONS
    public String deleteCalfById(Long calfId, Authentication auth){
        String username = auth.getName();

        User foundUser = this.userRepository.findByUsername(username).get();
        Calf foundCalf = this.calfRepository.findById(calfId).get();
       // foundUser.getCalves().stream().filter(calf -> calf.getId() == calfId)

        //foundUser.removeCalf(foundCalf);
        this.calfRepository.delete(foundCalf);

       // this.userRepository.save(foundUser);


        return "THIS NEEDS TO BE UPDATED";
    }


}
