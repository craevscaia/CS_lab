package ViginereCipher;

public interface IViginereCipher {
    String Encrypt(String text, String key);
    String Decrypt(String encryptedText, String key);
}
