package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Anil on 15/06/2018
 */
public class Login {
    @FXML
    private javafx.scene.control.Button login,reset;
    @FXML
    private javafx.scene.control.TextField uname;
    @FXML
    private PasswordField pass;
    @FXML
    private Label warning;

    @FXML
    private void initialize()
    {
        reset.setOnAction(event ->
        {
            uname.setText("");
            pass.setText("");
        });
        login.setOnAction((event) ->
        {
            String name=uname.getText();
            String password= pass.getText();
            if (name.isEmpty())
            {
                warning.setText("Please enter Username..!!");
            }
            else if(password.isEmpty())
            {
                warning.setText("Please enter Password..!!");
            }
            else
            {
                try
                {
                    if(name.equals("admin") && password.equals("password"))
                    {
                        Parent root;
                        root= FXMLLoader.load(Home.class.getResource("home.fxml"));
                        Stage stage=new Stage();
                        stage.setTitle("File Pack Unpack");
                        stage.setScene(new Scene(root,550,350));
                        stage.setResizable(false);
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    }
                    else
                    {
                        warning.setText("Plese enter valid username and password");
                    }
                }
                catch (Exception e)
                {
                    warning.setText("Something went Wrong..!!");
                    System.out.print(e);
                }
            }

        });
    }
}
