package org.mlooser.learn.spring.worldgdp.dao;

import org.mlooser.learn.spring.worldgdp.mappers.CountryRowMapper;
import org.mlooser.learn.spring.worldgdp.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryDAO {
    public static final String PAGE_NO_KEY = "pageNo";

    private static final String SELECT =
            "SELECT c.code, c.name, c.continent, c.capital, cy.name capital_name " +
                    "FROM country c " +
                    "LEFT OUTER JOIN city cy ON cy.id = c.capital ";

    private static final String SEARCH_BY_NAME_WHERE =
            " AND ( LOWER( c.name ) LIKE CONCAT('%', LOWER(:name), '%') ";

    private static final String SEARCH_BY_CONTINENT_WHERE =
            " AND c.continent = :continent ";

    private static final String SEARCH_BY_REGION_WHERE =
            " AND c.region = :region ";

    private static final String PAGINATION =
            " ORDER BY c.code LIMIT :size OFFSET :offset ";

    private static final String UPDATE_BY_CODE =
            " UPDATE country SET "
                    + " name = :name, "
                    + " capital = :capital, "
                    + " continent = :continent "
                    + "WHERE Code = :code ";

    private static final Integer PAGE_SIZE = 20;

    @Autowired
    NamedParameterJdbcTemplate namedParamJdbcTemplate;

    public List<Country> getCountries(Map<String, Object> params) {
        int pageNo = 1;
        if (params.containsKey(PAGE_NO_KEY)) {
            pageNo = Integer.parseInt(params.get(PAGE_NO_KEY).toString());
        }
        Integer offset = (pageNo - 1) * PAGE_SIZE;

        params.put("size", PAGE_SIZE);
        params.put("offset", offset);

        String sql = SELECT
                + " WHERE 1 = 1 "
                + (!StringUtils.isEmpty(params.get("search")) ? SEARCH_BY_NAME_WHERE : "")
                + (!StringUtils.isEmpty(params.get("continent")) ? SEARCH_BY_CONTINENT_WHERE : "")
                + (!StringUtils.isEmpty(params.get("region")) ? SEARCH_BY_REGION_WHERE : "")
                + PAGINATION;

        System.out.println(sql);

        return namedParamJdbcTemplate.query(
                sql, params, new CountryRowMapper());
    }

    public int getCountriesCount(Map<String, Object> params) {
        String sql = "SELECT COUNT(*) FROM country c"
                + " WHERE 1 = 1 "
                + (!StringUtils.isEmpty(params.get("search")) ? SEARCH_BY_NAME_WHERE : "")
                + (!StringUtils.isEmpty(params.get("continent")) ? SEARCH_BY_CONTINENT_WHERE : "")
                + (!StringUtils.isEmpty(params.get("region")) ? SEARCH_BY_REGION_WHERE : "");

        System.out.println(sql);

        return namedParamJdbcTemplate.queryForObject(
                sql, params, Integer.class);
    }

    public Country getCountryDetail(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);

        String sql = SELECT
                + "	WHERE c.code = :code";

        System.out.println(sql);

        return namedParamJdbcTemplate.queryForObject(
                sql, params, new CountryRowMapper());
    }

    public void updateCountryDetail(String code, Country country) {
        namedParamJdbcTemplate.update(UPDATE_BY_CODE,
                getCountryAsMap(code, country));
    }

    private Map<String, Object> getCountryAsMap(String code, Country country) {
        Map<String, Object> countryMap = new HashMap<String, Object>();
        countryMap.put("name", country.getName());
        countryMap.put("capital", country.getCapital().getId());
        countryMap.put("continent", country.getContinent());
        countryMap.put("code", code);
        return countryMap;
    }
}
