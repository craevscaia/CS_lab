package AffineCipher;

public class AffineCipher implements IAffineCipher {

    public String Encrypt(String text, int a, int b) {
        var ciphertext = "";
        var chars = text.toUpperCase().toCharArray();

        for (var c : chars) {
            var x = (int)(c - 65);
            ciphertext += (char) (((a * x + b) % 26) + 65);
        }
        return ciphertext;
    }

    public String Decrypt(String text, int a, int b) {
        var plaintext = "";

        var aInvers = MultiplicativeInverse(a);

        var chars = text.toLowerCase().toCharArray();

        for (var c : chars) {
            var x = (int) (c - 65);
            if (x - b < 0) {
                x += (int) (x) + 26;
            }
            plaintext += (char) (((aInvers * (x - b)) % 26) + 65);
        }
        return text;
    }

    private static int MultiplicativeInverse(int a) {
        for (var i = 1; i < 27; i++) {
            if ((a * i) % 26 == 1) {
                return i;
            }
        }
        return 0;
    }
}
