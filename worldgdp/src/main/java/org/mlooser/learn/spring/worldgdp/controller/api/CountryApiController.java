package org.mlooser.learn.spring.worldgdp.controller.api;

import org.mlooser.learn.spring.worldgdp.dao.CountryDAO;
import org.mlooser.learn.spring.worldgdp.external.WorldBankApiClient;
import org.mlooser.learn.spring.worldgdp.model.Country;
import org.mlooser.learn.spring.worldgdp.model.CountryGDP;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worldgdp/api/countries")
public class CountryApiController {

    private final CountryDAO countryDAO;
    private final WorldBankApiClient worldBankApiClient;

    public CountryApiController(CountryDAO countryDAO, WorldBankApiClient worldBankApiClient) {
        this.countryDAO = countryDAO;
        this.worldBankApiClient = worldBankApiClient;
    }

    @GetMapping
    public ResponseEntity<?> getCountries(
            @RequestParam(name = "search", required = false) String searchTerm,
            @RequestParam(name = "continent", required = false) String continent,
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "pageNo", required = false) Integer pageNo) {

        Map<String, Object> params = new HashMap<>();
        params.put("search", searchTerm);
        params.put("continent", continent);
        params.put("region", region);
        if (pageNo != null) {
            params.put(CountryDAO.PAGE_NO_KEY, pageNo);
        }

        List<Country> countries = countryDAO.getCountries(params);
        int countriesCount = countryDAO.getCountriesCount(params);

        Map<String, Object> response = new HashMap<>();
        response.put("list", countries);
        response.put("count", countriesCount);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/{countryCode}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateCountry(
            @PathVariable String countryCode,
            @Valid @RequestBody Country country) {
        countryDAO.updateCountryDetail(countryCode, country);
        Country countryFromDb = countryDAO.getCountryDetail(countryCode);
        return ResponseEntity.ok(countryFromDb);
    }

    @GetMapping("/{countryCode}/gdp")
    public ResponseEntity<?> getGDP(@PathVariable String countryCode) throws Exception {
        List<CountryGDP> countryGDPs = worldBankApiClient.getGDP(countryCode);
        return ResponseEntity.ok(countryGDPs);
    }
}
