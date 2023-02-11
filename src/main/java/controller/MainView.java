package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import view.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane tieuDePane;

    @FXML
    private Label tieuDeText;

    @FXML
    private Button trangChuButton;

    @FXML
    private Button nhanKhauButton;

    @FXML
    private Button hoKhauButton;

    @FXML
    private Button xetNghiemButton;

    @FXML
    private Button khaiBaoButton;

    @FXML
    private Button cachLyButton;

    @FXML
    private Button thongKeButton;

    @FXML
    private Button dangXuatButton;

    @FXML
    private Pane mainPane;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("trangChu.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void resetButtonBackground() {
        trangChuButton.setStyle("-fx-background-color: #2F333D;");
        nhanKhauButton.setStyle("-fx-background-color: #2F333D;");
        hoKhauButton.setStyle("-fx-background-color: #2F333D;");
        xetNghiemButton.setStyle("-fx-background-color: #2F333D;");
        khaiBaoButton.setStyle("-fx-background-color: #2F333D;");
        cachLyButton.setStyle("-fx-background-color: #2F333D;");
        thongKeButton.setStyle("-fx-background-color: #2F333D;");

    }

    public void trangChuButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        trangChuButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("trangChu.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void nhanKhauButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        nhanKhauButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(getClass().getResource("/view/NhanKhau/NhanKhauController.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void hoKhauButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        hoKhauButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(getClass().getResource("/view/HoKhau/hoKhau.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void thongKeButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        thongKeButton.setStyle("-fx-background-color: #757C95;");

        Parent trangChuPane =  FXMLLoader.load(getClass().getResource("/view/ThongKe/thongKe.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void khaiBaoButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        khaiBaoButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(getClass().getResource("/view/KhaiBao/KhaiBaoController.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void testCovidButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        xetNghiemButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(getClass().getResource("/view/TestCovid/TestCovidController.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void cachLyButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        cachLyButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(getClass().getResource("/view/CachLy/CachLyController.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void dangXuatButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dangXuatButton.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Đăng nhập");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(620);
        stage.setHeight(440);
        stage.show();
    }
}
