package Test;

import Hash.CreateUser;

import java.security.KeyPair;

import static Hash.CreateUser.byteToHex;
import static Hash.CreateUser.generateRSAKeyPair;

public class MainHash {
    public static void main(String[] args) throws Exception {
        String userName = "valentina";
        String password = "password";
        KeyPair keyPair = generateRSAKeyPair();

        CreateUser user = new CreateUser();

        byte[] newUser = user.addUser(userName,password.getBytes(), keyPair.getPrivate());
        boolean verify = user.verifyDigitalSignature(password.getBytes(),newUser, keyPair.getPublic());
        System.out.println(byteToHex(newUser));
        System.out.println(verify);
    }
}
