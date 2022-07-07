package com.example.EDMWebsite.Services;

import com.example.EDMWebsite.Models.Calf;
import com.example.EDMWebsite.Models.EnumModels.CalfStatus;
import com.example.EDMWebsite.Models.EnumModels.Sex;
import com.example.EDMWebsite.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {




    @Test
    public void testingFilter(){
        //GIVEN
        Calf calf = new Calf("bob234","another one in the tank",444, Sex.BULL, CalfStatus.ALIVE);
        Calf calf2 = new Calf("fdsa84","another one in the tank",444, Sex.BULL, CalfStatus.ALIVE);
        calf.setId(1l);
        calf2.setId(2l);
        List<Calf> calfList = new ArrayList<>();
        calfList.add(calf);
        calfList.add(calf2);

        //WHEN
        filter(calfList,1l);

        //THEN
        assertThat(calfList.size()).isEqualTo(2);
    }

    public void filter(List<Calf> calfList,Long id) {
        for (int k = 0; k < calfList.size(); k++) {
            if (calfList.get(k).getId() == id) {
                calfList.remove(k);
            }
        }
    }
}
