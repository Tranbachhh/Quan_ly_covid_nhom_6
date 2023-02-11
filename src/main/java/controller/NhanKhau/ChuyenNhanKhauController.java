package controller.NhanKhau;

import entity.ChuyenNhanKhau;
import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.ChuyenNhanKhauRepository;
import repository.ChuyenNhanKhauRepositoryImpl;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ChuyenNhanKhauController {

    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    private TextField noiChuyenDenF;
    @FXML
    private DatePicker ngayChuyenDiF;
    @FXML
    private TextArea ghiChuF;
    int nhanKhauId;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static ChuyenNhanKhauRepository ChuyenNhanKhauRepo= new ChuyenNhanKhauRepositoryImpl();

    public void setChuyenNhanKhau(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        nhanKhauId=nk.getId();
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save_chuyenDi(MouseEvent event) {
        String noiChuyenDen = noiChuyenDenF.getText();
        LocalDate ngayChuyenDi = ngayChuyenDiF.getValue();
        if (noiChuyenDen.isEmpty() || ngayChuyenDi==null  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();
        } else {
            update();
            insert();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Chuyển nhân khẩu thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
    private void update() {
        NhanKhauRepo.updateTrangThai("Đã chuyển đi", nhanKhauId);
    }
    private void insert() {
        String ghiChu="";
        if(ghiChuF.getText()!=null){
            ghiChu=ghiChuF.getText();
        }
        ChuyenNhanKhauRepo.insertChuyenNhanKhau(new ChuyenNhanKhau(nhanKhauId,String.valueOf(ngayChuyenDiF.getValue()),
                noiChuyenDenF.getText(),ghiChu ));
    }

    public void huy(MouseEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}