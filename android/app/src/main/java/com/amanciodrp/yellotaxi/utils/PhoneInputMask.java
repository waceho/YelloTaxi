package com.amanciodrp.yellotaxi.utils;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Locale;

public class PhoneInputMask implements TextWatcher {

    private static final int MAX_LENGTH = 9;
    private static final int MIN_LENGTH = 9;

    private String updatedText;
    private boolean editing;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        if (text.toString().equals(updatedText) || editing) return;

        String digits = text.toString().replaceAll("\\D", "");
        int length = digits.length();

        if (length <= MIN_LENGTH) {
            updatedText = digits;
            return;
        }

        if (length > MAX_LENGTH) {
            digits = digits.substring(0, MAX_LENGTH);
        }

        if (length <= 6) {
            String firstPart = digits.substring(0, 2);
            String secondPart = digits.substring(3, 5);
            String thirdPart = digits.substring(5, 7);

            updatedText = String.format(Locale.US, "%s-%s-%s", firstPart, secondPart, thirdPart);
        }
        else if (length <= 9){
            String firstPart = digits.substring(0, 2);
            String secondPart = digits.substring(2, 4);
            String thirdPart = digits.substring(4, 6);
            String forPart = digits.substring(6, 8);

            updatedText = String.format(Locale.US, "%s-%s-%s-%s", firstPart, secondPart, thirdPart, forPart);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editing) return;

        editing = true;

        editable.clear();
        editable.insert(0, updatedText);

        editing = false;
    }
}
