package alabs.team.ParsingService.repository;

import alabs.team.ParsingService.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OfficeRepository extends JpaRepository<Office, Long> {
    @Query("DELETE FROM Office c WHERE c.id = :office_id")
    int deleteByOfficeId(Long office_id);

    @Query(value = "delete from t_offices", nativeQuery = true)
    int deleteByOfficeAll();
}