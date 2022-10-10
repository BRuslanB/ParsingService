package alabs.team.parsing.services;

import alabs.team.parsing.dto.OfficeDto;
import alabs.team.parsing.model.Office;

import java.util.List;

public interface OfficeService {
    Office getOffice(Long id);
    List<OfficeDto> getAllOfficeDto(String sort);
    List<OfficeDto> setOfficeDto(List<OfficeDto> officeDtoList);
    void createOfficeDto(OfficeDto officeDto);
    void updateOfficeDto(Office office, OfficeDto officeDto);
}