package ClassicalCiphers.Interface;

public interface ICaesarCipher {
    String Encrypt(String text, int key);
    String Decrypt(String encryptedText, int key);
}
