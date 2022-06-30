package com.example.EDMWebsite.Repsitories;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.Sex;
import com.example.EDMWebsite.Models.User;
import com.example.EDMWebsite.Repositories.CalfRepository;
import com.example.EDMWebsite.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestCalfUserRepository {

    @Autowired
    CalfRepository calfUnderTest;

    @Autowired
    UserRepository userUnderTest;


}
