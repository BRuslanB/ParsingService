package alabs.team.ParsingService.rest;

import alabs.team.ParsingService.dto.OfficeDto;
import alabs.team.ParsingService.services.impl.OfficeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeServiceImpl officeService;

    @PostMapping
    public List<OfficeDto> setOfficeDto(@RequestBody List<OfficeDto> officeDtoList){
        return officeService.setOfficeDto(officeDtoList);
    }
}