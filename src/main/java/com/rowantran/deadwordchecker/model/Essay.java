package com.rowantran.deadwordchecker.model;

import com.rowantran.deadwordchecker.DeadWordChecker;

public class Essay {
    private String essay;

    public String scannedEssay;

    public Essay(String essay) {
        this.essay = essay;
    }

    public void updateEssay(String newEssay) {
        essay = newEssay;
    }

    public void scanEssay(VocabList list) {
        for (String word : list.list.keySet()) {
            if (essay.contains(word)) {
                list.list.get(word).set(true);
            } else {
                list.list.get(word).set(false);
            }
        }

        scannedEssay = essay;
        for (String search : DeadWordChecker.DEAD_WORDS) {
            scannedEssay = scannedEssay.replaceAll("\\b" + search + "\\b", "<mark>" + search + "</mark>");
        }

        for (String word : list.list.keySet()) {
            scannedEssay = scannedEssay.replaceAll("\\b" + word + "\\b", "<b>" + word + "</b>");
        }
    }
}
