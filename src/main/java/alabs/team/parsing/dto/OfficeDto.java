package alabs.team.parsing.dto;

import alabs.team.parsing.model.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OfficeDto {
    public Long id;
    public String city;
    public String name;
    public String mainaddress;
    public ArrayList<String> phones;
    public Data data;
}