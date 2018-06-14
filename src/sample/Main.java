package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("File Pack Unpack");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(7),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.hide();

                        Parent root = null;
                        try {
                            root= FXMLLoader.load(Login.class.getResource("Login.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage=new Stage();
                        stage.setTitle("File Pack Unpack");
                        Screen screen=Screen.getPrimary();
                        Rectangle2D bounds=screen.getVisualBounds();
                        stage.setScene(new Scene(root,500,350));
                        stage.show();
                    }
                }));
        timeline.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
