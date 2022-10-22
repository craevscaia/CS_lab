# Topic: Asymmetric Ciphers.
## Course: Cryptography & Security
## Author: Craevscaia Valentina

# Theory
Asymmetric cryptography, commonly referred to as public-key cryptography, 
is a method for encrypting and decrypting messages and protecting them from 
unwanted access or use. It makes use of a pair of linked keys, a public 
key and a private key.

A public key is a cryptographic key that anybody can use to encrypt 
messages such that only the intended recipient can decrypt them using 
their private key. A private key, usually referred to as a secret key, 
is only disclosed to the key's creator.

An example of asymmetric cipher is RSA.
This means that it uses a public key and a private key.
The keys are made as safe as feasible using the RSA method. 
The actions that follow demonstrate how it functions:

1. Generating the keys.
- Choose the big prime numbers x and y. It is necessary for the prime numbers to 
be huge in order to make them challenging to decipher.
- Calculate n = x * y.
- Calculate the totient function:
  ϕ(n)=(x−1)(y−1).
- Choose an integer e, such that e is co-prime to ϕ(n)
  and 1<e<ϕ(n).The pair of numbers (n,e) makes up the public key.
- Now calculate Private Key, d :
  d = (k*Φ(n) + 1) / e for some integer k.

2. Encryption.
   Given a plaintext, represented as a number, the ciphertext is calculated as:
   cipherText = plaintext^{e} mod n.

3. Decryption.
   Using the private key (n,d), the plaintext can be found using:
   plaintext = cipherText^{d} mod n.


# Objectives:
1. Get familiar with the asymmetric cryptography mechanisms.
2. Implement an example of an asymmetric cipher.
3. As in the previous task, please use a client class or test 
classes to showcase the execution of your programs.


# Implementation description
To start with RSA implementation consists of 2 classes, 
first one represent an interface with two methods
encrypt() and decrypt() with  given parameter, the text. 
The second class implements the interface
with its methods. 

### RSA Cipher
Firstly two numbers x and y are chosen. Then n is computed through the formula: n = x * y;
The encrypt() method takes as parameter an integer plainText. To proceed an e integer is 
chosen, such that e should be co-prime to phi(ϕ(n)), and 1 < e < phi; After that the 
cipherText is calculated using the mathematical equation: ciphertext = ((plainText.pow(e) % n), where
n is computed through the formula: n = x * y;
````
public int encrypt(int plainText) {
        this.plainText = plainText;
        int ciphertext;

        for (e = 2; e < phi; e++) {

            // e is for public key exponent
            if (gcd(e, phi) == 1) {
                break;
            }
        }
        ciphertext = (int) ((Math.pow(plainText, e)) % n);
        System.out.println("Encrypted message is : " + ciphertext);

        return ciphertext;
    }
````
To decrypt() method takes the cipher text and proceed. The private key d is computed
d = ((i * Φ(n) + 1) / e), where i should be an integer value 0 < i <= 9; Following the decryption
process of the cipher text using the mathematical expression:  
decryptedText = (cipherText.pow(d)).mod(n), where n is previously computed through:
n = x * y;

````
    public BigInteger decrypt(int cipherText) {
        BigInteger decryptedText;

        for (int i = 0; i <= 9; i++) {
            int x = 1 + (i * phi);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }

        BigInteger N = BigInteger.valueOf(n);
        BigInteger cipher = BigDecimal.valueOf(cipherText).toBigInteger();
        
        decryptedText = (cipher.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + decryptedText);

        return decryptedText;
    }

````

# Output
````
Encrypted message is : 900
Decrypted message is : 158
````

# Conclusion
To sum up, asymmetric cryptography has several advantages, including:
there is no need to exchange keys, hence the issue with key distribution is solved and
since the private keys never need to be communicated or made public, security is increased.
The RSA algorithm -- the most widely used asymmetric algorithm. The computational complexity of factoring huge 
integers that are the product of two large prime numbers is the source of RSA's security.