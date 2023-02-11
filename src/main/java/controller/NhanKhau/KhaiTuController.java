package controller.NhanKhau;

import entity.KhaiTu;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.KhaiTuRepository;
import repository.KhaiTuRepositoryImpl;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;
import utility.SQLCommand;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class KhaiTuController implements Initializable {

    @FXML
    private TableView<NhanKhau> table;
    @FXML
    private TableColumn<NhanKhau,Integer> idColumn;
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
    private ComboBox truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    DatePicker ngayMatF;
    @FXML
    DatePicker ngayKhaiBaoF;
    @FXML
    TextArea lyDoF;
    @FXML
    Label nguoiKhaiBaoF;
    @FXML
    Label ngaySinhKB;

    private String truongTraCuu=null;
    private String duLieuTraCuu=null;
    private int idNguoiMat=-1;
    private int idNguoiKhaiBao=-1;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static KhaiTuRepository KhaiTuRepo =new KhaiTuRepositoryImpl();
    @FXML
    ObservableList<NhanKhau>  nhanKhauList2 = FXCollections.observableArrayList();
    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {
        nhanKhauList2.clear();
        truongTraCuu = truongTraCuuF.getSelectionModel().getSelectedItem().toString();
        duLieuTraCuu=duLieuF.getText();
        if(truongTraCuu=="Họ tên"){
            nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_HO_TEN, duLieuTraCuu));
        } else if(truongTraCuu=="Chứng minh nhân dân"){
            nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_CMND,duLieuTraCuu));
        }else if(truongTraCuu=="Trạng thái"){
            nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_TRANG_THAI,duLieuTraCuu));
        }else if(truongTraCuu=="Ngày sinh"){
            nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_NGAY_SINH,duLieuTraCuu));
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Bạn cần chọn trường tra cứu");
            alert.showAndWait();
        }
        table.setItems(nhanKhauList2);
    }
    @FXML
    private void handleClickTableView(MouseEvent click) {
        NhanKhau userlist = table.getSelectionModel().getSelectedItem();
        if (userlist != null) {
            nguoiKhaiBaoF.setText(userlist.getHoTen());
            ngaySinhKB.setText(userlist.getBieuDienNgaySinh());
            idNguoiKhaiBao=userlist.getId();
        }
    }

    @SneakyThrows
    @FXML
    private void save_khaiTu(MouseEvent event) {
        LocalDate ngayMat = ngayMatF.getValue();
        LocalDate ngayKhaiBao = ngayKhaiBaoF.getValue();
        String hoTenNguoiKhaiBao = nguoiKhaiBaoF.getText();

        if (ngayMat==null||ngayKhaiBao==null || hoTenNguoiKhaiBao.isEmpty() ||idNguoiKhaiBao==-1||idNguoiMat==-1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();
        }else if(idNguoiKhaiBao==idNguoiMat){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Người khai trùng với người mất");
            alert.setContentText("Vui lòng chọn lại");
            alert.showAndWait();
        } else {
            insert();
            update();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Thêm thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Họ tên","Chứng minh nhân dân","Trạng thái","Ngày sinh");
        truongTraCuuF.setItems(listTruongTraCuu);
        loadData();
    }

    public void setKhaiTu(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        idNguoiMat=nk.getId();
    }
    @FXML
    private void refreshTable() {
        nhanKhauList2.clear();
        nhanKhauList2.addAll(NhanKhauRepo.loadDataNhanKhau());
        table.setItems(nhanKhauList2);
    }

    private void loadData(){
        refreshTable();
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("bieuDienNgaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        table.setItems(nhanKhauList2);
    }

    private void insert() {
        String lyDoMat="";
        if(lyDoF.getText()!=""){
            lyDoMat=lyDoF.getText();
        }
        KhaiTuRepo.insertKhaiTu(new KhaiTu(idNguoiMat,idNguoiKhaiBao,String.valueOf(ngayKhaiBaoF.getValue()),
                String.valueOf(ngayMatF.getValue()),lyDoMat));
    }

    private void update() {
        NhanKhauRepo.updateTrangThai("Đã mất",idNguoiMat);
    }
}
