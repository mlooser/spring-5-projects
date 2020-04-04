package org.mlooser.learn.spring.worldgdp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CountryGDP {
    private Short year;
    private Double value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryGDP that = (CountryGDP) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, value);
    }
}
