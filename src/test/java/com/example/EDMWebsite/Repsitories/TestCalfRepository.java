package com.example.EDMWebsite.Repsitories;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.Sex;


import com.example.EDMWebsite.Repositories.CalfRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestCalfRepository {

    @Autowired
    CalfRepository underTest;


    @Test
    public void findByUsernameTest(){
       //GIVEN
        String EXPECTED_TAG_NUMBER = "asdfas4";
        Calf calf = new Calf(EXPECTED_TAG_NUMBER,"another one in the tank",444, Sex.BULL);

        //WHEN
        underTest.save(calf);
       Calf returnedCalf = underTest.getCalfByTagNumber(EXPECTED_TAG_NUMBER).get();


        //THEN

        assertThat(returnedCalf.getSex()).isEqualTo(Sex.BULL);


    }
}
