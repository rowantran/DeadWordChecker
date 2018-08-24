package com.rowantran.deadwordchecker.model;

import com.rowantran.deadwordchecker.app.DeadWordChecker;
import javafx.beans.property.SimpleStringProperty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Essay {
    private String essay;

    public SimpleStringProperty scannedEssay;

    public Essay(String essay) {
        this.essay = essay;

        scannedEssay = new SimpleStringProperty();
    }

    public void updateEssay(String newEssay) {
        essay = newEssay;
    }

    public void scanEssay() {
        scannedEssay.set("");
        for (String search : DeadWordChecker.DEAD_WORDS) {
            Pattern p = Pattern.compile("\\b" + search + "\\b");
            Matcher m = p.matcher(essay);
            int count = 0;
            while (m.find()) {
                count++;
            }

            if (count == 1) {
                scannedEssay.set(scannedEssay.get() + count + " instance of \"" + search + "\"\n");
            } else if (count > 1) {
                scannedEssay.set(scannedEssay.get() + count + " instances of \"" + search + "\"\n");
            }
        }
    }
}
