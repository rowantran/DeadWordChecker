package com.rowantran.deadwordchecker.controller;

import com.rowantran.deadwordchecker.model.Essay;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;


public class EssayEntry {
    @FXML
    private TextArea essayText;

    @FXML
    private WebView checkedEssay;

    private Essay essay;

    @FXML
    public void initialize() {
        essay = new Essay(essayText.getText());

        essayText.setStyle("-fx-font-family: 'Times New Roman', serif;" +
                "-fx-font-size: 12pt;");

        checkedEssay.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/rowantran/deadwordchecker/css/checkedessay.css").toString());
    }

    @FXML
    protected void handleCheckEssay(KeyEvent event) {
        essay.updateEssay(essayText.getText());
        essay.scanEssay();

        updateText(essay.scannedEssay);
    }

    private void updateText(String text) {
        checkedEssay.getEngine().loadContent("<html><body contentEditable=\"false\">" +
                text +
                "</body></html>");
    }
}
