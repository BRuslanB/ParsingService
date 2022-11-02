package alabs.team.parsing.services.impl;

import alabs.team.parsing.dto.OfficeDto;
import alabs.team.parsing.model.Currency;
import alabs.team.parsing.model.Data;
import alabs.team.parsing.model.Office;
import alabs.team.parsing.model.Phone;
import alabs.team.parsing.repository.OfficeRepository;
import alabs.team.parsing.services.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;

    @Override
    public Office getOffice(Long id){
        return officeRepository.findById(id).orElse(null);
    }

    @Override
    public List<OfficeDto> getAllOfficeDto(String sort) {
        List<Office> officeList;
        // Parameter sort
        switch (sort) {
            case ("asc") -> officeList = officeRepository.findAllOrderByDateAsc();
            case ("desc") -> officeList = officeRepository.findAllOrderByDateDesc();
            default -> officeList = officeRepository.findAll();
        }
        List<OfficeDto> officeDtoList = new ArrayList<>();
        OfficeDto officeDto;
        for (Office office : officeList) {
            officeDto = new OfficeDto();
            officeDto.setId(office.getId());
            officeDto.setCity(office.getCity());
            officeDto.setName(office.getName());
            officeDto.setMainaddress(office.getAddress());
            officeDto.setDate(office.getDate());
            // Phone list
            officeDto.setPhones(office.getPhoneSet().stream().map(Phone::getPhone).collect(Collectors.toList()));
            // Currency list
            ArrayList<Double> usd = new ArrayList<>();
            ArrayList<Double> eur = new ArrayList<>();
            ArrayList<Double> rub = new ArrayList<>();
            for (Currency currency : office.getCurrencySet()) {
                usd.add(currency.getUsd_purchase());
                usd.add(currency.getUsd_sale());
                eur.add(currency.getEur_purchase());
                eur.add(currency.getEur_sale());
                rub.add(currency.getRub_purchase());
                rub.add(currency.getRub_sale());
            }
            officeDto.setData(new Data(usd, eur, rub));
            //Add to officeDtoList
            officeDtoList.add(officeDto);
        }
        return officeDtoList;
    }

    @Override
    public List<OfficeDto> setOfficeDto(List<OfficeDto> officeDtoList) {
        // Check exist
        for (OfficeDto officeDto : officeDtoList) {
            Office officeCheck = getOffice(officeDto.getId());
            if (officeCheck != null) {
                updateOfficeDto(officeCheck, officeDto);
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
        office.setCity(officeDto.getCity());
        office.setName(officeDto.getName());
        office.setAddress(officeDto.getMainaddress());
        office.setDate(officeDto.getDate());
        office.setTime_update(Timestamp.valueOf(LocalDateTime.now()));
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
                                      officeDto.getData().rub.get(0), officeDto.getData().rub.get(1), office));
        office.setCurrencySet(currencySet);
        // Save all data to Entity
        officeRepository.save(office);
    }

    @Override
    public void updateOfficeDto(Office office, OfficeDto officeDto) {
        // Main data
        office.setCity(officeDto.getCity());
        office.setName(officeDto.getName());
        office.setAddress(officeDto.getMainaddress());
        office.setDate(officeDto.getDate());
        office.setTime_update(Timestamp.valueOf(LocalDateTime.now()));
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
        currencyList.add(currency);
        office.getCurrencySet().clear();
        office.getCurrencySet().addAll(currencyList);
        // Save all data to Entity
        officeRepository.save(office);
    }
}