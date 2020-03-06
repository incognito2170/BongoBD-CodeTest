# Source code for Anagram check with Local Unit Test code.
* Unit test code is placed under "BongoBD-CodeTest/AnagramChecker/app/src/test/java/com/bongotest/anagramchecker/".

# Q1) Write a function that detects if two strings are anagram e.g. ‘bleat’ and ‘table’ are anagrams but ‘eat’ and ‘tar’ are not.

# Answer:

```java
static boolean isAnagram(String firstWord, String secondWord) {
       if (null == firstWord || null == secondWord || firstWord.equals("") || secondWord.equals("")) {
           return false;
       } else if (firstWord.length() != secondWord.length()) {
           return false;
       }

       String firstString = firstWord.toLowerCase();
       String secondString = secondWord.toLowerCase();

       Map<Character, Integer> occurrencesMap = new HashMap<>();

       for (int i = 0; i < firstString.length(); i++) {
           char charFromLeft = firstString.charAt(i);
           int numberOfCharsInLeft = occurrencesMap.containsKey(charFromLeft) ? Objects.requireNonNull(occurrencesMap.get(charFromLeft)) : 0;
           occurrencesMap.put(charFromLeft, ++numberOfCharsInLeft);

           char charFromRight = secondString.charAt(i);
           int numberOfCharsInRight = occurrencesMap.containsKey(charFromRight) ? Objects.requireNonNull(occurrencesMap.get(charFromRight)) : 0;
           occurrencesMap.put(charFromRight, --numberOfCharsInRight);
       }

       for (int occurrence : occurrencesMap.values()) {
           if (occurrence != 0) {
               return false;
           }
       }
       
       return true;
}
```

# Call to above function from AnagramCheckerActivity:

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText firstWordEt = findViewById(R.id.first_word_et);
        final EditText secondWordEt = findViewById(R.id.second_word_et);
        Button anagramCheckBtn = findViewById(R.id.anagram_checker_btn);
        final TextView anagramCheckResultTv = findViewById(R.id.anagram_result_tv);

        anagramCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstWord = firstWordEt.getText().toString().trim();
                String secondWord = secondWordEt.getText().toString().trim();

                if (isAnagram(firstWord, secondWord)) {
                    anagramCheckResultTv.setText(getResources().getString(R.string.are_anagrams));
                } else {
                    anagramCheckResultTv.setText(getResources().getString(R.string.are_not_anagrams));
                }
            }
        });
    }
```

# Unit test code:

```java
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
```
