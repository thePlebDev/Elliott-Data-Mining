package com.example.EDMWebsite.Services;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Repositories.CalfRepository;
import com.example.EDMWebsite.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalfService {

    CalfRepository calfRepository;

    UserRepository userRepository;

    @Autowired
    public CalfService(CalfRepository calfRepository, UserRepository userRepository){
        this.calfRepository = calfRepository;
        this.userRepository = userRepository;

    }


    public List<Calf> getAllCalves(){
        return this.calfRepository.findAll();
    }

//    public Calf save(Calf calf,String username){
//        User user= userRepository.findByUsername(username).get();
//        user.setCalves(calf);
//        return this.calfRepository.save(calf);
//    }

    public Calf updateCalf(Calf calf){
        Calf returnedCalf = this.calfRepository.getReferenceById(calf.getId());

        returnedCalf.setTagNumber(calf.getTagNumber());
        returnedCalf.setSex(calf.getSex());
        returnedCalf.setDetails(calf.getDetails());
        returnedCalf.setWeight(calf.getWeight());

        return this.calfRepository.save(returnedCalf);

    }

    public String deleteCalf(Calf calf){
        this.calfRepository.delete(calf);
        return calf.getTagNumber();

    }
    public Calf getCalfById(Long id){
        Calf foundCalf = this.calfRepository.findById(id).orElseThrow(()-> new BadCredentialsException("Improper ID"));
        return foundCalf;
    }

}
