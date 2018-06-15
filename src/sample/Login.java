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

import static java.lang.System.exit;

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
    private Label warning, chance;

    private static int cnt = 3;

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
            if(cnt == 0)
                exit(0);
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
                        chance.setText("Remaining chances : "+cnt);
                        cnt--;
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
