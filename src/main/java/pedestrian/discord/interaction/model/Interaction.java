package pedestrian.discord.interaction.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonDeserialize(as = ImmutableInteraction.class)
@JsonSerialize(as = ImmutableInteraction.class)
@Value.Immutable
public interface Interaction {

    InteractionType type();
}
