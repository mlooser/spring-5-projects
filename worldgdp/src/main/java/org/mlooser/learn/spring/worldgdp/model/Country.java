package org.mlooser.learn.spring.worldgdp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Country {
    @NotNull
    @Size(min = 3, max = 3)
    private String code;

    @NotNull
    @Size(max = 52)
    private String name;

    @NotNull
    private String continent;

    private City capital;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(code, country.code) &&
                Objects.equals(name, country.name) &&
                Objects.equals(continent, country.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, continent);
    }
}
