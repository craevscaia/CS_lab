# Symmetric Ciphers. Stream Ciphers. Block Ciphers.
## Course: Cryptography & Security
## Author: Craevscaia Valentina

# Theory
In order to use symmetric key cryptography, a message must first be encrypted,
as is the case with all other types of encryption. Any plaintext data will be converted
into ciphertext, an unintelligible code, by this encryption process. The original communication
is then recovered by another party after receiving the ciphertext.

The encryption and decryption techniques used in this process both utilize keys of some kind.
Usually, this key is only a string of bits that each stand for a specific integer. Depending
on the type of encryption being used, the key may be anything. In symmetric ciphers, the
encryption and decryption algorithms share the same key. As a result, the encryption and
decryption algorithm functions look like this:

<p align="center">
encrypt(plaintext, key) = ciphertext,
<p align="center">
decrypt(ciphertext, key) = plaintext.

Symmetric encryption algorithms are categorized into two: block and stream ciphers.

- Stream ciphers:
1. The encryption is done one byte at a time.
2. Stream ciphers use confusion to hide the plain text.
3. Make use of substitution techniques to modify the plain text.
4. The implementation is fairly complex.
5. The execution is fast.

- Block ciphers:
1. The encryption is done one block of plain text at a time.
2. Block ciphers use confusion and diffusion to hide the plain text.
3. Make use of transposition techniques to modify the plain text.
4. The implementation is simpler relative to the stream ciphers.
5. The execution is slow compared to the stream ciphers.

Examples of Stream Ciphers are Rivest Cipher (RC4), Salsa20, Software-optimized Encryption Algorithm (SEAL).
Examples of Block Ciphers are Data Encryption Standard (DES), Advanced Encryption Standard (AES), Twofish.
In this laboratory work, DES cipher and RC4 were implemented.

***DES*** is a block cipher and encrypts data in blocks of size of 64 bits each, which means
64 bits of plain text go as the input to DES, which produces 64 bits of ciphertext. The
same algorithm and key are used for encryption and decryption, with minor differences.
The key length is 56 bits.  Actually, the initial key consists of 64 bits. However,
before the DES process even starts, every 8th bit of the key is discarded to produce
a 56-bit key. That is bit positions 8, 16, 24, 32, 40, 48, 56, and 64 are discarded.
DES is based on the two fundamental attributes of cryptography:
***substitution*** (also called confusion) and ***transposition***
(also called diffusion). DES consists of 16 steps, each of which is called a round.
Each round performs the steps of substitution and transposition.

- In the first step, the 64-bit plain text block is handed over to an initial Permutation (IP) function.
- The initial permutation is performed on plain text.
- Next, the initial permutation (IP) produces two halves of the permuted block;
  saying Left Plain Text (LPT) and Right Plain Text (RPT).
- Now each LPT and RPT go through 16 rounds of the encryption process.
- In the end, LPT and RPT are rejoined and a Final Permutation (FP) is performed on the combined block
- The result of this process produces 64-bit ciphertext.


***RC4*** is a type of stream cipher also referred to as Rivest Cipher 4.
It uses an algorithm to encrypt messages one byte at a time.
RC4 is based on the following sequential model:

1. ***Initiate:*** The process starts by entering a secret key and the text you want to protect.

2. ***Encrypt:*** The cipher uses encryption to jumble up your text. Instead of being done in pieces, the work is done one by one.

3. ***Send:*** The recipient will receive your jumbled text. A duplicate of the secret key you used to secure the data should be sent to that person.

4. ***Decryption:*** The receiver repeats these actions to find your original text.

Two mathematical ideas underpin RC4:

1. ***KSA:*** The process is initialized in an array commonly referred to as "S" by a
   key-scheduling method. The letter "S" is processed 256 times, and the key's
   keybytes are also jumbled in.
2.  ***PRGA:*** Byte by byte, data is put into a mathematical model, which transforms it.
    The model looks up values, multiplies them by 256, and uses the result as
    the keystream's first byte. At least once every 256 rounds, each element
    is switched out for another.
# Objectives:
1. Get familiar with the symmetric cryptography, stream and block ciphers.
2. Implement an example of a stream cipher.
3. Implement an example of a block cipher.
4. The implementation should, ideally follow the abstraction/contract/interface used in the previous laboratory work.
5. Please use packages/directories to logically split the files that you will have.
6. As in the previous task, please use a client class or test classes to showcase the execution of your programs.


# Implementation description


### Block Cipher: DES
DES is a block cipher--meaning it operates on plaintext blocks of a given size (64-bits) and returns
ciphertext blocks of the same size. To start with DES cipher, first one represent an interface with two methods
encrypt() and decrypt with() two given parameters the text and the key. The second class implements the interface
with its methods. The encrypt() method takes as parameters  the plainText = "123456ACCD132536" and the
key = "AABB09182736CCDD".

It starts with  preparing 16 keys for 16 rounds, for this the getKeys() method is being used.
After that the initial permutation of the 64 bits of the message data is applied. This rearranges the bits according
to the following table(InitialPermutation), where the entries in the table show the new arrangement of the
bits from their initial order. Next divide the permuted block IP into a left half L0 of 32 bits,
and a right half R0 of 32 bits. Now proceed through 16 iterations, for 1<=n<=16, using the round() function
which operates on two blocks. In the next round, we will have L2 = R1, which is the block we just calculated,
and then we must calculate R2 =L1 + f(R1, K2), and so on for 16 rounds.
At the end of the sixteenth round we have the blocks L16 and R16. We then reverse
the order of the two blocks into the 64-bit block and apply a final permutation IP1.

````
public String encrypt(String plainText, String key)
   {
       int i;
       // get round keys
       String keys[] = getKeys(key);

       // initial permutation
       plainText = permutation(InitialPermutation, plainText);
       System.out.println("After initial permutation: "
               + plainText.toUpperCase());
       System.out.println(
               "After splitting: L0="
                       + plainText.substring(0, 8).toUpperCase()
                       + " R0="
                       + plainText.substring(8, 16).toUpperCase()
                       + "\n");

       // 16 rounds
       for (i = 0; i < 16; i++) {
           plainText = round(plainText, keys[i], i);
       }

       // 32-bit swap
       plainText = plainText.substring(8, 16)
               + plainText.substring(0, 8);

       // final permutation
       plainText = permutation(IP1, plainText);
       return plainText;
   }
````
The getKeys() method used for preparing 16 keys. The initial key is being permuted using the permutation() method
and following the PC1 table. After getting the permuted key, it is split into left and right halves. Each pair
of blocks is formed from the previous pair Ln-1 and Rn-1,
respectively, for n = 1, 2, ..., 16, using the following schedule of "left shifts" of the previous block, the
leftCircularShift(). is being called.
To do a left shift, move each bit one place to the left, except for the first bit, which is cycled to the
end of the block.
````
String[] getKeys(String key)
   {
       String keys[] = new String[16];
       // first key permutation
       key = permutation(PC1, key);
       for (int i = 0; i < 16; i++) {
           key = leftCircularShift(key.substring(0, 7),
                   shiftBits[i])
                   + leftCircularShift(
                   key.substring(7, 14),
                   shiftBits[i]);
           // second key permutation
           keys[i] = permutation(PC2, key);
       }
       return keys;
   }
````
The permutation() method is being called each time when the string of bits has to be permuted according
to the permutation table. It uses to functions hextoBin() and  binToHex(), which is
used for binary to hexadecimal conversion and vice versa.
````
String permutation(int[] sequence, String input)
   {
       String output = "";
       input = hextoBin(input);
       for (int i = 0; i < sequence.length; i++)
           output += input.charAt(sequence[i] - 1);
       output = binToHex(output);
       return output;
   }
````

The conversion functions that take as input the string and converts to the appropriate form.

````
String hextoBin(String input)
   {
       int n = input.length() * 4;
       input = Long.toBinaryString(
               Long.parseUnsignedLong(input, 16));
       while (input.length() < n)
           input = "0" + input;
       return input;
   }

   // binary to hexadecimal conversion
   String binToHex(String input)
   {
       int n = (int)input.length() / 4;
       input = Long.toHexString(
               Long.parseUnsignedLong(input, 2));
       while (input.length() < n)
           input = "0" + input;
       return input;
   }
````
To do a left shift, move each bit one place to the left, except for
the first bit, which is cycled to the end of the block.  In all cases,
by a single left shift is meant a rotation of the bits one place to the left,
so that after one left shift the bits in the 28 positions are the bits that were
previously in positions 2, 3,..., 28, 1.

````
   String leftCircularShift(String input, int numBits)
   {
       int n = input.length() * 4;
       int perm[] = new int[n];
       for (int i = 0; i < n - 1; i++)
           perm[i] = (i + 2);
       perm[n - 1] = 1;
       while (numBits-- > 0)
           input = permutation(perm, input);
       return input;
   }

````
The round() method operates on two blocks, a input block of 32 bits and a key of 48 bits,
to produce a block of 32 bits. The input is separated into the left and right, the right one being stored in temp
variable till last computation. The temp proceed with permutation then it follows with XOR addition,(bit-by-bit addition modulo 2). Then for n going from 1 to 16 we calculate.
This results in a final block, for n = 16, of L16R16. The value is passed the sBox() method.
````
String round(String input, String key, int num)
   {
       // fk
       String left = input.substring(0, 8);
       String temp = input.substring(8, 16);
       String right = temp;
       // Expansion permutation
       temp = permutation(ExpansionPermutation, temp);
       // xor temp and round key
       temp = xor(temp, key);
       // lookup in s-box table
       temp = sBox(temp);
       // Straight D-box
       temp = permutation(Permutation, temp);
       // xor
       left = xor(left, temp);
       System.out.println("Round " + (num + 1) + " "
               + right.toUpperCase() + " "
               + left.toUpperCase() + " "
               + key.toUpperCase());

       // swapper
       return right + left;
   }
````
Here is the xor() method, which performs logical operation on two different strings.

````
String xor(String a, String b)
   {
       // hexadecimal to decimal(base 10)
       long t_a = Long.parseUnsignedLong(a, 16);
       // hexadecimal to decimal(base 10)
       long t_b = Long.parseUnsignedLong(b, 16);
       // xor
       t_a = t_a ^ t_b;
       // decimal to hexadecimal
       a = Long.toHexString(t_a);
       // prepend 0's to maintain length
       while (a.length() < b.length())
           a = "0" + a;
       return a;
   }
````
We now have 48 bits, or eight groups of six bits. We now do something strange with each group
of six bits: we use them as addresses in tables called "S boxes". Each group of six bits will
give us an address in a different S box. Located at that address will be a 4 bit number. This 4
bit number will replace the original 6 bits. The net result is that the eight groups of 6 bits
are transformed into eight groups of 4 bits (the 4-bit outputs from the S boxes) for 32 bits total.
````
String sBox(String input)
   {
       String output = "";
       input = hextoBin(input);
       for (int i = 0; i < 48; i += 6) {
           String temp = input.substring(i, i + 6);
           int num = i / 6;
           int row = Integer.parseInt(
                   temp.charAt(0) + "" + temp.charAt(5),
                   2);
           int col = Integer.parseInt(
                   temp.substring(1, 5), 2);
           output += Integer.toHexString(
                   sbox[num][row][col]);
       }
       return output;
   }

````
The decrypt() method has the same flow as the encryption process.

````
public String decrypt(String plainText, String key)
   {
       int i;
       // get round keys
       String keys[] = getKeys(key);

       // initial permutation
       plainText = permutation(InitialPermutation, plainText);
       System.out.println("After initial permutation: "
               + plainText.toUpperCase());
       System.out.println(
               "After splitting: L0="
                       + plainText.substring(0, 8).toUpperCase()
                       + " R0="
                       + plainText.substring(8, 16).toUpperCase()
                       + "\n");

       // 16-rounds
       for (i = 15; i > -1; i--) {
           plainText
                   = round(plainText, keys[i], 15 - i);
       }

       // 32-bit swap
       plainText = plainText.substring(8, 16)
               + plainText.substring(0, 8);
       plainText = permutation(IP1, plainText);
       return plainText;
   }
````

**Output**
````
Encryption:

After initial permutation: 14A7DE7018CA18A5
After splitting: L0=14A7DE70 R0=18CA18A5

Round 1 18CA18A5 231068A3 0B48BC5AF988
Round 2 231068A3 09A33A56 45E4C91CACEF
Round 3 09A33A56 C2C2FC02 53CD86AEDCD1
Round 4 C2C2FC02 D4CA1269 78A9830BE773
Round 5 D4CA1269 16B262BD 31A42FBFCD00
Round 6 16B262BD 096413DB E10496C84756
Round 7 096413DB 1491EBF2 748AB4DDE28C
Round 8 1491EBF2 16716052 96B032F056C9
Round 9 16716052 75196AE6 C0EB477BBC94
Round 10 75196AE6 EE1421E0 21F7132945BF
Round 11 EE1421E0 F2D4FA8C 6515E30F7887
Round 12 F2D4FA8C 0527345A F3C0D1E641F5
Round 13 0527345A D4DD27B9 1DC392838BCF
Round 14 D4DD27B9 6BA09F46 36119FD69791
Round 15 6BA09F46 C49EF67D 3F00455B076D
Round 16 C49EF67D E424940D 593D5964D6E6

Cipher Text: 0328FF232E5ACAEC

Decryption

After initial permutation: E424940DC49EF67D
After splitting: L0=E424940D R0=C49EF67D

Round 1 C49EF67D 6BA09F46 593D5964D6E6
Round 2 6BA09F46 D4DD27B9 3F00455B076D
Round 3 D4DD27B9 0527345A 36119FD69791
Round 4 0527345A F2D4FA8C 1DC392838BCF
Round 5 F2D4FA8C EE1421E0 F3C0D1E641F5
Round 6 EE1421E0 75196AE6 6515E30F7887
Round 7 75196AE6 16716052 21F7132945BF
Round 8 16716052 1491EBF2 C0EB477BBC94
Round 9 1491EBF2 096413DB 96B032F056C9
Round 10 096413DB 16B262BD 748AB4DDE28C
Round 11 16B262BD D4CA1269 E10496C84756
Round 12 D4CA1269 C2C2FC02 31A42FBFCD00
Round 13 C2C2FC02 09A33A56 78A9830BE773
Round 14 09A33A56 231068A3 53CD86AEDCD1
Round 15 231068A3 18CA18A5 45E4C91CACEF
Round 16 18CA18A5 14A7DE70 0B48BC5AF988

Plain Text: 123456ACCD132536

Process finished with exit code 0

````
### Stream Cipher:
RC4 is a stream cipher--meaning it key algorithm is used identically for encryption and 
decryption such that the data stream is simply XORed with the generated key sequence. 
To start with RC4 cipher, first one represent an interface with two methods
encrypt() and decrypt(). The second class implements the interface
with its methods. The encrypt() method has the given plainText and key. 
The algorithm is serial as it requires successive exchanges of state entries 
based on the key sequence. The algorithm works in two phases: KSA() and PRGA() and they are used 
both for encryption and decryption.
````
def encryption():

    global key, plain_text, n

    # Given text and key
    plain_text = "111110011111"
    key = "101000000000"

    n = 3

    # The initial state vector array
    S = [i for i in range(0, 2**n)]
    print("S : ", S)

    key_list = [key[i:i + n] for i in range(0, len(key), n)]

    # Convert to key_stream to decimal
    for i in range(len(key_list)):
        key_list[i] = int(key_list[i], 2)

    # Convert to plain_text to decimal
    global pt

    pt = [plain_text[i:i + n] for i in range(0, len(plain_text), n)]

    for i in range(len(pt)):
        pt[i] = int(pt[i], 2)

    print("Plain text ( in array form ): ", pt)

    # Making key_stream equal
    # to length of state vector
    diff = int(len(S)-len(key_list))

    if diff != 0:
        for i in range(0, diff):
            key_list.append(key_list[i])

    print("Key list : ", key_list)
    print(" ")

````
By applying a permutation using a variable-length key made up of 0 to 256 bytes, 
it is utilized to construct a State array. The key 
is used to initialize permutation S and can be any length between 0 and 256 bytes. 
A byte makes up each K[I] and S[I].
If the key is 256 bytes in length, copy it to K; otherwise, after copying, the 
leftover slots of K are filled with additional Key Values until full.

````
    def KSA():
        j = 0
        N = len(S)

        # Iterate over the range [0, N]
        for i in range(0, N):

            # Find the key
            j = (j + S[i]+key_list[i]) % N

            # Update S[i] and S[j]
            S[i], S[j] = S[j], S[i]
            print(i, " ", end ="")

            # Print S
            print(S)

    KSA()
````

After one more cycle of permutation, PRGA() method uses the State 
vector array to generate keystream bytes.

````
    def PGRA():

        N = len(S)
        i = j = 0
        global key_stream
        key_stream = []

        # Iterate over [0, length of pt]
        for k in range(0, len(pt)):
            i = (i + 1) % N
            j = (j + S[i]) % N

            # Update S[i] and S[j]
            S[i], S[j] = S[j], S[i]
            print(k, " ", end ="")
            print(S)
            t = (S[i]+S[j]) % N
            key_stream.append(S[t])

    PGRA()
````
To proceed the XOR() operation is performed between the keystream generated 
and the plain text for encryption.

````
 def XOR():
        global cipher_text
        cipher_text = []
        for i in range(len(pt)):
            c = key_stream[i] ^ pt[i]
            cipher_text.append(c)

    XOR()
````
The decryption() process is similar to the encryption().

````
def decryption():

    # The initial state vector array
    S = [i for i in range(0, 2**n)]

    key_list = [key[i:i + n] for i in range(0, len(key), n)]

    # Convert to key_stream to decimal
    for i in range(len(key_list)):
        key_list[i] = int(key_list[i], 2)

    # Convert to plain_text to decimal
    global pt

    pt = [plain_text[i:i + n] for i in range(0, len(plain_text), n)]

    for i in range(len(pt)):
        pt[i] = int(pt[i], 2)

    # making key_stream equal
    # to length of state vector
    diff = int(len(S)-len(key_list))

    if diff != 0:
        for i in range(0, diff):
            key_list.append(key_list[i])

    print(" ")
````
# Conclusion
To sum up, in this laboratory work we implemented stream and block ciphers.
Ciphers have the same steps used for encryption and decryption process(implementation for block and stream differ).
Both of them use bitwise XOR operators on binary data as part of their 
encryption and decryption processes.
Speed and ease of use are among RC4's major benefits. 

