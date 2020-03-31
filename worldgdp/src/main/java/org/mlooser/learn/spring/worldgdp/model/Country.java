package org.mlooser.learn.spring.worldgdp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Country {
    private String code;
    private String name;
    private String continent;
    private City capital;
}
