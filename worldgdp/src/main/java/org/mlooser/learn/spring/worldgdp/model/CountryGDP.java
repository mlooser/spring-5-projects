package org.mlooser.learn.spring.worldgdp.model;

import java.util.Objects;

public class CountryGDP {
    private Short year;
    private Double value;

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

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
