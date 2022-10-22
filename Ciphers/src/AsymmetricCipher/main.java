package AsymmetricCipher;

public class main {
    public static void main(String[] args) {
        RSACipher rsa = new RSACipher(89, 11);
        int encryptedText =  rsa.encrypt(158);
        rsa.decrypt(encryptedText);
    }
}
