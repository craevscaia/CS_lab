# Intro to Cryptography. Classical ciphers. Caesar cipher.
## Course: Cryptography & Security
## Author: Craevscaia Valentina

# Theory
Information and data can be protected via
cryptography, so that only those for whom the information
is intended can read and process it.

In computer science, the term "cryptography"
refers to safe information and communication
methods that use mathematical principles and a
system of calculations based on rules, or
"algorithms," to change
messages in ways that are challenging to read.

The following four goals are of particular
relevance to modern cryptography:

1. **Confidentiality**. Anyone for whom the
   information was not meant cannot understand it.
2. **Integrity**. The information cannot be changed
   while being stored or being transported between the
   sender and the intended recipient without the change being noticed.
3. **Non-repudiation**. The person who created or
   sent the material cannot afterwards deny that they
   had any motivation for doing so.
4. **Authentication**. Both the sender and the
   recipient are able to verify each other's identities
   and the information's source and destination.

Cryptography is when the message is disguised instead of hidden.
The recipient then decrypts the received ciphertext to reveal the
original message, also known as the plaintext, which has been encrypted.
The sender and recipient will usually have an agreed upon key. An encryption key is used to create
the ciphertext while a decryption key is used to decrypt the ciphertext into the original message.

In this laboratory work 4 types of ciphers were implemented. 
- First one 
is the Caesar Cipher. It is merely a sort of substitution cipher in which each letter
of a given text is substituted with a letter that is located a certain
number of positions farther down the alphabet. 
- Next one is the Affine cipher,
which maps each letter of the alphabet to its numerical counterpart,
encrypts it using a straightforward mathematical formula, and then
converts it back to the original letter. 
- Third cipher is the Vigenere 
cipher, uses a grid of alphabetic characters where
encoders can shift lines for alphabetic substitution. Instead of doing
a consistent shift alphabetically, the Vigenere shifts letters according
to a repeating keyword, which serves to make the encryption more complex
and more difficult to decode. 
- The last one is Playfair cipher.
It is the same as a traditional cipher. The only difference is that it 
encrypts a digraph (a pair of two letters) instead of a single letter. 
It initially creates a key-table of 5*5 matrix.


# Objectives:
1. Get familiar with the basics of cryptography and classical ciphers.

2. Implement 4 types of the classical ciphers:

- Caesar cipher with one key used for substitution (as explained above),
- Caesar cipher with one key used for substitution, and a permutation of the alphabet,
- Vigenere cipher,
- Playfair cipher.

3. Structure the project in methods/classes/packages as neeeded.


# Implementation description

### Caesar Cipher Implementation
Caesar Cipher has 2 classes, first one represent an interface with two methods
encrypt and decrypt with two given parameters the plain text and the key.
The second class implements the interface with its methods. The Encrypt() method take the
string of lower case letters and traverse it, one character at a time.
For each character, transform it, following the mathematical expression: **(charIndex + key) % 26**
and return the new generated string. 

````
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
````
The Decrypt() method takes the encrypted text and follow the same path as in encrypt method, the
main difference being present in the mathematical equation: **(charIndex - key) % 26**.
As the result of this expression can be a negative, an if statement is added to avoid such 
outcomes. In the end we obtain the generated plain text.
````
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
````

**Output**
````
Encrypted text using Caesar Cipher xcngpvkpc
Decrypted text using Caesar Cipher valentina
````


### Affine Cipher Implementation
Affine Cipher has 2 classes, first one represent an interface with two methods
encrypt and decrypt with three given parameters the plain text and two keys, a and b.
The second class implements the interface with its methods. The Encrypt() method takes
the string of lower case letters and traverse it, one character at a time. 
For each character, transform it, following the mathematical expression:
**(keyA * charIndex + keyB) % 26**, using the given keys and return the new 
generated string. 

````
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
````
The Decrypt() method takes the encrypted text and uses the same keys.
Comparing to the encryption process, here we need the multiplicative inverse of keyA
that will be used in the decryption mathematical expression:**(((aInvers * (x - keyB)) % 26) + 65)**.
To determine the inverse the MultiplicativeInverse(int keyA) method is being called. Knowing the aInvers
we can substitute it the equation and get the position of the new letter, so the plain text is 
being generated.
````
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
````

````
 private static int MultiplicativeInverse(int keyA) {
        for (var i = 1; i < 27; i++) {
            if ((keyA * i) % 26 == 1) {
                return i;
            }
        }
        return keyA;
    }
````

**Output**
````
Encrypted text using Affine Cipher afqjsynsf
Decrypted text using Affine Cipher valentina
````


### Viginere Cipher Implementation
Viginere Cipher has 2 classes, first one represent an interface with two methods
encrypt and decrypt with two given parameters the plain text and the key.
The second class implements the interface with its methods. The encrypt method starts with
creating 2 lists that will contain the position of each character of the string. getCharPosition 
method is being used to compute those character's position. Then the encrypt method takes the
given text and traverse it, one character at a time, following the mathematical expression. In this 
mathematical expression the computed charPosition of the plain text and key are used, 
then the new generated
string is being returned, following the position of the new letter of the alphabet.
Comparing to the Caesar Cipher the key is of type String, so the nr. of letters in the plain 
text and in the key text should be the same. So the expression adjust the text with additional letters
that a less used in the alphabet. 


````
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
````
````
 private List<Integer> getCharPosition(String text) {
        text = text.toLowerCase();
        List<Integer> charAtPosition = new ArrayList<Integer>();
        for (int i = 0; i < text.length(); i++) {
            int charIndex = alphabet.indexOf(text.charAt(i));
            charAtPosition.add(Integer.valueOf(String.valueOf(charIndex)));

        }
        return charAtPosition;
    }
````

The decrypt method takes the encrypted text and follow the same path as in encrypt method,
the main difference being present in the mathematical expression. It has also to subtract 
the added less used letters.
````
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
````
**Output**
````
Encrypted text using Viginere Cipher nuywhgahn
Decrypted text using Viginere Cipher valentina
````


### Playfair Cipher Implementation
Playfair Cipher has 2 classes, first one represent an interface with two methods
encrypt and decrypt, with given parameter the plain text for decryption.
The second class implements the interface with its methods. The encryption of the Playfair
cipher consists in generating a table 5x5 with all the letters from the alphabet, letter "i"
and "j" being as the same. First of all the key that presents a text is being traversed
and formatted, removing all the duplicates. For this the formatKey() method is being used.


````
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
````
After that the remained letters are typed into the table, followed by the rest of the letters
from the alphabet. The generateTable() method is being used to create this 5x5 matrix.

````
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
````
Generating the table the program procedure with the plain text. The formatPlainText() method
is called to format the plain text, replacing the character j with i, if there are 2 consecutive
characters that are same, then a less used letter in the alphabet is being inserted between them.


````
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
````
The formPairs() method form pairs from the formatted plain text.

````
public String[] formPairs(String text) {
        String[] pairs = new String[text.length() / 2];

        for (int i = 0, cnt = 0; i < text.length() / 2; i++)
            pairs[i] = text.substring(cnt, cnt += 2);

        return pairs;
    }
````
The getCharPosition() function is used to generate the position of each letter in the table.
Further those position will be used in encryption process.
````
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
````
The Encrypt() function is in charge of encrypting the plain text using the 5x5 matrix
and following the cases in which the pairs of letters appear in the table: same raw, column or
different rows and columns.
````
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
````
The decryption() method follow the same principle as in the encryption().
````
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
````
The getPoint() method gives the position of the character in the table used in 
decryption process.

````
private Point getPoint(char c) {
          Point pt = new Point(0, 0);
          for (int i = 0; i < 5; i++)
              for (int j = 0; j < 5; j++)
                  if (c == matrix[i][j])
                    pt = new Point(i, j);
        return pt;
    }
````
**Output**
````
Key: Problem
PlainText: Playfair
Playfair Cipher Key Matrix:
[p, r, o, b, l]
[e, m, a, c, d]
[f, g, h, i, k]
[n, q, s, t, u]
[v, w, x, y, z]
Encrypted text is: rpcxhegb
Decrypted text is: playfair
````

# Conclusion
To sum up, classical ciphers are easily broken. It is significant to note 
that while some classical ciphers are very difficult for an attacker to break or 
decipher using cryptanalysis, they are simple to identify from ciphertext. 
On the other hand, some other ciphers, such as the Caesar cipher, can be cracked 
using a straightforward brute force attack, but determining their type is a more 
challenging procedure. This provides us with an extra understanding of the 
classification of ciphertext's necessity.