package Test;

import ClassicalCiphers.Implementation.AffineCipher;
import AsymmetricCipher.RSACipher;
import SymmetricCiphers.BlockCipher.DESCipher;
import ClassicalCiphers.Implementation.CaesarCipher;
import ClassicalCiphers.Implementation.PlayfairCipher;
import ClassicalCiphers.Implementation.ViginereCipher;

public class Main {
    public static void main(String[] args) {
        MainCipher main = new MainCipher(new CaesarCipher(), new ViginereCipher(), new AffineCipher(), new RSACipher(8900, 191));
        main.mainCipher();

        String key1 = "Problem";
        String plainText1 = "Playfair";

        System.out.println("Key: " + key1);
        System.out.println("PlainText: " + plainText1);

        PlayfairCipher text = new PlayfairCipher(key1, plainText1);
        text.formatKey();
        text.generateTable();

        String enc = text.Encrypt();
        System.out.println("Encrypted text is: " + enc);
        String dec = text.Decrypt("rpcxhegb");
        System.out.println("Decrypted text is: " + dec);

        String plainText = "123456ACCD132536";
        String key = "ABBB02389836CCAA";

        DESCipher cipher = new DESCipher();
        String encryptedText = cipher.encrypt(plainText, key);
        String decryptedText = cipher.decrypt(encryptedText, key);


    }
}
