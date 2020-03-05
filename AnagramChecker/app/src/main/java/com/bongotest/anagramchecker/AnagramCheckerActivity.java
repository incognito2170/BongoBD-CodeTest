package com.bongotest.anagramchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnagramCheckerActivity extends AppCompatActivity {

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
}
