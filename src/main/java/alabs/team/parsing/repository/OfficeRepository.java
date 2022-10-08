package alabs.team.parsing.repository;

import alabs.team.parsing.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OfficeRepository extends JpaRepository<Office, Long> {

}