package com.rowantran.deadwordchecker.app;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/rowantran/deadwordchecker/img/icon.png")));
        primaryStage.setScene(new Scene(essayEntryView, 800, 520));
        primaryStage.setOnCloseRequest((WindowEvent e) -> Platform.exit());
        primaryStage.show();

        // Request focus in main window, outside of text entry field
        essayEntryView.requestFocus();
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