package pedestrian.discord.interaction;

import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public final class BouncyCastleInteractionValidator implements InteractionValidator {

    private final Ed25519PublicKeyParameters publicKey;

    public BouncyCastleInteractionValidator(
        @Value("${DISCORD_PUBLIC_KEY}")
        final String publicKey
    ) {
        final var publicKeyBytes = Hex.decode(publicKey);
        this.publicKey = new Ed25519PublicKeyParameters(publicKeyBytes);
    }

    @Override
    public boolean isSignatureValid(final String signature, final String timestamp, final String body) {
        final var message = timestamp + body;
        final var messageBytes = message.getBytes(StandardCharsets.UTF_8);
        final var signatureBytes = Hex.decode(signature);

        final var verifier = new Ed25519Signer();
        verifier.init(false, publicKey);
        verifier.update(messageBytes, 0, messageBytes.length);
        return verifier.verifySignature(signatureBytes);
    }
}
