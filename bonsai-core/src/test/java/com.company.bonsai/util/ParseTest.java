package com.company.bonsai.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParseTest {

    Parse parse = new Parse();

    @Test
    public void beginSubstringIsEmpty() {
        String str = parse.parse("<1> <2> <3> <4>", "", "2");

        assertEquals("<1> <", str);
    }

    @Test
    public void endSubstringIsEmpty() {
        String str = parse.parse("<1> <2> <3> <4>", "<2", "");

        assertEquals("> <3> <4>", str);
    }

    @Test
    public void bothSubstringIsEmpty() {
        String str = parse.parse("<1> <2> <3> <4>", "", "");

        assertEquals("", str);
    }

    @Test
    public void beginNotFindSubstringIsEmpty() {
        String str = parse.parse("<1> <2> <3> <4>", "5", ">");

        assertEquals("", str);
    }

    @Test
    public void endNotFindSubstringIsEmpty() {
        String str = parse.parse("<1> <2> <3> <4>", "3>", "2");

        assertEquals("", str);
    }

    @Test
    public void alright() {
        String str = parse.parse("<1> <2> <3> <4>", " <2", "<4");

        assertEquals("> <3> ", str);
    }

    @Test
    public void beginSubstringIsEmptyLast() {
        String str = parse.parseLast("<1> <2> <2> <4>", "", "2>");

        assertEquals("<1> <2> <", str);
    }

    @Test
    public void endSubstringIsEmptyLast() {
        String str = parse.parseLast("<1> <2> <2> <4>", "<2", "");

        assertEquals("> <4>", str);
    }

    @Test
    public void bothSubstringIsEmptyLast() {
        String str = parse.parseLast("<1> <2> <2> <4>", "", "");

        assertEquals("", str);
    }

    @Test
    public void beginNotFindSubstringIsEmptyLast() {
        String str = parse.parseLast("<1> <2> <2> <4>", "p", ">");

        assertEquals("", str);
    }

    @Test
    public void endNotFindSubstringIsEmptyLast() {
        String str = parse.parseLast("<1> <2> <2> <4>", "2>", "1>");

        assertEquals("", str);
    }

    @Test
    public void alrightLast() {
        String str = parse.parseLast("<1> <2> <2> <4>", " <2", "4");

        assertEquals("> <", str);
    }

    @Test
    public void parseIfEmptyWithEmpty() {
        String str = parse.parseIfEmpty("", "<1> <2> <2> <4>", " <2", "4");

        assertEquals("> <2> <", str);
    }

    @Test
    public void parseIfEmptyWithNotEmpty() {
        String str = parse.parseIfEmpty("no empty string", "<1> <2> <2> <4>", " <2", "4");

        assertEquals("no empty string", str);
    }

    @Test
    public void parseIfEmptyWithEmptyLast() {
        String str = parse.parseIfEmptyLast("", "<1> <2> <2> <4>", " <2", "4");

        assertEquals("> <", str);
    }

    @Test
    public void parseIfEmptyWithNotEmptyLast() {
        String str = parse.parseIfEmptyLast("no empty string last", "<1> <2> <2> <4>", " <2", "4");

        assertEquals("no empty string last", str);
    }

}