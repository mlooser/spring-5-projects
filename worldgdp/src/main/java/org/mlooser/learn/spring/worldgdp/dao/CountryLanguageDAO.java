package org.mlooser.learn.spring.worldgdp.dao;

import org.mlooser.learn.spring.worldgdp.mappers.CountryLanguageRowMapper;
import org.mlooser.learn.spring.worldgdp.model.CountryLanguage;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CountryLanguageDAO {

    private static final Integer PAGE_SIZE = 10;

    private final NamedParameterJdbcTemplate namedParamJdbcTemplate;
    private final CountryLanguageRowMapper countryLanguageRowMapper;

    public CountryLanguageDAO(NamedParameterJdbcTemplate namedParamJdbcTemplate, CountryLanguageRowMapper countryLanguageRowMapper) {
        this.namedParamJdbcTemplate = namedParamJdbcTemplate;
        this.countryLanguageRowMapper = countryLanguageRowMapper;
    }

    public List<CountryLanguage> getLanguages(String countryCode, Integer pageNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", countryCode);

        Integer offset = (pageNo - 1) * PAGE_SIZE;
        params.put("offset", offset);
        params.put("size", PAGE_SIZE);

        String sql = "SELECT * FROM countrylanguage"
                + " WHERE countrycode = :code"
                + " ORDER BY percentage DESC "
                + " LIMIT :size OFFSET :offset ";

        return namedParamJdbcTemplate.query(
                sql, params, countryLanguageRowMapper);
    }

    public void addLanguage(CountryLanguage cl) {
        String sql = "INSERT INTO countrylanguage ( "
                + " countrycode, language, isofficial, percentage ) "
                + " VALUES ( :country_code, :language, "
                + " :is_official, :percentage ) ";

        namedParamJdbcTemplate.update(
                sql, getAsMap(cl));
    }

    public boolean ifLanguageExists(String countryCode, String language) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", countryCode);
        params.put("lang", language);

        String sql = "SELECT COUNT(*) FROM countrylanguage"
                + " WHERE countrycode = :code "
                + " AND language = :lang";

        Integer langCount = namedParamJdbcTemplate.queryForObject(
                sql, params, Integer.class);
        return langCount > 0;
    }

    public void deleteLanguage(String countryCode, String language) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", countryCode);
        params.put("lang", language);

        String sql = "DELETE FROM countrylanguage "
                + " WHERE countrycode = :code AND "
                + " language = :lang ";

        namedParamJdbcTemplate.update(sql, params);
    }

    private Map<String, Object> getAsMap(CountryLanguage cl) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("country_code", cl.getCountryCode());
        map.put("language", cl.getLanguage());
        map.put("is_official", cl.getIsOfficial());
        map.put("percentage", cl.getPercentage());
        return map;
    }

}
