package org.mlooser.learn.spring.worldgdp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
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
}
