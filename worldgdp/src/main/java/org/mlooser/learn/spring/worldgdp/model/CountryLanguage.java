package org.mlooser.learn.spring.worldgdp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
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
    @Size(min = 1, max = 1)
    private String isOfficial;

    @NotNull
    private Double percentage;
}
