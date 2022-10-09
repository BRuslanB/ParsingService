package alabs.team.parsing.dto;

import alabs.team.parsing.model.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OfficeDto {
    public Long id;
    public String city;
    public String name;
    public String mainaddress;
    public Long date;
    public List<String> phones;
    public Data data;
}