package com.rowantran.deadwordchecker.model;

import com.rowantran.deadwordchecker.app.DeadWordChecker;

public class Essay {
    private String essay;

    public String scannedEssay;

    public Essay(String essay) {
        this.essay = essay;
    }

    public void updateEssay(String newEssay) {
        essay = newEssay;
    }

    public void scanEssay() {
        scannedEssay = essay;
        for (String search : DeadWordChecker.DEAD_WORDS) {
            scannedEssay = scannedEssay.replaceAll("\\b" + search + "\\b", "<mark>" + search + "</mark>");
        }
    }
}
