package com.rowantran.deadwordchecker.controller;

import com.rowantran.deadwordchecker.model.Essay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;


public class EssayEntry {
    @FXML
    private TextArea essayText;

    public Essay essay;

    @FXML
    public void initialize() {
        essay = new Essay(essayText.getText());
    }

    @FXML
    protected void handleCheckEssay(ActionEvent event) {
        essay.updateEssay(essayText.getText());
        essay.scanEssay();
    }
}
