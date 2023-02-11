package controller.NhanKhau;

import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;

import java.time.LocalDate;

public class ChinhSuaNhanKhauController  {
    @FXML
    private TextField hoTenLabel;
    @FXML
    private DatePicker ngaySinhLabel;
    @FXML
    private TextField noiSinhLabel;
    @FXML
    private ComboBox combGioiTinh;
    @FXML
    private TextField nguyenQuanLabel;
    @FXML
    private TextField danTocLabel;
    @FXML
    private TextField tonGiaoLabel;
    @FXML
    private TextField quocTichLabel;
    @FXML
    private TextField ngheNghiepLabel;
    @FXML
    private TextField noiLamViecLabel;
    @FXML
    private TextField CMNDLabel;
    @FXML
    private TextField noiCapLabel;
    @FXML
    private DatePicker ngayCapLabel;
    @FXML
    private DatePicker chuyenDenNgayLabel;
    @FXML
    private TextField noiThuongTruTruocLabel;
    private int id_NK;
    String gioiTinhC=null;
    NhanKhau f=new NhanKhau();
    static NhanKhauRepository NhanKhauRepo= new NhanKhauRepositoryImpl();


    public void setChinhSuaNK(NhanKhau nk){
        id_NK=nk.getId();
        ObservableList<String> listGioiTinh2 = FXCollections.observableArrayList("Nam","Nữ");
        combGioiTinh.setItems(listGioiTinh2);
        loadData_ChinhSua();
    }

    @SneakyThrows
    @FXML
    private void save_ChinhSua(MouseEvent event) {
        String hoTen = hoTenLabel.getText();
        String ngaySinh = String.valueOf(ngaySinhLabel.getValue());
        String noiSinh = noiSinhLabel.getText();
        String nguyenQuan = nguyenQuanLabel.getText();
        String danToc = danTocLabel.getText();
        String tonGiao = tonGiaoLabel.getText();
        String quocTich = quocTichLabel.getText();

        if (hoTen.isEmpty() || ngaySinh.isEmpty() || quocTich.isEmpty() || tonGiao.isEmpty()||
                danToc.isEmpty()||noiSinh.isEmpty()||nguyenQuan.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();
        } else {
            update();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Chỉnh sửa thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    private void selectGioiTinh(ActionEvent event) {
        gioiTinhC = combGioiTinh.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    public void loadData_ChinhSua() {
        f= NhanKhauRepo.chiTietNhanKhauData(id_NK);

        hoTenLabel.setText(f.getHoTen());
        String ngaySinh=f.getBieuDienNgaySinh().substring(6)+"-"+f.getBieuDienNgaySinh().substring(3,5)+"-"+f.getBieuDienNgaySinh().substring(0,2);
        ngaySinhLabel.setValue(LocalDate.parse(ngaySinh));
        noiSinhLabel.setText(f.getNoiSinh());
        combGioiTinh.getSelectionModel().select(f.getGioiTinh());
        gioiTinhC=f.getGioiTinh();
        nguyenQuanLabel.setText(f.getNguyenQuan());
        danTocLabel.setText(f.getDanToc());
        tonGiaoLabel.setText(f.getTonGiao());
        quocTichLabel.setText(f.getQuocTich());
        ngheNghiepLabel.setText(f.getNgheNghiep());
        noiLamViecLabel.setText(f.getNoiLamViec());
        CMNDLabel.setText(f.getCMND());
        if(f.getBieuDienNgayCap()!=""){
            String ngayCap=f.getBieuDienNgayCap().substring(6)+"-"+f.getBieuDienNgayCap().substring(3,5)+"-"+f.getBieuDienNgayCap().substring(0,2);
            ngayCapLabel.setValue(LocalDate.parse(ngayCap));
        }
        noiCapLabel.setText(f.getNoiCap());
        if(f.getBieuDienChuyenDenNgay()!=""){
            String ngayChuyenDen=f.getBieuDienChuyenDenNgay().substring(6)+"-"+f.getBieuDienChuyenDenNgay().substring(3,5)+"-"+f.getBieuDienChuyenDenNgay().substring(0,2);
            chuyenDenNgayLabel.setValue(LocalDate.parse(ngayChuyenDen));
        }
        noiThuongTruTruocLabel.setText(f.getNoiThuongTruTruoc());
    }

    private void update() {
        String ngayCap="";
        if(ngayCapLabel.getValue()!=null){
            ngayCap=String.valueOf(ngayCapLabel.getValue());
        }
        String chuyenDenNgay="";
        if(chuyenDenNgayLabel.getValue()!=null){
            chuyenDenNgay=String.valueOf(chuyenDenNgayLabel.getValue());
        }
        NhanKhauRepo.updateNhanKhau(id_NK,new NhanKhau(hoTenLabel.getText(),String.valueOf(ngaySinhLabel.getValue()),noiSinhLabel.getText(),
                gioiTinhC,nguyenQuanLabel.getText(),danTocLabel.getText(),tonGiaoLabel.getText(),quocTichLabel.getText(),
                ngheNghiepLabel.getText(),noiLamViecLabel.getText(),CMNDLabel.getText(),ngayCap,noiCapLabel.getText(),chuyenDenNgay,
                noiThuongTruTruocLabel.getText()));
    }
    @FXML
    private void huy(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
