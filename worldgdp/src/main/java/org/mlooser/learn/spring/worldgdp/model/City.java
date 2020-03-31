package org.mlooser.learn.spring.worldgdp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class City {
    private Long id;
    private String name;
    private Country country;
    private String district;
    private Long population;
}
