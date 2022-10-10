package alabs.team.parsing.repository;

import alabs.team.parsing.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OfficeRepository extends JpaRepository<Office, Long> {
    @Query(value = "SELECT * FROM t_offices ORDER BY date", nativeQuery = true)
    List<Office> findAllOrderByDateAsc();

    @Query(value = "SELECT * FROM t_offices ORDER BY date DESC", nativeQuery = true)
    List<Office> findAllOrderByDateDesc();
}