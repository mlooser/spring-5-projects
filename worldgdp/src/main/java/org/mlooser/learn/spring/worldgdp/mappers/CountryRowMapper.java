package org.mlooser.learn.spring.worldgdp.mappers;

import org.mlooser.learn.spring.worldgdp.model.City;
import org.mlooser.learn.spring.worldgdp.model.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();

        country.setCode(resultSet.getString("code"));
        country.setName(resultSet.getString("name"));
        country.setContinent(resultSet.getString("continent"));

        if(Long.valueOf(resultSet.getLong("capital"))!=null){
            City city = new City();
            city.setId(resultSet.getLong("capital"));
            city.setName(resultSet.getString("capital_name"));
            city.setCountry(country);
            country.setCapital(city);
        }

        return country;
    }
}
