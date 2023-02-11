package controller.NhanKhau;

import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanKhauRepositoryImpl;
import repository.NhanKhauRepository;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ThemNhanKhauController implements Initializable {
    @FXML
    private TextField hoTenF;
    @FXML
    private TextField noiCapF;
    @FXML
    private DatePicker ngaySinhF;
    @FXML
    private DatePicker ngayCapF;
    @FXML
    private DatePicker chuyenDenNgayF;
    @FXML
    private TextField noiSinhF;
    @FXML
    private ComboBox comb;
    @FXML
    private TextField nguyenQuanF;
    @FXML
    private TextField danTocF;
    @FXML
    private TextField tonGiaoF;
    @FXML
    private TextField quocTichF;
    @FXML
    private TextField ngheNghiepF;
    @FXML
    private TextField noiLamViecF;
    @FXML
    private TextField CMNDF;
    @FXML
    private TextField noiThuongTruTruocF;


    String gioiTinhC="";
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam","Nữ");
        comb.setItems(listGioiTinh);
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save(MouseEvent event) {

        String hoTen = hoTenF.getText();
        LocalDate ngaySinh = ngaySinhF.getValue();
        String noiSinh = noiSinhF.getText();
        String nguyenQuan = nguyenQuanF.getText();
        String danToc = danTocF.getText();
        String tonGiao = tonGiaoF.getText();
        String quocTich = quocTichF.getText();
        String cmnd = CMNDF.getText();

        if(!cmnd.isEmpty()){
            try{
                int test_cmnd = Integer.parseInt(cmnd);
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Trường CMND không thoả mãn");
                alert.showAndWait();
                return;
            }
        }
        if (hoTen.isEmpty() || ngaySinh==null || quocTich.isEmpty() || tonGiao.isEmpty()||
                danToc.isEmpty()||noiSinh.isEmpty()||nguyenQuan.isEmpty()||gioiTinhC=="") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();
        } else {
            insert();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Thêm thành công");
            alert_TC.showAndWait();
            gioiTinhC="";
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void Select(ActionEvent event) {
        gioiTinhC = comb.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void huy(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void insert() {
        String trangThaiMacDinh="Thường trú";
        //Xử lý dữ liệu đầu vào
        String hoten = hoTenF.getText();
        String gioiTinh = gioiTinhC;
        String ngaySinh= String.valueOf(ngaySinhF.getValue());
        String noiSinh = noiSinhF.getText();
        String CMND ="";
        if (CMNDF.getText()!="" ){
            CMND=CMNDF.getText();
        }
        String danToc = danTocF.getText();
        String tonGiao = tonGiaoF.getText();
        String nguyenQuan = nguyenQuanF.getText();
        String quocTich = quocTichF.getText();
        String trangThai = trangThaiMacDinh;
        String noiThuongTruTruoc="";
        if (noiThuongTruTruocF.getText()!="" ){
            noiThuongTruTruoc=noiThuongTruTruocF.getText();
        }
        String ngheNghiep="";
        if (ngheNghiepF.getText()!="" ){
            ngheNghiep=ngheNghiepF.getText();
        }
        String noiLamViec="";
        if (noiLamViecF.getText()!="" ){
            noiLamViec=noiLamViecF.getText();
        }
        String noiCap="";
        if (noiCapF.getText()!="" ){
            noiCap=noiCapF.getText();
        }
        String ngayCap="";
        if (ngayCapF.getValue()!=null ){
            ngayCap=String.valueOf(ngayCapF.getValue());
        }
        String chuyenDenNgay="";
        if (chuyenDenNgayF.getValue()!=null ){
                chuyenDenNgay=String.valueOf(chuyenDenNgayF.getValue());
        }

        //call repository
        NhanKhauRepo.themNhanKhau(new NhanKhau(hoten,ngaySinh,noiSinh,gioiTinh,
                nguyenQuan,danToc,tonGiao,quocTich,ngheNghiep,noiLamViec,
                CMND,ngayCap,noiCap,chuyenDenNgay,noiThuongTruTruoc,trangThai));
    }

}
