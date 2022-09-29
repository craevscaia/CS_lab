package AffineCipher;

public interface IAffineCipher {
    String Encrypt(String text, int keyA, int keyB);
    String Decrypt(String encryptedText, int keyA, int keyB);
}
