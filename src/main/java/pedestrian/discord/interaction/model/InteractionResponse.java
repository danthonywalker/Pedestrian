package pedestrian.discord.interaction.model;

import org.immutables.value.Value;

@Value.Immutable
public interface InteractionResponse {

    InteractionCallbackType type();
}
