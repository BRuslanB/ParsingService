package alabs.team.ParsingService.repository;

import alabs.team.ParsingService.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Query("DELETE FROM Currency c WHERE c.office.id = :office_id")
    int deleteByOfficeId(Long office_id);
}