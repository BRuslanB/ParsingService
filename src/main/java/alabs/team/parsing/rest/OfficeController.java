package alabs.team.parsing.rest;

import alabs.team.parsing.dto.OfficeDto;
import alabs.team.parsing.services.impl.OfficeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeServiceImpl officeService;

    @GetMapping
    public List<OfficeDto> getAllOfficeDTO(){
        return officeService.getAllOfficeDto();
    }

    @PostMapping
    public List<OfficeDto> setOfficeDto(@RequestBody List<OfficeDto> officeDtoList){
        return officeService.setOfficeDto(officeDtoList);
    }
}