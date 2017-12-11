package it.minoranza.minorgroup.minorserver;

import it.minoranza.minorgroup.minorserver.control.RunVirtualCommunication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Principale extends Application{

    public static ArrayList<RunVirtualCommunication> dealers,clients;

    public static void main(String[] args) {
        launch(args);

        dealers=new ArrayList<>();
        clients=new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final Parent root= FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setScene(new Scene(root,500,350));
        primaryStage.setTitle("Server - MinorGroup");
        primaryStage.show();
    }
}