package com.rowantran.deadwordchecker.controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class CheckedEssay {
    @FXML
    private TextArea checkedEssay;

    @FXML
    public void bindText(StringProperty results) {
        checkedEssay.textProperty().bind(results);
    }
}
