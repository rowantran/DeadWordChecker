package com.rowantran.deadwordchecker.app;

import com.rowantran.deadwordchecker.controller.CheckedEssay;
import com.rowantran.deadwordchecker.controller.EssayEntry;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeadWordChecker extends Application {
    public static String[] DEAD_WORDS;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/rowantran/deadwordchecker/view/EssayEntry.fxml"));
        Parent essayEntryView = loader.load();

        primaryStage.setTitle("Dead Word Checker");
        primaryStage.setScene(new Scene(essayEntryView, 300, 250));
        primaryStage.show();

        EssayEntry essayEntry = loader.getController();

        FXMLLoader checkEssayLoader = new FXMLLoader();
        checkEssayLoader.setLocation(getClass().getResource("/com/rowantran/deadwordchecker/view/CheckedEssay.fxml"));
        Parent checkEssayView = checkEssayLoader.load();

        Stage resultsStage = new Stage();
        resultsStage.setTitle("Dead Words Scanning Results");
        resultsStage.setScene(new Scene(checkEssayView, 400, 250));
        resultsStage.setX(primaryStage.getX() + primaryStage.getWidth());
        resultsStage.setY(primaryStage.getY());
        resultsStage.show();

        primaryStage.requestFocus();
        essayEntryView.requestFocus();

        CheckedEssay checkedEssay = checkEssayLoader.getController();
        checkedEssay.bindText(essayEntry.essay.scannedEssay);
    }

    public static void main(String[] args) {
        DEAD_WORDS = readStringArray("/com/rowantran/deadwordchecker/deadwords.txt");

        launch(args);
    }

    private static String[] readStringArray(String filename) {
        List<String> words = new ArrayList<>();
        Scanner scan = new Scanner(DeadWordChecker.class.getResourceAsStream(filename));

        while (scan.hasNext()) {
            words.add(scan.nextLine());
        }

        return words.toArray(new String[0]);
    }
}