package ru.pasharik.chapter0.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pasharik on 19/07/17.
 * Taken from Spring
 */
public class Hex {
    private static final char[] HEX = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static char[] encode(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2*nBytes];

        int j = 0;
        for (int i=0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4 ];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }

        return result;
    }

    public static String getSHA512SecurePassword(String password) /*throws NoSuchAlgorithmException*/ {
        password = password + "$2a$06$BMgN9iI8bIUK9vIfPVtgbe";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        byte[] bytes = md.digest(password.getBytes());
        return new String(Hex.encode(bytes));
    }
}
