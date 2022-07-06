package com.example.EDMWebsite.Repositories;


import com.example.EDMWebsite.Models.Calf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CalfRepository extends JpaRepository<Calf , Long> {

    @Query(value = "SELECT * FROM calf WHERE calf.tag_number = ?1",nativeQuery = true)
    public Optional<Calf> getCalfByTagNumber(String tagNumber);

    @Query(value = "SELECT * FROM calf",nativeQuery = true)
    public List<Calf> getAllCalves();

    @Query(value = "SELECT * FROM calf WHERE calf.user_id = ?1",nativeQuery = true)
    public List<Calf> getAllCalvesByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM calf WHERE calf.id = ?1",nativeQuery = true)
    public int deleteCalfById(Long id);

    @Modifying
    @Query(value = "DELETE FROM calf WHERE calf.id = ?1 AND calf.user_id = ?2 ",nativeQuery = true)
    public int deleteCalfByUserIdAndCalfId(Long calfId, Long userId);
}
