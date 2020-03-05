package com.bongotest.anagramchecker;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramCheckerUnitTest {

    @Test
    public void testIsAnagram_testNullValues_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram(null, null));
    }

    @Test
    public void testIsAnagram_testEmptyStrings_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram("", ""));
    }

    @Test
    public void testIsAnagram_testEmptyStringAndNullValue_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram("", null));
    }

    @Test
    public void testIsAnagram_testNullValueAndEmptyString_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram(null, ""));
    }

    @Test
    public void testIsAnagram_testCaseSensitivity_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram("a", "B"));
        assertFalse(AnagramCheckerActivity.isAnagram("A", "b"));
    }

    @Test
    public void testIsAnagram_testCaseSensitivity_returnsTrue() {
        assertTrue(AnagramCheckerActivity.isAnagram("a", "A"));
        assertTrue(AnagramCheckerActivity.isAnagram("A", "a"));
        assertTrue(AnagramCheckerActivity.isAnagram("bleat", "taBle"));
        assertTrue(AnagramCheckerActivity.isAnagram("bleat", "Table"));
        assertTrue(AnagramCheckerActivity.isAnagram("bleat", "tablE"));
        assertTrue(AnagramCheckerActivity.isAnagram("bleat", "TaBlE"));
        assertTrue(AnagramCheckerActivity.isAnagram("Bleat", "table"));
        assertTrue(AnagramCheckerActivity.isAnagram("bleaT", "table"));
        assertTrue(AnagramCheckerActivity.isAnagram("blEat", "table"));
        assertTrue(AnagramCheckerActivity.isAnagram("BlEaT", "table"));
        assertTrue(AnagramCheckerActivity.isAnagram("BlEat", "TaBlE"));
    }

    @Test
    public void testIsAnagram_testSameLengthStrings_returnsTrue() {
        assertTrue(AnagramCheckerActivity.isAnagram("a", "a"));
        assertTrue(AnagramCheckerActivity.isAnagram("A", "A"));
        assertTrue(AnagramCheckerActivity.isAnagram("word", "wrdo"));
        assertTrue(AnagramCheckerActivity.isAnagram("mary", "army"));
        assertTrue(AnagramCheckerActivity.isAnagram("stop", "tops"));
        assertTrue(AnagramCheckerActivity.isAnagram("boat", "btoa"));
        assertTrue(AnagramCheckerActivity.isAnagram("bleat", "table"));
        assertTrue(AnagramCheckerActivity.isAnagram("BLEAT", "TABLE"));
    }

    @Test
    public void testIsAnagram_testSameLengthStrings_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram("A", "B"));
        assertFalse(AnagramCheckerActivity.isAnagram("a", "b"));
        assertFalse(AnagramCheckerActivity.isAnagram("poll", "pool"));
        assertFalse(AnagramCheckerActivity.isAnagram("eat", "tar"));
    }

    @Test
    public void testIsAnagram_testDifferentLengthStrings_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram("pure", "in"));
        assertFalse(AnagramCheckerActivity.isAnagram("fill", "fil"));
        assertFalse(AnagramCheckerActivity.isAnagram("fil", "fill"));
        assertFalse(AnagramCheckerActivity.isAnagram("b", "bbb"));
        assertFalse(AnagramCheckerActivity.isAnagram("ccc", "ccccccc"));
        assertFalse(AnagramCheckerActivity.isAnagram("sleep", "slep"));
    }

    @Test
    public void testIsAnagram_testLargeStringsInSameSequence_returnsTrue() {
        assertTrue(AnagramCheckerActivity.isAnagram("Hi, this is a large string to test anagram. If the test fails then there is a problem.",
                "Hi, this is a large string to test anagram. If the test fails then there is a problem."));
    }

    @Test
    public void testIsAnagram_testLargeStringsInDifferentSequence_returnsTrue() {
        assertTrue(AnagramCheckerActivity.isAnagram("Hi, this is a large string to test anagram. If this test fails then there is a problem.",
                "Hi, if this test fails then there is a problem. This is a large string to test anagram."));
    }

    @Test
    public void testIsAnagram_testLargeStrings_returnsFalse() {
        assertFalse(AnagramCheckerActivity.isAnagram("Hi this is a large string to test anagram. If the test fails then there is a problem.",
                "Hi this is a large string to test anagram. If the test fails then there is problem."));
    }
}