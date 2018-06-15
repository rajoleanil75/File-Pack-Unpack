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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Anil on 15/06/2018
 */
public class Unpack {
    @FXML
    private Button dname, fname, cancel, unpack;
    @FXML
    private Label dnamelbl, fnamelbl, warning;

    private void unZIp(String zipFilePath, String destDir)
    {
        File dir = new File(destDir);
        if(!dir.exists())
            dir.mkdir();
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis .getNextEntry();
            while (ze != null)
            {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0)
                    fos.write(buffer, 0 ,len);
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

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
                unZIp(fnamelbl.getText(), dnamelbl.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File Unpack Successful");
                alert.setHeaderText(null);
                alert.setContentText("File Unpack Successful\n Location :"+dnamelbl.getText());
                alert.showAndWait();
            }
        });
    }
}
