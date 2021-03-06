package it.minoranza.minorgroup.minordealer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principale  extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage stage;
    public static String dealerName;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setScene(new Scene(root, 610, 500));
        primaryStage.setTitle("MinorDealer");
        primaryStage.show();

        stage=primaryStage;
    }
}
