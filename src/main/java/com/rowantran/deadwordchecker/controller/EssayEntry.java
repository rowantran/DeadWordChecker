package com.rowantran.deadwordchecker.controller;

import com.rowantran.deadwordchecker.model.Essay;
import com.rowantran.deadwordchecker.model.VocabList;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;


public class EssayEntry {
    @FXML
    private TextArea essayText;
    @FXML
    private WebView checkedEssay;
    @FXML
    private ListView<String> vocabListDisplay;

    private Essay essay;
    private VocabList vocabList;

    @FXML
    public void initialize() {
        essay = new Essay(essayText.getText());

        vocabList = new VocabList(3);

        vocabListDisplay.getItems().addAll(vocabList.list.keySet());
        vocabListDisplay.setCellFactory(CheckBoxListCell.forListView((String item) -> vocabList.list.get(item)));

        essayText.setStyle("-fx-font-family: 'Times New Roman', serif;" +
                "-fx-font-size: 12pt;");

        checkedEssay.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/rowantran/deadwordchecker/css/checkedessay.css").toString());
    }

    @FXML
    protected void handleCheckEssay(KeyEvent event) {
        essay.updateEssay(essayText.getText());
        essay.scanEssay(vocabList);

        updateText(essay.scannedEssay);
    }

    private void updateText(String text) {
        checkedEssay.getEngine().loadContent("<html><body contentEditable=\"false\">" +
                text +
                "</body></html>");
    }
}
