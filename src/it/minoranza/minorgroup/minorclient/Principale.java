package it.minoranza.minorgroup.minorclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Principale extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{

        final FXMLLoader loader=new FXMLLoader(getClass().getResource("view/listeningserver.fxml"));
        final Parent root = loader.load();

        primaryStage.setTitle("Collegamento al server - MinorGroup");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();

    }

}
