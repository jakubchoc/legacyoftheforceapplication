package com.game.legacyoftheforceapplication.dto;

import lombok.Data;
import java.util.List;

@Data
public class InventoryDTO {
    private List<ItemDTO> items;
}
