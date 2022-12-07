package pedestrian.discord.interaction;

import org.springframework.stereotype.Component;
import pedestrian.discord.interaction.model.*;

@Component
public final class PingInteractionHandler implements InteractionHandler {

    @Override
    public InteractionResponse handleInteraction(final Interaction interaction) {
        return ImmutableInteractionResponse.builder()
            .type(InteractionCallbackType.PONG)
            .build();
    }

    @Override
    public boolean isInteractionSupported(final Interaction interaction) {
        return interaction.type() == InteractionType.PING;
    }
}
