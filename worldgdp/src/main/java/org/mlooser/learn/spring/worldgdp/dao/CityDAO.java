package org.mlooser.learn.spring.worldgdp.dao;

import org.mlooser.learn.spring.worldgdp.mappers.CityRowMapper;
import org.mlooser.learn.spring.worldgdp.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityDAO {
    private static final Integer PAGE_SIZE = 10;

    @Autowired
    private NamedParameterJdbcTemplate namedParamJdbcTemplate;

    public List<City> getCities(String countryCode) {
        return getCities(countryCode, null);
    }

    public List<City> getCities(String countryCode, Integer pageNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", countryCode);
        if (pageNo != null) {
            Integer offset = (pageNo - 1) * PAGE_SIZE;
            params.put("offset", offset);
            params.put("size", PAGE_SIZE);
        }

        String sql = "SELECT "
                + " id, name, countrycode country_code, district, population "
                + " FROM city WHERE countrycode = :code"
                + " ORDER BY Population DESC"
                + ((pageNo != null) ? " LIMIT :offset , :size " : "");

        System.out.println(sql);

        return namedParamJdbcTemplate.query(
                sql, params, new CityRowMapper());
    }

    public City getCityDetail(Long cityId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", cityId);
        String sql = "SELECT id, "
                + " name, countrycode country_code, "
                + " district, population  "
                + "	FROM city WHERE id = :id";

        return namedParamJdbcTemplate.queryForObject(
                sql, params, new CityRowMapper());
    }

    public Long addCity(City city) {

        SqlParameterSource paramSource = new MapSqlParameterSource(getMapForCity(city));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO city("
                + " name, countrycode, "
                + " district, population) "
                + " VALUES (:name, :countrycode, "
                + " :district, :population )";

        System.out.println(sql);

        namedParamJdbcTemplate.update(sql, paramSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void deleteCity(Long cityId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", cityId);
        System.out.println("Deleting : {}" + params);
        namedParamJdbcTemplate.update("DELETE FROM city WHERE id = :id", params);
    }

    private Map<String, Object> getMapForCity(City city) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", city.getName());
        map.put("countrycode", city.getCountryCode());
        map.put("district", city.getDistrict());
        map.put("population", city.getPopulation());
        return map;
    }
}
