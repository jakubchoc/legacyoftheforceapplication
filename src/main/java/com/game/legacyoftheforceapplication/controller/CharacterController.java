package com.game.legacyoftheforceapplication.controller;

import com.game.legacyoftheforceapplication.dto.CharacterDTO;
import com.game.legacyoftheforceapplication.equipment.CharacterAPI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class CharacterController {

    private final CharacterAPI api;

    @GetMapping("/character/{characterId}")
    String getProfile(@PathVariable Long characterId, Model model) {
        CharacterDTO character = api.getCharacterProfile(characterId);
        model.addAttribute("character", character);
        return "index";
    }
}
