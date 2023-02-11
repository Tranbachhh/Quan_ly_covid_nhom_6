package controller.CachLy;

import entity.CachLy;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class XemCachLyController {
    @FXML
    Label cmndLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    private TextField noiCachLyF;
    @FXML
    private TextField mucDoF;
    @FXML
    private DatePicker tuNgayF;
    @FXML
    private DatePicker denNgayF;
    @FXML
    private TextArea lyDoF;

    public void setThongTin(CachLy cl){
        cmndLabel.setText(cl.getCmnd());
        hoTenLabel.setText((cl.getHoTen()));
        noiCachLyF.setText(cl.getNoiCachLy());
        mucDoF.setText(cl.getMucDoCachLy());
        tuNgayF.setValue(LocalDate.parse(cl.getNgayBatDau().toString()));
        denNgayF.setValue(LocalDate.parse(cl.getNgayKetThuc().toString()));
        lyDoF.setText(cl.getLyDoCachLy());
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @SneakyThrows
    @FXML
    private void huy(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
