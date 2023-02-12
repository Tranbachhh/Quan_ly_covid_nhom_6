package controller.TiemCovid;
import entity.CachLy;
import entity.TiemCovid;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.*;
import utility.SQLCommand;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
public class ThemTiemCovidController implements Initializable{
    @FXML
    private TableView<NhanKhau> table;
    @FXML
    private TableColumn<NhanKhau,String>  hoTenColumn;
    @FXML
    private TableColumn<NhanKhau,String>  ngaySinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  CMNDColumn;
    @FXML
    private TextField noiTiemF;
    @FXML
    private TextArea soMuiF;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private ComboBox<String> mucDoF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    Label cmndLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    DatePicker ngayBatDauF;
    private String duLieuTraCuu="";
    private String hoTen;
    private int idNguoiKhaiBao=-1;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static TiemCovidRepository TiemCovidRepo = new TiemCovidRepositoryImpl();
    @FXML
    ObservableList<NhanKhau>  nhanKhauList2 = FXCollections.observableArrayList();
    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {
        nhanKhauList2.clear();
        duLieuTraCuu=duLieuF.getText();
        String truongTraCuu = truongTraCuuF.getValue();
        try{
            if(truongTraCuu=="Họ tên"){
                nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_HO_TEN, duLieuTraCuu));
            } else if(truongTraCuu=="Chứng minh nhân dân"){
                nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_CMND,duLieuTraCuu));
            }else if(truongTraCuu=="Trạng thái"){
                nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_TRANG_THAI,duLieuTraCuu));
            }else if(truongTraCuu=="Ngày sinh"){
                nhanKhauList2.addAll(NhanKhauRepo.findNhanKhau(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_NGAY_SINH,duLieuTraCuu));
            }
            table.setItems(nhanKhauList2);
        }catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Bạn cần chọn trường tra cứu");
            alert.show();
            return;
        }
    }
    @FXML
    private void handleClickTableView(MouseEvent click) {
        NhanKhau userlist = table.getSelectionModel().getSelectedItem();
        if (userlist != null) {
            hoTenLabel.setText(userlist.getHoTen());
            idNguoiKhaiBao=userlist.getId();
            cmndLabel.setText(userlist.getCMND());
            hoTen = userlist.getHoTen();
        }
    }
    public void huy_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
    @SneakyThrows
    @FXML
    private void save(MouseEvent event) {
        LocalDate ngayBatDau = ngayBatDauF.getValue();
        if (ngayBatDau==null || idNguoiKhaiBao==-1||noiTiemF.getText().isEmpty()||soMuiF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();
            return;
        }else {
            insert();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Thêm bản ghi thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }


    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Họ tên","Chứng minh nhân dân","Trạng thái","Ngày sinh");
    @FXML
    private void loadData(){
        nhanKhauList2.clear();
        nhanKhauList2.addAll(NhanKhauRepo.loadDataNhanKhau());
        table.setItems(nhanKhauList2);
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    private void initCol(){
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("bieuDienNgaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<>("CMND"));
    }

    private void insert() {
        TiemCovidRepo.themTiemCoivd(new TiemCovid(1,idNguoiKhaiBao,hoTen,noiTiemF.getText(),Integer.parseInt(soMuiF.getText())));
    }
}
