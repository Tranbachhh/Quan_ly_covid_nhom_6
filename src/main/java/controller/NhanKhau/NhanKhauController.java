package controller.NhanKhau;

import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.*;
import repository.NhanKhauRepository;
import utility.SQLCommand;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class NhanKhauController implements Initializable {
    @FXML
    private TableView<NhanKhau>table;
    @FXML
    private TableColumn<NhanKhau,Integer>  idColumn;
    @FXML
    private TableColumn<NhanKhau,String>  hoTenColumn;
    @FXML
    private TableColumn<NhanKhau,String>  ngaySinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  CMNDColumn;
    @FXML
    private TableColumn<NhanKhau,String>  trangThaiColumn;
    @FXML
    private Button themNhanKhau;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    ObservableList<NhanKhau>  nhanKhauList = FXCollections.observableArrayList();
    private String duLieuTraCuu="";
    private int id_NK;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static HoKhauRepository HoKhauRepo =new HoKhauRepositoryImpl();

    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {
        nhanKhauList.clear();
        duLieuTraCuu=duLieuF.getText();
        String truongTraCuu = truongTraCuuF.getValue();
        try{
            if(truongTraCuu.equals("Họ tên")){
                nhanKhauList.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_HO_TEN, duLieuTraCuu));
            } else if(truongTraCuu.equals("Chứng minh nhân dân")){
                nhanKhauList.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_CMND,duLieuTraCuu));
            }else if(truongTraCuu.equals("Trạng thái")){
                nhanKhauList.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_TRANG_THAI,duLieuTraCuu));
            }else if(truongTraCuu.equals("Ngày sinh")){
                nhanKhauList.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_NGAY_SINH,duLieuTraCuu));
            }
            table.setItems(nhanKhauList);
        }
        catch (NullPointerException ex){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Bạn cần chọn trường tra cứu");
                alert.show();
                return;
            }
    }

    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Họ tên","Chứng minh nhân dân","Trạng thái","Ngày sinh");
    @FXML
    private void loadDataNhanKhau() {
        nhanKhauList.clear();
        nhanKhauList.addAll(NhanKhauRepo.loadDataNhanKhau());
        table.setItems(nhanKhauList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("bieuDienNgaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataNhanKhau();
    }

    public void changScenceThemNhanKhau(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/themNhanKhau.fxml"));
        Parent thongTinNK = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Thông tin nhân khẩu");
        Scene scene = new Scene(thongTinNK);
        stage.setScene(scene);
        stage.show();
    }

    public void detailNhanKhau(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/chiTietNhanKhau.fxml"));
        Parent ChiTietNK = loader.load();
        ChiTietNhanKhauController controllerChiTietNhanKhau = loader.getController();
        NhanKhau selectedNhanKhau = table.getSelectionModel().getSelectedItem();
        if (selectedNhanKhau == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controllerChiTietNhanKhau.setNhanKhau(selectedNhanKhau);
        Stage stage = new Stage();
        stage.setTitle("Thông tin nhân khẩu");
        Scene scene = new Scene(ChiTietNK);
        stage.setScene(scene);
        stage.show();
    }

    public void chinhSuaNK(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/chinhSuaNhanKhau.fxml"));
        Parent chinhSuaNKView = loader.load();
        ChinhSuaNhanKhauController controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.setChinhSuaNK(selected);
        Stage stage = new Stage();
        stage.setTitle("CHỈNH SỬA NHÂN KHẨU");
        Scene scene = new Scene(chinhSuaNKView);
        stage.setScene(scene);
        stage.show();
    }

    public void XoaNK(ActionEvent e) throws IOException {
            NhanKhau nhankhau = table.getSelectionModel().getSelectedItem();
            if (nhankhau == null) {
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Thông báo!");
                m.setHeaderText("Không nhân khẩu nào được chọn.");
                m.setContentText("Vui lòng chọn lại.");
                m.show();
                return;
            }
            id_NK=nhankhau.getId();
            if(HoKhauRepo.check_chu_ho(id_NK)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Bạn đang xóa một chủ hộ, vui lòng đổi chủ hộ trước khi xóa!");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa nhân khẩu");
            alert.setHeaderText("Bạn có thực sự muốn xóa nhân khẩu này ?");
            alert.setContentText("Việc xóa nhân khẩu sẽ làm mất tất cả các dữ liệu liên quan đến nhân khẩu.");
            Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {}
                else if (option.get() == ButtonType.OK) {
                    NhanKhauRepo.deleteNhanKhau(id_NK);
                    loadDataNhanKhau();
                } else if (option.get() == ButtonType.CANCEL) {}

    }

    public void chuyenNK() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/chuyenNhanKhau.fxml"));
        Parent chinhSuaNKView = loader.load();
        ChuyenNhanKhauController controller = loader.getController();
        NhanKhau selectedNhanKhau = table.getSelectionModel().getSelectedItem();
        if (selectedNhanKhau == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selectedNhanKhau.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");
            m2.show();
            return;
        }else{
            controller.setChuyenNhanKhau(selectedNhanKhau);
            Stage stage = new Stage();
            stage.setTitle("CHUYỂN NHÂN KHẨU");
            Scene scene = new Scene(chinhSuaNKView);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void tamVang() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/tamVang.fxml"));
        Parent tamVangNK = loader.load();
        TamVangController controller = loader.getController();
        NhanKhau selectedNhanKhau = table.getSelectionModel().getSelectedItem();
        if (selectedNhanKhau == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selectedNhanKhau.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");
            m2.show();
            return;

        }else{
            controller.setTamVang(selectedNhanKhau);
            Stage stage = new Stage();
            stage.setTitle("QUẢN LÝ TẠM VẮNG");
            Scene scene = new Scene(tamVangNK);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void khaiTu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/khaiTu.fxml"));
        Parent khaiTuNK = loader.load();
        KhaiTuController controller = loader.getController();
        NhanKhau selectedNhanKhau = table.getSelectionModel().getSelectedItem();
        if (selectedNhanKhau == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selectedNhanKhau.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");
            m2.show();
            return;
        }else{
            controller.setKhaiTu(selectedNhanKhau);
            Stage stage = new Stage();
            stage.setTitle("KHAI TỬ");
            Scene scene = new Scene(khaiTuNK, 1100, 700);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void tamTru() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/tamTru.fxml"));
        Parent tamTruNK = loader.load();
        TamTruController controller = loader.getController();
        NhanKhau selectedNhanKhau = table.getSelectionModel().getSelectedItem();

        if (selectedNhanKhau == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selectedNhanKhau.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");
            m2.show();
            return;

        }else{
            controller.setTamTru(selectedNhanKhau);
            Stage stage = new Stage();
            stage.setTitle("QUẢN LÝ TẠM TRÚ");
            Scene scene = new Scene(tamTruNK);
            stage.setScene(scene);
            stage.show();
        }
    }
}
