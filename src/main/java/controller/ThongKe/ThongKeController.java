package controller.ThongKe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ThongKeController {

    @FXML
    private Pane mainPane;

    public void cachLyModeClick(MouseEvent mouseEvent) throws IOException {
        Parent cachLyPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeCachLy.fxml"));
        mainPane.getChildren().add(cachLyPane);
    }

    public void nhanKhauModeClick(MouseEvent mouseEvent) throws IOException {
        Pane nhanKhauPane = FXMLLoader.load(getClass().getResource("/view/ThongKe/thongKeNhanKhau.fxml"));
        mainPane.getChildren().add(nhanKhauPane);
    }

    public void khaiBaoModeClick(MouseEvent mouseEvent) throws IOException {
        Pane khaiBaoPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeKhaiBao.fxml"));
        mainPane.getChildren().add(khaiBaoPane);
    }

    public void testCovidModeClick(MouseEvent mouseEvent) throws IOException {
        Pane testCovidPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeTestCovid.fxml"));
        mainPane.getChildren().add(testCovidPane);
    }
}
