package Hash;

import java.security.*;

public class CreateUser {
    private UserStorage userStorage = new UserStorage();

    public static KeyPair generateRSAKeyPair()
            throws Exception
    {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public byte[] addUser(String name, byte[] password, PrivateKey privateKey)
         throws Exception
    {
        User newUser = new User();
        newUser.name =name;
        newUser.password = password;
        userStorage.usersList.add(newUser);
        userStorage.getUsersList();

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(password);

        return signature.sign();
    }

    public boolean verifyDigitalSignature(byte[] password, byte[] signatureToVerify, PublicKey publicKey)
            throws Exception
    {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(password);

        return signature.verify(signatureToVerify);
    }

    public static String byteToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }


}
