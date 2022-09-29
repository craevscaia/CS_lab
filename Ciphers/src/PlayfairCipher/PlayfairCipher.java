package PlayfairCipher;

import java.awt.*;
import java.util.*;

public class PlayfairCipher implements IPlayfairCipher{

    String key;
    String plainText;
    char[][] matrix = new char[5][5];

    public PlayfairCipher(String key, String plainText) {
        this.key = key.toLowerCase();
        this.plainText = plainText.toLowerCase();
    }


    public void formatKey() {
        LinkedHashSet<Character> set = new LinkedHashSet<Character>();
        String newKey = "";

        for (int i = 0; i < key.length(); i++)
            set.add(key.charAt(i));

        Iterator<Character> it = set.iterator();

        while (it.hasNext())
            newKey += (Character) it.next();

        key = newKey;
    }

    // function to generate playfair cipher key table
    public void generateTable() {
        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == 'j')
                continue;
            set.add(key.charAt(i));
        }

        // remove repeated characters from the cipher key
        String tempKey = new String(key);

        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 97);
            if (ch == 'j')
                continue;

            if (!set.contains(ch))
                tempKey += ch;
        }

        // create cipher key table
        for (int i = 0, idx = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = tempKey.charAt(idx++);

        System.out.println("Playfair Cipher Key Matrix:");

        for (int i = 0; i < 5; i++)
            System.out.println(Arrays.toString(matrix[i]));
    }

    // function to preprocess plaintext
    public String formatPlainText() {
        String text = "";

        for (int i = 0; i < plainText.length(); i++) {
            // if plaintext contains the character 'j',
            // replace it with 'i'
            if (plainText.charAt(i) == 'j')
                text += 'i';
            else
                text += plainText.charAt(i);
        }

        // if two consecutive characters are same, then
        // insert character 'x' in between them
        for (int i = 0; i < text.length(); i += 2) {
            if (text.charAt(i) == text.charAt(i + 1))
                text = text.substring(0, i + 1) + 'x'
                        + text.substring(i + 1);
        }

        // make the plaintext of even length
        if (plainText.length() % 2 == 1)
            text += 'x'; // dummy character

        return text;
    }

    // function to group every two characters
    public String[] formPairs(String text) {
        String[] pairs = new String[text.length() / 2];

        for (int i = 0, cnt = 0; i < text.length() / 2; i++)
            pairs[i] = text.substring(cnt, cnt += 2);

        return pairs;
    }

    // function to get position of character in key table
    public int[] getCharPosition(char ch) {
        int[] keyPos = new int[2];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (matrix[i][j] == ch) {
                    keyPos[0] = i;
                    keyPos[1] = j;
                    break;
                }
            }
        }
        return keyPos;
    }

    @Override
    public String Encrypt() {
        String message = formatPlainText();
        String[] msgPairs = formPairs(message);
        String encText = "";

        for (int i = 0; i < msgPairs.length; i++) {
            char ch1 = msgPairs[i].charAt(0);
            char ch2 = msgPairs[i].charAt(1);
            int[] ch1Pos = getCharPosition(ch1);
            int[] ch2Pos = getCharPosition(ch2);

            // if both the characters are in the same row
            if (ch1Pos[0] == ch2Pos[0]) {
                ch1Pos[1] = (ch1Pos[1] + 1) % 5;
                ch2Pos[1] = (ch2Pos[1] + 1) % 5;
            }

            // if both the characters are in the same column
            else if (ch1Pos[1] == ch2Pos[1]) {
                ch1Pos[0] = (ch1Pos[0] + 1) % 5;
                ch2Pos[0] = (ch2Pos[0] + 1) % 5;
            }

            // if both the characters are in different rows
            // and columns
            else {
                int temp = ch1Pos[1];
                ch1Pos[1] = ch2Pos[1];
                ch2Pos[1] = temp;
            }

            // get the corresponding cipher characters from
            // the key matrix
            encText = encText + matrix[ch1Pos[0]][ch1Pos[1]]
                    + matrix[ch2Pos[0]][ch2Pos[1]];
        }

        return encText;
    }

    @Override
    public String Decrypt(String text) {

        String decText = "";
        for (int i = 0; i < text.length() / 2; i++) {
            char a = text.charAt(2 * i);
            char b = text.charAt(2 * i + 1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();
            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decText = decText + matrix[r1][c1] + matrix[r2][c2];
        }
        return decText;
    }

    private Point getPoint(char c) {
        Point pt = new Point(0, 0);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (c == matrix[i][j])
                    pt = new Point(i, j);
        return pt;
    }
}
