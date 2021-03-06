package com.example.EDMWebsite.Repsitories;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.EnumModels.CalfStatus;
import com.example.EDMWebsite.Models.EnumModels.Sex;


import com.example.EDMWebsite.Repositories.CalfRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestCalfRepository {

    @Autowired
    CalfRepository underTest;


    @Test
    public void findByUsernameTest(){
       //GIVEN
        String EXPECTED_TAG_NUMBER = "asdfas4";
        Calf calf = new Calf(EXPECTED_TAG_NUMBER,"another one in the tank",444, Sex.BULL, CalfStatus.ALIVE);

        //WHEN
        underTest.save(calf);
        Calf returnedCalf = underTest.getCalfByTagNumber(EXPECTED_TAG_NUMBER).get();


        //THEN
        assertThat(returnedCalf.getSex()).isEqualTo(Sex.BULL);


    }

    @Test
    public void updateCalfTest(){
        //GIVEN
        String EXPECTED_TAG_NUMBER = "asdfas4";
        String UPDATED_TAG_NUMBER = "DSAFDS";
        Calf calf = new Calf(EXPECTED_TAG_NUMBER,"another one in the tank",444, Sex.BULL, CalfStatus.ALIVE);


        //WHEN
        underTest.save(calf);
        Calf returnedCalf = underTest.getReferenceById(1l);
        returnedCalf.setTagNumber(UPDATED_TAG_NUMBER);
        underTest.save(returnedCalf);
        Calf updatedCalf = underTest.getReferenceById(1l);


        //THEN

        assertThat(updatedCalf.getTagNumber()).isEqualTo(UPDATED_TAG_NUMBER);

    }

    @Test
    public void deleteCalfByIdTest(){

        //GIVEN
        String EXPECTED_TAG_NUMBER = "asdfas4";
        Calf calf = new Calf(EXPECTED_TAG_NUMBER,"another one in the tank",444, Sex.BULL, CalfStatus.ALIVE);


        //WHEN
        underTest.save(calf);
        int deletedCalf = underTest.deleteCalfById(1l);
        List<Calf> calfList = underTest.getAllCalves();

        //THEN
        assertThat(deletedCalf).isEqualTo(1);

    }

}
