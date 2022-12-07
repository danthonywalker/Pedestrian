package pedestrian.discord.interaction;

@FunctionalInterface
public interface InteractionValidator {

    boolean isSignatureValid(String signature, String timestamp, String body);
}
