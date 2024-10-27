import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.pqc.jcajce.provider.asymmetric.util.EC5Util;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
import java.util.Scanner;
public class ECC {
    
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }
    public static byte[] performKeyAgreement(PrivateKey privateKey, PublicKey publicKey) throws Exception {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret();
    }
    public static String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            KeyPair aliceKeyPair = generateKeyPair();
            PublicKey alicePublicKey = aliceKeyPair.getPublic();
            PrivateKey alicePrivateKey = aliceKeyPair.getPrivate();

            KeyPair bobKeyPair = generateKeyPair();
            PublicKey bobPublicKey = bobKeyPair.getPublic();
            PrivateKey bobPrivateKey = bobKeyPair.getPrivate();

            byte[] aliceSharedSecret = performKeyAgreement(alicePrivateKey, bobPublicKey);
            byte[] bobSharedSecret = performKeyAgreement(bobPrivateKey, alicePublicKey);

            System.out.println("Alice's Public Key: " + bytesToBase64(alicePublicKey.getEncoded()));
            System.out.println("Bob's Public Key: " + bytesToBase64(bobPublicKey.getEncoded()));
            System.out.println("Alice's Shared Secret: " + bytesToBase64(aliceSharedSecret));
            System.out.println("Bob's Shared Secret: " + bytesToBase64(bobSharedSecret));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
