package alabs.team.ParsingService.services;

import alabs.team.ParsingService.dto.OfficeDto;

import java.util.List;

public interface OfficeService {
    List<OfficeDto> setOfficeDto(List<OfficeDto> officeDtoList);

    void createOfficeDto(OfficeDto officeDto);
    void updateOfficeDto(OfficeDto officeDto);
}