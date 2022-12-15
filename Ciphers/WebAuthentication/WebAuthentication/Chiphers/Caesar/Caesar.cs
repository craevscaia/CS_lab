namespace WebAuthentication.Chiphers.CaesarCipher;

public static class Caesar
{
    public static char Cipher(char ch, int key)
    {
        if (!char.IsLetter(ch))
            return ch;

        var offset = char.IsUpper(ch) ? 'A' : 'a';
        return (char) ((ch + key - offset) % 26 + offset);
    }
    
    public static string Encrypt(string input, int key)
    {
        var output = string.Empty;

        foreach (var ch in input)
        {
            output += Cipher(ch, key);
        }

        return output;
    }

    public static string Decrypt(string input, int key)
    {
        return Encrypt(input, 26 - key);
    }
}