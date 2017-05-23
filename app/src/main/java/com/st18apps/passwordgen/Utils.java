package com.st18apps.passwordgen;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by st18r on 15.05.2017.
 */

public class Utils {

    private int charNum;

    private String[] passwordChar = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z"};

    public Utils(int charNum) {
        this.charNum = charNum;
    }

    public String randomChar() {

        String generatedPassword = "";
        for (int i = 0; i < charNum; i++) {
            int randomC = (int) (Math.random() * passwordChar.length - 1) + 1;
            String generatedPasswordChar = passwordChar[randomC];
            generatedPassword += generatedPasswordChar;

        }
        return generatedPassword;
    }

    public static String md5(String string) {
        return new String(Hex.encodeHex(DigestUtils.md5(string)));
    }

    public static String sha1(String string) {
        return new String(Hex.encodeHex(DigestUtils.sha1(string)));
    }

    public static String base64(String string) {
        return new String(Base64.encodeBase64(string.getBytes()));
    }
}
