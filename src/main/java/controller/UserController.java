package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import repository.UserRepository;
import repository.UserRepositoryImpl;
import utility.Message;
import view.Main;
import java.io.IOException;

public class UserController {
    @FXML
    private Button ExitButton;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Password;

    @FXML
    private Label ThatbaiMessage;

    static UserRepository UserRepository = new UserRepositoryImpl() ;

    public void ExitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    public void LoginButtonOnAction(ActionEvent event) throws IOException {
        if (UserName.getText().isBlank() == false && Password.getText().isBlank() == false) {
            if (UserRepository.Login(UserName.getText(), Password.getText())) {
                Stage stage = (Stage) LoginButton.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                stage.setTitle("Quản lý khu phố");
                stage.setScene(scene);
                stage.setWidth(1400);
                stage.setHeight(780);
                stage.setX(10);
                stage.setY(10);
                stage.show();
            } else {
                ThatbaiMessage.setText(Message.LoginFailed);
            }
        } else {
            ThatbaiMessage.setText(Message.LoginLostOfField);
        }
    }
}
