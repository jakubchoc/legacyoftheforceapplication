package com.game.legacyoftheforceapplication.equipment;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.legacyoftheforceapplication.dto.CharacterDTO;
import com.game.legacyoftheforceapplication.utility.LegacyOfTheForceAPIConnection;

@Service
@AllArgsConstructor
public class CharacterAPI {

    private static final Logger logger = LogManager.getLogger(CharacterAPI.class);
    private final LegacyOfTheForceAPIConnection connection;

    public CharacterDTO getCharacterProfile(Long characterId) {
        String response = connection.get(characterId);
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterDTO DTO;
        try {
            DTO = objectMapper.readValue(response, CharacterDTO.class);
        } catch (JsonProcessingException e) {
            logger.error("Could not parse response ito JSON object");
            throw new RuntimeException(e);
        }
        return DTO;
    }

}
