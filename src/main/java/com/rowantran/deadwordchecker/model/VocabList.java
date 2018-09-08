package com.rowantran.deadwordchecker.model;

import com.rowantran.deadwordchecker.DeadWordChecker;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.LinkedHashMap;
import java.util.Map;

public class VocabList {
    public Map<String, SimpleBooleanProperty> list;

    public VocabList(int unit) {
        list = new LinkedHashMap<>();

        try {
            String filename = "/com/rowantran/deadwordchecker/vocablists/unit" + unit + ".txt";
            String[] words = DeadWordChecker.readStringArray(filename);
            for (String word : words) {
                list.put(word, new SimpleBooleanProperty(false));
            }
        } catch (NullPointerException e) {
            list.put("List does not exist", new SimpleBooleanProperty(false));
        }
    }
}
