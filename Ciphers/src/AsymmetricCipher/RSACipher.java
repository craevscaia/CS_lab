package AsymmetricCipher;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RSACipher implements IRSACipher {
    public int x, y, d, e, n, phi, plainText;

    public RSACipher(int x, int y) {
        this.x = x;
        this.y = y;
        this.n = x * y;
        this.phi = (x - 1) * (y - 1);
    }

    public int encrypt(int plainText) {
        this.plainText = plainText;
        int ciphertext;

        for (e = 2; e < phi; e++) {
            // e is for public key exponent
            if (gcd(e, phi) == 1) {
                break;
            }
        }
        ciphertext = (int) ((Math.pow(plainText, e)) % n);
        System.out.println("Encrypted message is : " + ciphertext);

        return ciphertext;
    }

    public BigInteger decrypt(int cipherText) {
        BigInteger decryptedText;

        for (int i = 0; i <= 9; i++) {
            int x = 1 + (i * phi);
            // d is for private key exponent
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        // converting int value
        BigInteger N = BigInteger.valueOf(n);
        // converting float value
        BigInteger cipher = BigDecimal.valueOf(cipherText).toBigInteger();

        decryptedText = (cipher.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + decryptedText);

        return decryptedText;
    }

    static int gcd(int e, int phi) {
        if (e == 0)
            return phi;
        else
            return gcd(phi % e, e);
    }
}
