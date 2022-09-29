package ViginereCipher;

import java.util.ArrayList;
import java.util.List;

public class ViginereCipher implements IViginereCipher{

    static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private List<Integer> getCharPosition(String text) {
        text = text.toLowerCase();
        List<Integer> charAtPosition = new ArrayList<Integer>();
        for (int i = 0; i < text.length(); i++) {
            int charIndex = alphabet.indexOf(text.charAt(i));
            charAtPosition.add(Integer.valueOf(String.valueOf(charIndex)));

        }
        return charAtPosition;
    }

    @Override
    public String Encrypt(String text, String key) {
        List<Integer> textCharAtPosition = getCharPosition(text);
        List<Integer> keyCharAtPosition = getCharPosition(key);

        String encryptedText = new String();
        for (int i = 0; i < text.length(); i++) {
            int newLetter = textCharAtPosition.get(i) + keyCharAtPosition.get(i % key.length());
            newLetter %= alphabet.length();
            encryptedText += (alphabet.charAt(newLetter));
        }
        return encryptedText;
    }

    @Override
    public String Decrypt(String encryptedText, String key) {
        List<Integer> encryptedCharAtPosition = getCharPosition(encryptedText);
        List<Integer> keyCharAtPosition = getCharPosition(key);

        String decryptedText = new String();
        for (int i = 0; i < encryptedText.length(); i++) {
            int newLetter = encryptedCharAtPosition.get(i) - keyCharAtPosition.get(i % key.length());
            newLetter += alphabet.length();
            newLetter %= alphabet.length();
            decryptedText += (alphabet.charAt(newLetter));
        }
        return decryptedText;
    }
}
