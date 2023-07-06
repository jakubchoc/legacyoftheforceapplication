package com.game.legacyoftheforceapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CharacterDTO {

    @JsonProperty("_id")
    private Long id;
    @NotBlank
    private String name;
    private RaceDTO race;
    private String profilePhoto;
}
