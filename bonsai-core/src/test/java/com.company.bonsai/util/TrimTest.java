package com.company.bonsai.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class TrimTest {

    Trim trim = new Trim();

    @Test
    public void trimTest() {
        String source = "123" + (char)0 + "123" + (char)1 + "456" + (char)2 + "456" + (char)3 + "456" + (char)4
            + "456" + (char)5 + "456" + (char)6 + "456" + (char)7 + "456" + (char)8 + "456" + (char)9 + "456"
            + (char)10 + "456" + (char)11 + "456" + (char)12 + "456" + (char)13 + "456" + (char)14 + "456"
            + (char)15 + "456" + (char)16 + "456" + (char)17 + "456" + (char)18 + "456" + (char)19 + "456"
            + (char)20 + "456" + (char)21 + "456" + (char)22 + "456" + (char)23 + "456" + (char)24 + "456"
            + (char)25 + "456" + (char)26 + "456" + (char)27 + "456" + (char)28 + "456" + (char)29 + "456"
            + (char)30 + "456"+ (char)31 + "456" + (char)127 + "456";
        String str = trim.trim(source);
        assertEquals("123123456456456456456456456456456456456456"
            +"456456456456456456456456456456456456456456456456456456456456", str);
    }

}