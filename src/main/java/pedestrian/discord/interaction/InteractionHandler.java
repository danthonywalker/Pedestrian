package pedestrian.discord.interaction;

import pedestrian.discord.interaction.model.Interaction;
import pedestrian.discord.interaction.model.InteractionResponse;

public interface InteractionHandler {

    InteractionResponse handleInteraction(Interaction interaction);

    boolean isInteractionSupported(Interaction interaction);
}
