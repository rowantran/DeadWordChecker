package com.rowantran.deadwordchecker;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadWordChecker extends Application {
    private static String[] DEAD_WORDS;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dead Word Checker");

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        TextArea essay = new TextArea();
        essay.setPromptText("Paste essay here...");
        essay.setWrapText(true);
        root.getChildren().add(essay);

        Button checkEssay = new Button();
        checkEssay.setText("Check essay");
        checkEssay.setOnAction(event -> scanEssay(essay.getText()));
        root.getChildren().add(checkEssay);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        DEAD_WORDS = readStringArray("deadwords.txt");

        launch(args);
    }

    private void scanEssay(String essay) {
        String results = "";
        for (String search : DEAD_WORDS) {
            Pattern p = Pattern.compile("\\b" + search + "\\b");
            Matcher m = p.matcher(essay);
            int count = 0;
            while (m.find()) {
                count++;
            }

            if (count == 1) {
                results += count + " instance of \"" + search + "\"\n";
            } else if (count > 1) {
                results += count + " instances of \"" + search + "\"\n";
            }
        }

        Stage resultsStage = new Stage();
        resultsStage.setTitle("Dead Words Scanning Results");

        StackPane root = new StackPane();

        TextArea resultsDisplay = new TextArea(results);
        resultsDisplay.setWrapText(true);
        root.getChildren().add(resultsDisplay);

        resultsStage.setScene(new Scene(root, 400, 250));
        resultsStage.show();
    }

    private static String[] readStringArray(String filename) {
        List<String> words = new ArrayList<>();
        Scanner scan = new Scanner(DeadWordChecker.class.getClassLoader().getResourceAsStream(filename));

        while (scan.hasNext()) {
            words.add(scan.nextLine());
        }

        return words.toArray(new String[0]);
    }
}