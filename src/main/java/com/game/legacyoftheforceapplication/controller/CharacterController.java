package com.game.legacyoftheforceapplication.controller;

import com.game.legacyoftheforceapplication.equipment.CharacterAPI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CharacterController {

    private final CharacterAPI api;

    @GetMapping("/character/{characterId}")
    String getProfile(@RequestParam Long characterId, Model model) {
        api.getCharacter(characterId);
        model.addAttribute("pozdrav", "ahoj");
        return "index";
    }
}
