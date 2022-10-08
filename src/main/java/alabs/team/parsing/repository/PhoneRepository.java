package alabs.team.parsing.repository;

import alabs.team.parsing.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}