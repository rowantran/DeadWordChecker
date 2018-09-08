package com.rowantran.deadwordchecker.controller;

import com.rowantran.deadwordchecker.model.Essay;
import com.rowantran.deadwordchecker.model.VocabList;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.util.prefs.Preferences;


public class EssayEntry {
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private TextArea essayText;
    @FXML
    private WebView checkedEssay;
    @FXML
    private ListView<String> vocabListDisplay;

    private Essay essay;
    private VocabList vocabList;

    private Preferences prefs;

    @FXML
    public void initialize() {
        prefs = Preferences.userNodeForPackage(EssayEntry.class);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15,
                Integer.parseInt(prefs.get("unit", "1")));
        spinner.setValueFactory(valueFactory);
        valueFactory.valueProperty().addListener((obs, oldVal, newVal) -> loadVocabList(newVal));

        essay = new Essay(essayText.getText());

        essayText.setStyle("-fx-font-family: 'Times New Roman', serif;" +
                "-fx-font-size: 12pt;");

        checkedEssay.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/rowantran/deadwordchecker/css/checkedessay.css").toString());

        loadVocabList(valueFactory.valueProperty().get());
    }

    private void loadVocabList(int unit) {
        vocabList = new VocabList(unit);

        vocabListDisplay.getItems().clear();
        vocabListDisplay.getItems().addAll(vocabList.list.keySet());
        vocabListDisplay.setCellFactory(CheckBoxListCell.forListView((String item) -> vocabList.list.get(item)));

        prefs.put("unit", Integer.toString(unit));
        handleCheckEssay(null);
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
