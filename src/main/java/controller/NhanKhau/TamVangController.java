package controller.NhanKhau;

import entity.NhanKhau;
import entity.TamVang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;
import repository.TamVangRepository;
import repository.TamVangRepositoryImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TamVangController implements Initializable {
    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    private TextField noiTamTruF;
    @FXML
    private DatePicker tuNgayF;
    @FXML
    private DatePicker denNgayF;
    @FXML
    private TextArea lyDoF;
    int nhanKhauId;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static TamVangRepository TamVangRepo =new TamVangRepositoryImpl();

    public void setTamVang(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        nhanKhauId=nk.getId();
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save(MouseEvent event) {
        String noiTamTru = noiTamTruF.getText();
        LocalDate tuNgay = tuNgayF.getValue();
        if (noiTamTru.isEmpty() || tuNgay==null  ) {
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
        NhanKhauRepo.updateTrangThai("Tạm vắng",nhanKhauId);
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
        TamVangRepo.insertTamVang(new TamVang(nhanKhauId,noiTamTruF.getText(),String.valueOf(tuNgayF.getValue()),denNgay,lyDo));
    }
}
