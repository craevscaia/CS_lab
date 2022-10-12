package BlockCipher;

public interface IDESCipher {
    String encrypt(String plainText, String key);
    String decrypt(String plainText, String key);

}
