package AsymmetricCipher;

import java.math.BigInteger;

public interface IRSACipher {
    int encrypt(int plainText);
    BigInteger decrypt(int cipherText);
}
