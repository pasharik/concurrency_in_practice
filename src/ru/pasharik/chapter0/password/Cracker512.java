package ru.pasharik.chapter0.password;

import java.security.NoSuchAlgorithmException;

/**
 * Created by pasharik on 19/07/17.
 */
public class Cracker512 {
    public static String TARGET = "4ef1e7e4769b4b3d1f4b8501b64f731b56e6c6e2c0e0f68843156a03a09ea5728a4d3a88f5495ac28e69a43c9c8f54ca4fd06f6360976aa420471372f744c702";
    public static char[] chars = new char[]{'t', 'e', 's'};
    public static int pwdMaxLen = 4;

    public static void main(String[] args) throws NoSuchAlgorithmException { doCrack(); }


    public static void doCrack() throws NoSuchAlgorithmException {
        for (int pwdlen = 1; pwdlen <= pwdMaxLen; pwdlen++) {
            long pow = (long) Math.pow(chars.length, pwdlen);
            for (long i = 0; i < pow; i++) {
                long t = i;
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < pwdlen; j++) {
                    sb.append(chars[(int) t % chars.length]);
                    t /= chars.length;
                }

                if (tryPass(sb.toString())) { System.out.println(sb.toString()); return; }
            }
        }
    }

    public static boolean tryPass(String password) throws NoSuchAlgorithmException {
        return TARGET.equals(Hex.getSHA512SecurePassword(password));
    }

}
