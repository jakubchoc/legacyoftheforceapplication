package com.game.legacyoftheforceapplication.dto;

import lombok.Data;

@Data
public class CharacterDTO {
    private Long id;
    private String name;
    private RaceDTO race;
    private String profilePhoto;
    private InventoryDTO inventoryDTO;
}
