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

    @GetMapping(value = "{sort}")
    public List<OfficeDto> getAllOfficeDTO(@PathVariable(name="sort") String sort){
        return officeService.getAllOfficeDto(sort);
    }

    @PostMapping
    public List<OfficeDto> setOfficeDto(@RequestBody List<OfficeDto> officeDtoList){
        return officeService.setOfficeDto(officeDtoList);
    }
}