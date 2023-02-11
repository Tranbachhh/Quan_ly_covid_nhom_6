package controller.NhanKhau;

import entity.NhanKhau;
import entity.TamTru;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;
import repository.TamTruRepository;
import repository.TamTruRepositoryImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class TamTruController {
    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    private TextField noiTamTruF;
    @FXML
    private TextField noiOTruocKiaF;
    @FXML
    private DatePicker tuNgayF;
    @FXML
    private DatePicker denNgayF;
    @FXML
    private TextArea lyDoF;
    int nhanKhauId;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static TamTruRepository TamTruRepo =new TamTruRepositoryImpl();

    public void setTamTru(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        nhanKhauId=nk.getId();
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save_tamTru(MouseEvent event) {
        String noiTamTru = noiTamTruF.getText();
        String noiOTruocKia = noiOTruocKiaF.getText();
        LocalDate tuNgay = tuNgayF.getValue();
        if (noiTamTru.isEmpty() || tuNgay==null ||noiOTruocKia.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();
        } else {
            if (String.valueOf(tuNgay).compareTo(String.valueOf(denNgayF.getValue()))>0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Trường ĐẾN NGÀY phải có thời gian sau trường TỪ NGÀY ");
                alert.showAndWait();
            }else{
                update();
                insert();
                Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
                alert_TC.setHeaderText(null);
                alert_TC.setContentText("Khai báo thành công");
                alert_TC.showAndWait();
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }
    }
    @FXML
    private void huy(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void update() {
        NhanKhauRepo.updateTrangThai("Tạm trú",nhanKhauId);
    }
    private void insert() {
        String denNgay="";
        if(denNgayF.getValue()!=null){
            denNgay=String.valueOf(denNgayF.getValue());
        }
        String lyDo="";
        if(lyDoF.getText()!=null){
            lyDo=lyDoF.getText();
        }
        TamTruRepo.insertTamTru(new TamTru(nhanKhauId,noiOTruocKiaF.getText(),noiTamTruF.getText(),
                String.valueOf(tuNgayF.getValue()),denNgay,lyDo));

    }
}
