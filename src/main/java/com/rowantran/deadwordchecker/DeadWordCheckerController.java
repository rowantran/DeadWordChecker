package com.rowantran.deadwordchecker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class DeadWordCheckerController {
    @FXML
    private TextArea essay;

    @FXML
    protected void handleCheckEssay(ActionEvent event) {
        DeadWordChecker.scanEssay(essay.getText());
    }
}
