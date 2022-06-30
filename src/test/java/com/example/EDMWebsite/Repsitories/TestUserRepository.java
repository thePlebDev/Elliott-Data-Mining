package com.example.EDMWebsite.Repsitories;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.Sex;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestUserRepository {

    @Autowired
    private UserRepository underTest;



    @Test
    public void findByUsernameTest(){
        //GIVEN
        String EXPECTED_USERNAME = "BOB";
        User user = new User(EXPECTED_USERNAME,"fdsa4","email@email.com");

        //WHEN
        underTest.save(user);
        User foundUser = underTest.findByUsername(EXPECTED_USERNAME).get();

        //THEN
        assertThat(foundUser.getUsername()).isEqualTo(EXPECTED_USERNAME);
    }

    //TODO: need to handle the org.hibernate.exception.ConstraintViolationException (duplicate username)
    @Test
    public void saveUserWithDuplicateUsername(){
        //GIVEN
        String EXPECTED_USERNAME = "BOB";
        User user = new User(EXPECTED_USERNAME,"fdsa4","email@email.com");
        User user2 = new User(EXPECTED_USERNAME,"fdsa4","email@email.com");

        //WHEN
        underTest.save(user);
        underTest.save(user2);
        User foundUser = underTest.findByUsername(EXPECTED_USERNAME).get();

        //THEN
        assertThat(foundUser.getUsername()).isEqualTo(EXPECTED_USERNAME);

    }

    @Test//TO GET ALL CALVES RELATED TO THE USER
    public void gettingAllCalvesToUsers(){
        //GIVEN
        String EXPECTED_USERNAME = "BOB";

        User user = new User(EXPECTED_USERNAME,"fdsa4","email@email.com");
        Calf calf = new Calf("bob234","another one in the tank",444, Sex.BULL);
        Calf calf2 = new Calf("fdsa84","another one in the tank",444, Sex.BULL);


        //WHEN
        user.setCalves(calf);
        user.setCalves(calf2);
        underTest.save(user);
        User foundUser = underTest.findByUsername(EXPECTED_USERNAME).get();
        List<Calf> calfList = foundUser.getCalves();

        //THEN
        assertThat(calfList.size()).isEqualTo(2);
    }


}
