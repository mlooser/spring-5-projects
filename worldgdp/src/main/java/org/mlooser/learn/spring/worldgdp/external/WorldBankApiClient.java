package org.mlooser.learn.spring.worldgdp.external;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mlooser.learn.spring.worldgdp.model.CountryGDP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorldBankApiClient {
    private static final String GDP_URL =
            "http://api.worldbank.org/countries/%s/indicators/NY.GDP.MKTP.CD?format=json&date=2008:2018";

    public List<CountryGDP> getGDP(String countryCode) throws ParseException {
        RestTemplate worldBankTestTemplate = new RestTemplate();

        ResponseEntity<String> response =
                worldBankTestTemplate.getForEntity(String.format(GDP_URL, countryCode), String.class);

        JSONParser parser = new JSONParser();
        JSONArray responseData = (JSONArray) parser.parse(response.getBody());
        JSONArray countryData = (JSONArray) responseData.get(1);

        List<CountryGDP> returnData = new ArrayList<>();

        for (int index = 0; index < countryData.size(); index++) {
            JSONObject countryDataYearWise = (JSONObject) countryData.get(index);

            String valueStr = "0";
            if (countryDataYearWise.get("value") != null) {
                valueStr = countryDataYearWise.get("value").toString();
            }
            String yearStr = countryDataYearWise.get("date").toString();
            CountryGDP gdp = new CountryGDP();
            gdp.setValue(valueStr != null ? Double.valueOf(valueStr) : null);
            gdp.setYear(Short.valueOf(yearStr));
            returnData.add(gdp);
        }

        return returnData;
    }
}
