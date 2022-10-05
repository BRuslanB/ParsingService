package alabs.team.ParsingService.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Data {
    @JsonProperty("USD")
    public ArrayList<Double> usd;
    @JsonProperty("EUR")
    public ArrayList<Double> eur;
    @JsonProperty("RUB")
    public ArrayList<Double> rub;
}