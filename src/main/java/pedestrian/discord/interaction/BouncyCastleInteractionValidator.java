package pedestrian.discord.interaction;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public final class BouncyCastleInteractionValidator implements InteractionValidator {

    private final CipherParameters publicKey;

    public BouncyCastleInteractionValidator(
        @Value("${DISCORD_PUBLIC_KEY}")
        final String publicKey
    ) {
        final var publicKeyBytes = Hex.decodeStrict(publicKey);
        this.publicKey = new Ed25519PublicKeyParameters(publicKeyBytes);
    }

    @Override
    public boolean isSignatureValid(final String signature, final String timestamp, final String body) {
        final var message = timestamp + body;
        final var messageBytes = message.getBytes(StandardCharsets.UTF_8);
        final var signatureBytes = Hex.decodeStrict(signature);

        final var signer = new Ed25519Signer();
        signer.init(false, publicKey);
        signer.update(messageBytes, 0, messageBytes.length);
        return signer.verifySignature(signatureBytes);
    }
}
