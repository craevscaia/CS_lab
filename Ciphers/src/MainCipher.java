import AffineCipher.IAffineCipher;
import AsymmetricCipher.RSACipher;
import CaesarCipher.ICaesarCipher;
import ViginereCipher.IViginereCipher;

public class MainCipher {
    private ICaesarCipher caesarCipher;
    private IViginereCipher viginereCipher;
    private IAffineCipher affineCipher;

    public MainCipher(ICaesarCipher caesarCipher,
                      IViginereCipher viginereCipher, IAffineCipher affineCipher) {
        this.caesarCipher = caesarCipher;
        this.viginereCipher = viginereCipher;
        this.affineCipher = affineCipher;
    }

    public void mainCipher(){
        String text= "Valentina";
        int key = 1;
        int keyB = 5;
        String keyV = "sun";


        var encryptedCaesar = caesarCipher.Encrypt(text, key);
        var decryptedCaesar = caesarCipher.Decrypt(encryptedCaesar, key);
        System.out.println("Encrypted text using Caesar Cipher " + encryptedCaesar);
        System.out.println("Decrypted text using Caesar Cipher " + decryptedCaesar);

        var encryptedViginere = viginereCipher.Encrypt(text, keyV);
        var decryptedViginere = viginereCipher.Decrypt(encryptedViginere, keyV);
        System.out.println("Encrypted text using Viginere Cipher " + encryptedViginere);
        System.out.println("Decrypted text using Viginere Cipher " + decryptedViginere);


        var encryptedAffine = affineCipher.Encrypt(text, key, keyB);
        var decryptedAffine = affineCipher.Decrypt(encryptedAffine, key, keyB);
        System.out.println("Encrypted text using Affine Cipher " + encryptedAffine);
        System.out.println("Decrypted text using Affine Cipher " + decryptedAffine);

        RSACipher rsa = new RSACipher(89, 11);
        int encryptedText =  rsa.encrypt(158);
        rsa.decrypt(encryptedText);
    }
}
