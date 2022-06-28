package com.example.EDMWebsite.Services;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Repositories.CalfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalfService {

    CalfRepository calfRepository;

    @Autowired
    public CalfService(CalfRepository calfRepository){
        this.calfRepository = calfRepository;
    }


    public List<Calf> getAllCalves(){
        return this.calfRepository.findAll();
    }

    public Calf save(Calf calf){
        return this.calfRepository.save(calf);
    }

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

}
