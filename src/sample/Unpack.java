package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Anil on 15/06/2018
 */
public class Unpack {
    @FXML
    private Button dname, fname, cancel, unpack;
    @FXML
    private Label dnamelbl, fnamelbl, warning;

    @FXML
    private void initialize()
    {
        dname.setOnAction(event -> {
            warning.setText("");
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Directory to Unpack file");
            Stage stage = new Stage();
            File file = directoryChooser.showDialog(stage);
            if(file == null)
            {
            }
            else {
                dnamelbl.setText(file.getAbsolutePath());
            }
        });
        fname.setOnAction(event -> {
            warning.setText("");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Pack file");
            Stage stage = new Stage();
            File file = fileChooser.showOpenDialog(stage);
            if(file == null)
            {
            }
            else {
                fnamelbl.setText(file.getAbsolutePath());
            }
        });
        cancel.setOnAction(event -> {
            Parent root = null;
            try {
                root= FXMLLoader.load(Home.class.getResource("home.fxml"));
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

        unpack.setOnAction(event -> {
            if(fnamelbl.getText().equals(""))
            {
                warning.setText("Please select file..!!!");
            }
            else if(dnamelbl.getText().equals(""))
            {
                warning.setText("Please select Directory..!!!");
            }
            else
            {
                warning.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File Pack Successful");
                alert.setHeaderText(null);
                alert.setContentText("File Unpack Successful\n Location :"+dnamelbl.getText());
                alert.showAndWait();
            }
        });
    }
}
