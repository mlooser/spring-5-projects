package org.mlooser.learn.spring.worldgdp.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LookupDAO {

    private static final String GET_CONTINENTS = "SELECT DISTINCT continent FROM country ORDER BY continent";
    private static final String GET_REGIONS = "SELECT DISTINCT region FROM country ORDER BY region";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LookupDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<String> getContinents() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForList(
                GET_CONTINENTS,
                params, String.class);
    }

    public List<String> getRegions() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForList(
                GET_REGIONS,
                params, String.class);
    }
}
