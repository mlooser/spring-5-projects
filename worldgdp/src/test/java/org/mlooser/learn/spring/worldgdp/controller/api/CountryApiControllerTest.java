package org.mlooser.learn.spring.worldgdp.controller.api;

import org.junit.Before;
import org.junit.Test;
import org.mlooser.learn.spring.worldgdp.dao.CountryDAO;
import org.mlooser.learn.spring.worldgdp.external.WorldBankApiClient;
import org.mlooser.learn.spring.worldgdp.model.Country;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CountryApiControllerTest {

    @Mock
    private CountryDAO countryDAOMock;

    @Mock
    private WorldBankApiClient worldBankApiClientMock;

    @InjectMocks
    private CountryApiController countryApiController;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountriesTest() {
        List<Country> givenCountries = Arrays.asList(new Country());
        when(countryDAOMock.getCountries(any()))
                .thenReturn(givenCountries);

        when(countryDAOMock.getCountriesCount(any()))
                .thenReturn(1);

        ResponseEntity<?> response = countryApiController.getCountries("", "", "", 0);

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

        Integer count = (Integer) responseBody.get("count");
        List<Country> countries = (List<Country>) responseBody.get("list");

        assertEquals(givenCountries, countries);
        assertEquals(1, count.intValue());
    }
}
