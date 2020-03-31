package org.mlooser.learn.spring.worldgdp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
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
}
