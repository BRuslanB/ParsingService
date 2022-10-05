package alabs.team.ParsingService.services.impl;

import alabs.team.ParsingService.dto.OfficeDto;
import alabs.team.ParsingService.model.Currency;
import alabs.team.ParsingService.model.Office;
import alabs.team.ParsingService.model.Phone;
import alabs.team.ParsingService.repository.OfficeRepository;
import alabs.team.ParsingService.services.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;

    public List<OfficeDto> setOfficeDto(List<OfficeDto> officeDtoList) {
        // Check exist
        Office officeCheck  = new Office();
        for (OfficeDto officeDto : officeDtoList) {
            officeCheck = officeRepository.findById(officeDto.getId()).orElse(null);
            if (officeCheck != null) {
                try {
                    updateOfficeDto(officeDto);

                } catch (Exception e) {
                }
            } else {
                try {
                    createOfficeDto(officeDto);
                } catch (Exception e) {
                }
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
        Currency currency = new Currency();
        ArrayList<Currency> currencies = new ArrayList<>();
        currency.setOffice(office);
        if (officeDto.getData() != null) {
            currency.setUsd_purchase(officeDto.getData().usd.get(0));
            currency.setUsd_sale(officeDto.getData().usd.get(1));
            currency.setEur_purchase(officeDto.getData().eur.get(0));
            currency.setEur_sale(officeDto.getData().eur.get(1));
            currency.setRub_purchase(officeDto.getData().rub.get(0));
            currency.setRub_sale(officeDto.getData().rub.get(1));
        }
        currency.setTime_update(Timestamp.valueOf(LocalDateTime.now()));
        currencies.add(currency);
        Set<Currency> currencySet = new HashSet<Currency>(currencies); //добавляет только один элемент
        office.setCurrencySet(currencySet);
        // Save all data
        officeRepository.save(office);
    }

    @Override
    public void updateOfficeDto(OfficeDto officeDto) {
        // Main data
        Office office = officeRepository.findById(officeDto.getId()).orElseThrow();
        office.setName(officeDto.getName());
        office.setCity(officeDto.getCity());
        office.setAddress(officeDto.getMainaddress());
        office.setCity(officeDto.getCity());
        // Phone List
        Set<Phone> phoneSet = new HashSet<Phone>();
        for (String phoneDto : officeDto.getPhones()) {
            phoneSet.add(new Phone(null,phoneDto,office));
        }
        if (office.getPhoneSet().isEmpty()){ //проблема здесь
            office.setPhoneSet(phoneSet);
        } else {
            office.getPhoneSet().clear();
            office.getPhoneSet().addAll(phoneSet);
        }
        // Currency data
        Currency currency = new Currency();
        ArrayList<Currency> currencies = new ArrayList<>();
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
        currencies.add(currency);
        Set<Currency> currencySet = new HashSet<Currency>(currencies); //добавляет только один элемент
        if (office.getCurrencySet().isEmpty()){ //проблема здесь
            office.setCurrencySet(currencySet);
        } else {
            office.getCurrencySet().clear();
            office.getCurrencySet().addAll(currencySet);
        }
        // Save all data
        officeRepository.save(office);
    }
}