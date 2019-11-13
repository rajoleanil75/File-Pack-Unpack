package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.beans.EventHandler;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Anil on 15/06/2018
 */
public class Home {
    @FXML
    private Label time;
    @FXML
    private Button pack, unpack;

    @FXML
    private void initialize()
    {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss a");
            LocalDateTime now = LocalDateTime.now();

            //please put label name instead of time below
            time.setText(dtf.format(now));
        }));
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();




        pack.setOnAction(event -> {
            Parent root = null;
            try {
                root= FXMLLoader.load(Pack.class.getResource("pack.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage=new Stage();
            stage.setTitle("File Pack");
            stage.setScene(new Scene(root,550,350));
            stage.setResizable(false);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

        unpack.setOnAction(event -> {
            try
            {
                Parent root = null;
                root = FXMLLoader.load(Unpack.class.getResource("unpack.fxml"));
                Stage stage = new Stage();
                stage.setTitle("File Pack");
                stage.setScene(new Scene(root, 550, 350));
                stage.setResizable(false);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}
