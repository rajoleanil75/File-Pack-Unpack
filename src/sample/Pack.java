package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Anil on 15/06/2018
 */
public class Pack {
    @FXML
    private Button pack, cancel,dname;
    @FXML
    private Label sname, warning;
    @FXML
    private TextField tname;
    private List<String> fileListInDir = new ArrayList<String>();

    private void populateFilesList(File dir) throws IOException
    {
        File[] files = dir.listFiles();
        for (File file : files)
        {
            if(file.isFile())
                fileListInDir.add(file.getAbsolutePath());
            else
                populateFilesList(file);
        }
    }
    private void zipDirectory(File dir, String zipDirName)
    {
        try
        {
            populateFilesList(dir);
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String filePath : fileListInDir)
            {
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1,filePath.length()));
                zos.putNextEntry(ze);
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while((len = fis.read(buffer)) > 0)
                    zos.write(buffer, 0, len);
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
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
            directoryChooser.setTitle("Select Directory to Pack");
            Stage stage = new Stage();
            File file = directoryChooser.showDialog(stage);
            if(file == null)
            {
            }
            else {
                sname.setText(file.getAbsolutePath());
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
        pack.setOnAction(event -> {
            if(sname.getText().equals(""))
            {
                warning.setText("Please select Directory..!!!");
            }
            else if(tname.getText().equals(""))
            {
                warning.setText("Please enter Target name..!!!");
            }
            else
            {
                warning.setText("");
                final File folder = new File(sname.getText());
                String packDirName = sname.getText()+"\\"+tname.getText();
                zipDirectory(folder, packDirName);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File Pack Successful");
                alert.setHeaderText(null);
                alert.setContentText("File Pack Successful\n Location :"+sname.getText()+"\\"+tname.getText());
                alert.showAndWait();
            }
        });
    }
}
