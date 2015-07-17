package com.company.bonsai.util;

public class Trim {

    public String trim(String str) {
        int length = str.length();
        char[] oldChars = new char[length];
        str.getChars(0, length, oldChars, 0);
        int newLen = 0;
        for (int j = 0; j < length; j++) {
            char ch = oldChars[j];
            if ((ch >= ' ') && (ch != (char)127)) {
                oldChars[newLen++] = ch;
            }
        }
        return new String(oldChars, 0, newLen);
    }

}