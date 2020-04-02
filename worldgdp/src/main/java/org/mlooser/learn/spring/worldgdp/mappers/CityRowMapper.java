package org.mlooser.learn.spring.worldgdp.mappers;

import org.mlooser.learn.spring.worldgdp.model.City;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CityRowMapper implements RowMapper<City> {

    public City mapRow(ResultSet resultSet, int i) throws SQLException {
        City city = new City();

        city.setCountryCode(resultSet.getString("country_code"));
        city.setDistrict(resultSet.getString("district"));
        city.setId(resultSet.getLong("id"));
        city.setName(resultSet.getString("name"));
        city.setPopulation(resultSet.getLong("population"));

        return city;
    }
}
