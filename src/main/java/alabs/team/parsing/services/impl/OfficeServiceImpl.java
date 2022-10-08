package alabs.team.parsing.services.impl;

import alabs.team.parsing.dto.OfficeDto;
import alabs.team.parsing.model.Currency;
import alabs.team.parsing.model.Office;
import alabs.team.parsing.model.Phone;
import alabs.team.parsing.repository.OfficeRepository;
import alabs.team.parsing.services.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;

    public Office getOffice(Long id){
        return officeRepository.findById(id).orElse(null);
    }

    public List<OfficeDto> setOfficeDto(List<OfficeDto> officeDtoList) {
        // Check exist
        for (OfficeDto officeDto : officeDtoList) {
            Office officeCheck = getOffice(officeDto.getId());
            if (officeCheck != null) {
                updateOfficeDto(officeDto);
            } else {
                createOfficeDto(officeDto);
            }
        }
        return officeDtoList;
    }

    @Override
    public void createOfficeDto(OfficeDto officeDto) {
        // Main data
        Office office = new Office();
        office.setId(officeDto.getId());
        office.setName(officeDto.getName());
        office.setCity(officeDto.getCity());
        office.setAddress(officeDto.getMainaddress());
        office.setCity(officeDto.getCity());
        // Phone List
        Set<Phone> phoneSet = new HashSet<Phone>();
        for (String phoneDto : officeDto.getPhones()) {
            phoneSet.add(new Phone(null, phoneDto, office));
        }
        office.setPhoneSet(phoneSet);
        // Currency data
        Set<Currency> currencySet = new HashSet<Currency>();
        currencySet.add(new Currency(null, officeDto.getData().usd.get(0), officeDto.getData().usd.get(1),
                                      officeDto.getData().eur.get(0), officeDto.getData().eur.get(1),
                                      officeDto.getData().rub.get(0), officeDto.getData().rub.get(1),
                                      Timestamp.valueOf(LocalDateTime.now()), office));
        office.setCurrencySet(currencySet);
        // Save all data
        officeRepository.save(office);
    }

    @Override
    @Transactional
    public void updateOfficeDto(OfficeDto officeDto) {
        // Main data
        Office office = officeRepository.findById(officeDto.getId()).orElseThrow();
        office.setName(officeDto.getName());
        office.setCity(officeDto.getCity());
        office.setAddress(officeDto.getMainaddress());
        office.setCity(officeDto.getCity());
        // Phone List
        for (String phoneDto : officeDto.getPhones()) {
            Optional<Phone> phone = office.getPhoneSet().stream().filter(a -> a.getPhone().equals(phoneDto)).findFirst();
            if (phone.isEmpty()) {
                office.getPhoneSet().add(new Phone(null,phoneDto,office));
            }
        }
        Set<Phone> phoneSet = office.getPhoneSet().stream().collect(Collectors.toSet());
        phoneSet.removeIf(a->!officeDto.getPhones().contains(a.getPhone()));
        office.getPhoneSet().clear();
        office.getPhoneSet().addAll(phoneSet);
        // Currency data
        Currency currency = new Currency();
        ArrayList<Currency> currencyList = new ArrayList<>();
        currency.setOffice(office);
        if (officeDto.getData() != null){
            currency.setUsd_purchase(officeDto.getData().usd.get(0));
            currency.setUsd_sale(officeDto.getData().usd.get(1));
            currency.setEur_purchase(officeDto.getData().eur.get(0));
            currency.setEur_sale(officeDto.getData().eur.get(1));
            currency.setRub_purchase(officeDto.getData().rub.get(0));
            currency.setRub_sale(officeDto.getData().rub.get(1));
        }
        currency.setTime_update(Timestamp.valueOf(LocalDateTime.now()));
        currencyList.add(currency);
        office.getCurrencySet().clear();
        office.getCurrencySet().addAll(currencyList);
        // Save all data
        officeRepository.save(office);
    }
}