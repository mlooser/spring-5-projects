package org.mlooser.learn.spring.worldgdp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CountryLanguage {
    private Country country;

    @NotNull
    @Size(min = 3, max = 3)
    private String countryCode;

    @NotNull
    @Size(max = 30)
    private String language;

    @NotNull
    @Size(min = 1, max = 1)
    private String isOfficial;

    @NotNull
    private Double percentage;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLanguage that = (CountryLanguage) o;
        return Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(language, that.language) &&
                Objects.equals(isOfficial, that.isOfficial) &&
                Objects.equals(percentage, that.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language, isOfficial, percentage);
    }
}
