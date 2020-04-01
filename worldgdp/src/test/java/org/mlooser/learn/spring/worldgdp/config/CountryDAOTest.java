package org.mlooser.learn.spring.worldgdp.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mlooser.learn.spring.worldgdp.dao.CountryDAO;
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
    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void getCountriesTest(){
        List<Country> countries = countryDAO.getCountries(new HashMap<String, Object>());
        assertThat(countries).hasSize(20);
    }
}
