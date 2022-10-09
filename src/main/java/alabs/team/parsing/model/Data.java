package alabs.team.parsing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class Data {
    @JsonProperty("USD")
    public ArrayList<Double> usd;
    @JsonProperty("EUR")
    public ArrayList<Double> eur;
    @JsonProperty("RUB")
    public ArrayList<Double> rub;
}