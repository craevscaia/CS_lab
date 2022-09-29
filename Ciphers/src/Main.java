import AffineCipher.AffineCipher;
import CaesarCipher.CaesarCipher;
import PlayfairCipher.PlayfairCipher;
import ViginereCipher.ViginereCipher;

public class Main {
    public static void main(String[] args) {
        MainCipher main = new MainCipher(new CaesarCipher(), new ViginereCipher(), new AffineCipher());
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

    }
}
