package ClassicalCiphers.Implementation;

import ClassicalCiphers.Interface.ICaesarCipher;

public class CaesarCipher implements ICaesarCipher {

    static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String Encrypt(String text, int key) {
        text = text.toLowerCase();
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int charIndex = alphabet.indexOf(text.charAt(i));
            int newIndex = (charIndex + key) % 26;
            encryptedText.append(alphabet.charAt(newIndex));
        }
        return encryptedText.toString();
    }


        @Override
        public String Decrypt(String encryptedText,int key){
            encryptedText = encryptedText.toLowerCase();
            String decryptedText = "";
            for (int i = 0; i < encryptedText.length(); i++) {
                int charIndex = alphabet.indexOf(encryptedText.charAt(i));
                int newIndex = (charIndex - key) % 26;
                if (newIndex < 0) {
                    newIndex = alphabet.length() + 26;
                }
                decryptedText += alphabet.charAt(newIndex);
            }
            return decryptedText;
        }

}
