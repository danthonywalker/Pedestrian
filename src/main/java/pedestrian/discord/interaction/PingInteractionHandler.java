package pedestrian.discord.interaction;

import org.springframework.stereotype.Component;
import pedestrian.discord.interaction.model.Interaction;
import pedestrian.discord.interaction.model.InteractionCallbackType;
import pedestrian.discord.interaction.model.InteractionResponse;
import pedestrian.discord.interaction.model.InteractionType;

@Component
public final class PingInteractionHandler implements InteractionHandler {

    @Override
    public InteractionResponse handleInteraction(final Interaction interaction) {
        return InteractionResponse.builder()
            .type(InteractionCallbackType.PONG)
            .build();
    }

    @Override
    public boolean isInteractionSupported(final Interaction interaction) {
        return interaction.type() == InteractionType.PING;
    }
}
