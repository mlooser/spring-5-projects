package org.mlooser.learn.spring.worldgdp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mlooser.learn.spring.worldgdp.config.TestDBConfiguration;
import org.mlooser.learn.spring.worldgdp.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = {
        TestDBConfiguration.class,
        CityDAO.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CityDAOTest {

    @Autowired
    private CityDAO cityDAO;

    @Test
    public void getCitiesTest(){
        List<City> cities = cityDAO.getCities("AFG");
        assertThat(cities)
                .hasSize(4);
    }

    @Test
    public void getCityDetailTest(){
        City city = cityDAO.getCityDetail(1l);
        assertThat(city.getName())
                .isEqualTo("Kabul");
    }

    @Test
    public void addDeleteCityTest(){
        City newCity = new City();
        newCity.setName("xxx");
        newCity.setPopulation(1l);
        newCity.setDistrict("yyy");
        newCity.setCountryCode("zzz");

        Long addedCityId = cityDAO.addCity(newCity);

        newCity.setId(addedCityId);
        City foundCity = cityDAO.getCityDetail(addedCityId);

        assertThat(foundCity)
                .isEqualTo(newCity);
    }
}
