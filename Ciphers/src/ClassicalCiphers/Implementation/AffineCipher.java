package ClassicalCiphers.Implementation;

import ClassicalCiphers.Interface.IAffineCipher;

public class AffineCipher implements IAffineCipher {

    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public String Encrypt(String text, int keyA, int keyB){
        text = text.toLowerCase();
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int charIndex = alphabet.indexOf(text.charAt(i));
            int newIndex = (keyA * charIndex + keyB) % 26;
            encryptedText.append(alphabet.charAt(newIndex));
        }

        return encryptedText.toString();
    }

    public String Decrypt(String text, int keyA, int keyB) {
        var decryptText = "";

        var aInvers = MultiplicativeInverse(keyA);

        var chars = text.toUpperCase().toCharArray();

        for (var c : chars) {
            var x = (int) (c - 65);
            if (x - keyB < 0) {
                x += (int) (x) + 26;
            }
            decryptText += (char) (((aInvers * (x - keyB)) % 26) + 65);
        }
        return decryptText;
    }

    private static int MultiplicativeInverse(int keyA) {
        for (var i = 1; i < 27; i++) {
            if ((keyA * i) % 26 == 1) {
                return i;
            }
        }
        return keyA;
    }
}
