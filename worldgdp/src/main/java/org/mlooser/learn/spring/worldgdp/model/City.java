package org.mlooser.learn.spring.worldgdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
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
