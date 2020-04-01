package org.mlooser.learn.spring.worldgdp.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mlooser.learn.spring.worldgdp.dao.CountryDAO;
import org.mlooser.learn.spring.worldgdp.model.City;
import org.mlooser.learn.spring.worldgdp.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringJUnitConfig( classes = {
        TestDBConfiguration.class,
        CountryDAO.class
})
public class CountryDAOTest {

    private static final String AFGHANISTAN_COUNTRY_CODE = "AFG";

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void getCountriesTest(){
        List<Country> countries = countryDAO.getCountries(new HashMap<String, Object>());
        assertThat(countries).hasSize(20);
    }

    @Test
    public void getCountryDetailTest(){
        Country country = countryDAO.getCountryDetail(AFGHANISTAN_COUNTRY_CODE);
        assertThat(country.getName()).isEqualTo("Afghanistan");
        assertThat(country.getCapital().getName()).isEqualTo("Kabul");
    }

    @Test
    public void updateCountryDetailTest(){
        Country country = new Country();
        country.setCode(AFGHANISTAN_COUNTRY_CODE);
        country.setName("Afghanistan");
        country.setContinent("Europe");

        City capital = new City();
        capital.setId(1l);
        country.setCapital(capital);

        countryDAO.updateCountryDetail(AFGHANISTAN_COUNTRY_CODE,country);

        Country foundCountry = countryDAO.getCountryDetail(AFGHANISTAN_COUNTRY_CODE);
        assertThat(foundCountry.getContinent()).isEqualTo("Europe");
    }
}
