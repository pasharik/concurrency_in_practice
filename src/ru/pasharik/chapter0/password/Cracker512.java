package ru.pasharik.chapter0.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pasharik on 19/07/17.
 */
public class Cracker512 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getSHA512SecurePassword("test"));
    }

    public static String salt(String pass, String salt) { return pass + salt; }

    public static String getSHA512SecurePassword(String password) throws NoSuchAlgorithmException {
        password = salt(password, "$2a$06$BMgN9iI8bIUK9vIfPVtgbe");
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(password.getBytes());
        return new String(Hex.encode(bytes));
    }

}
