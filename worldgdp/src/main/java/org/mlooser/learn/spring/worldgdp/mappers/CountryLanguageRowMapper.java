package org.mlooser.learn.spring.worldgdp.mappers;

import org.mlooser.learn.spring.worldgdp.model.CountryLanguage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryLanguageRowMapper implements RowMapper<CountryLanguage> {
    public CountryLanguage mapRow(ResultSet resultSet, int i) throws SQLException {
        CountryLanguage countryLanguage = new CountryLanguage();

        countryLanguage.setCountryCode(resultSet.getString("countrycode"));
        countryLanguage.setIsOfficial(resultSet.getBoolean("isofficial"));
        countryLanguage.setLanguage(resultSet.getString("language"));
        countryLanguage.setPercentage(resultSet.getDouble("percentage"));

        return countryLanguage;
    }
}
