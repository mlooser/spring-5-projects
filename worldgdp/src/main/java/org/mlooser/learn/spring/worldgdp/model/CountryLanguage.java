package org.mlooser.learn.spring.worldgdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
public class CountryLanguage {
    private Country country;

    @NotNull
    @Size(min = 3, max = 3)
    private String countryCode;

    @NotNull
    @Size(max = 30)
    private String language;

    @NotNull
    private Boolean isOfficial;

    @NotNull
    private Double percentage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLanguage that = (CountryLanguage) o;
        return Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(language, that.language) &&
                Objects.equals(isOfficial, that.isOfficial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language, isOfficial);
    }
}
