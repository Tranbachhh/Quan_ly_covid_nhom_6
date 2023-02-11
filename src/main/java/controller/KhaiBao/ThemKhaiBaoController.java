package controller.KhaiBao;

import entity.KhaiBao;
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
import repository.KhaiBaoRepository;
import repository.KhaiBaoRepositoryImpl;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;
import utility.SQLCommand;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ThemKhaiBaoController implements Initializable {

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
    private CheckBox trieuChungCheckbox;
    @FXML
    private CheckBox binhThuongCheckbox;
    @FXML
    private CheckBox hoCheckbox;
    @FXML
    private CheckBox sotCheckbox;
    @FXML
    private TextField khacF;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    Label cmndLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    DatePicker ngayKhaiBaoF;


    private String duLieuTraCuu="";
    private int idNguoiKhaiBao=-1;
    private String trieuChung="Không";
    private String tinhTrangSucKhoe="";
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static KhaiBaoRepository KhaiBaoRepo = new KhaiBaoRepositoryImpl();
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
        }
    }
    public void huy_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    @SneakyThrows
    @FXML
    private void save(MouseEvent event) {
        LocalDate ngayKhaiBao = ngayKhaiBaoF.getValue();
        if(trieuChungCheckbox.isSelected()){
            trieuChung="Có";
        }
        if(binhThuongCheckbox.isSelected()){
            tinhTrangSucKhoe="Bình thường";
        }
        if(hoCheckbox.isSelected()){
            if(tinhTrangSucKhoe.isEmpty()){
                tinhTrangSucKhoe="Ho";
            }else{
                tinhTrangSucKhoe=tinhTrangSucKhoe.concat(" ,Ho");
            }
        }
        if(sotCheckbox.isSelected()){
            if(tinhTrangSucKhoe.isEmpty()){
                tinhTrangSucKhoe="Sốt";
            }else{
                tinhTrangSucKhoe=tinhTrangSucKhoe.concat(" ,Sốt");
            }
        }
        if(!khacF.getText().isEmpty()){
            if(tinhTrangSucKhoe.isEmpty()){
                tinhTrangSucKhoe=khacF.getText();
            }else{
                tinhTrangSucKhoe=tinhTrangSucKhoe.concat(" ,").concat(khacF.getText());
            }
        }

        if (ngayKhaiBao==null || idNguoiKhaiBao==-1 || tinhTrangSucKhoe.isEmpty() ) {
            tinhTrangSucKhoe="";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();
            return;
        }else {
            insert();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Thêm khai báo thành công");
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
        KhaiBaoRepo.themKhaiBao(new KhaiBao(idNguoiKhaiBao,Date.valueOf(ngayKhaiBaoF.getValue()),trieuChung,tinhTrangSucKhoe));
    }

}

