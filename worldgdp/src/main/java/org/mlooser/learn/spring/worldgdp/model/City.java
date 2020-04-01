package org.mlooser.learn.spring.worldgdp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class City {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 3)
    private String countryCode;

    private Country country;

    @NotNull
    private String district;

    @NotNull
    private Long population;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Country getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public Long getPopulation() {
        return population;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(countryCode, city.countryCode) &&
                Objects.equals(district, city.district) &&
                Objects.equals(population, city.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryCode, district, population);
    }
}
