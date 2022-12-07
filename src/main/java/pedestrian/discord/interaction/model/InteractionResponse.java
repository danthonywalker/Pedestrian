package pedestrian.discord.interaction.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonDeserialize(as = ImmutableInteractionResponse.class)
@JsonSerialize(as = ImmutableInteractionResponse.class)
@Value.Immutable
public interface InteractionResponse {

    InteractionCallbackType type();
}
