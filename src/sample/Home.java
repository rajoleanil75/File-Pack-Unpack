package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

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
        pack.setOnAction(event -> {
            Parent root = null;
            try {
                root= FXMLLoader.load(Pack.class.getResource("pack.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage=new Stage();
            stage.setTitle("File Pack Unpack");
            stage.setScene(new Scene(root,550,350));
            stage.setResizable(false);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }
}
