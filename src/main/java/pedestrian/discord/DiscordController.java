package pedestrian.discord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pedestrian.discord.interaction.InteractionHandler;
import pedestrian.discord.interaction.InteractionValidator;
import pedestrian.discord.interaction.model.Interaction;
import pedestrian.discord.interaction.model.InteractionResponse;

import java.util.List;

@RestController
@RequestMapping("/discord")
public final class DiscordController {

    private final InteractionValidator interactionValidator;
    private final ObjectMapper objectMapper;
    private final List<? extends InteractionHandler> interactionHandlers;

    public DiscordController(
        final InteractionValidator interactionValidator,
        final ObjectMapper objectMapper,
        final List<? extends InteractionHandler> interactionHandlers
    ) {
        this.interactionValidator = interactionValidator;
        this.objectMapper = objectMapper;
        this.interactionHandlers = interactionHandlers;
    }

    @GetMapping("/interactions")
    public InteractionResponse interactions(
        @RequestHeader("X-Signature-Ed25519")
        final String signature,
        @RequestHeader("X-Signature-Timestamp")
        final String timestamp,
        @RequestBody
        final String body
    ) throws JsonProcessingException {
        if (!interactionValidator.isSignatureValid(signature, timestamp, body)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        final var interaction = objectMapper.readValue(body, Interaction.class);

        return interactionHandlers.stream()
            .filter(interactionHandler -> interactionHandler.isInteractionSupported(interaction))
            .map(interactionHandler -> interactionHandler.handleInteraction(interaction))
            .findFirst()
            .orElseThrow();
    }
}
