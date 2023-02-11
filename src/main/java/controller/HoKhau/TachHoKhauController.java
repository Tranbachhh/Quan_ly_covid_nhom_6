package controller.HoKhau;

import entity.HoKhau;
import entity.HoKhauNhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repository.HoKhauRepositoryImpl;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class TachHoKhauController implements Initializable {

    @FXML
    private Label ho_ten_chu_ho_hien_tai;
    @FXML
    private Label ho_ten_chu_ho_moi;
    @FXML
    private TextField dia_chi_moi;
    @FXML
    private TextField tinh_thanh_moi;
    @FXML
    private TextField quan_huyen_moi;
    @FXML
    private TextField phuong_xa_moi;

    private String ngay_tao_moi;

    @FXML
    private DatePicker ngay_tao_moi_datepicker;

    private String NTMgetText(){
        return this.ngay_tao_moi;
    }

    private void NTMsetText(String s){
        this.ngay_tao_moi = s;
    }

    @FXML
    private TableView<HoKhauNhanKhau> hk_hien_tai_tab;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hoten_hien_tai_col;
    @FXML
    private TableColumn<HoKhauNhanKhau, Date> ngaysinh_hien_tai_col;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> quanhe_hien_tai_col;

    private ObservableList<HoKhauNhanKhau> list_hk_hien_tai = FXCollections.observableArrayList();

    @FXML
    private TableView<HoKhauNhanKhau> hk_moi_tab;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hoten_moi_col;
    @FXML
    private TableColumn<HoKhauNhanKhau, Date> ngaysinh_moi_col;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> quanhe_moi_col;

    private ObservableList<HoKhauNhanKhau> list_hk_moi = FXCollections.observableArrayList();

    public int id_chu_ho_moi=-1;
    public int ma_ho_khau_hien_tai=-1;

    //Repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    public void ngaytaomoi_datepicker(ActionEvent e){
        LocalDate a = ngay_tao_moi_datepicker.getValue();
        this.NTMsetText(a.toString());
    }

    public void chon_nk_sang_hk_moi(ActionEvent e){
        HoKhauNhanKhau a = hk_hien_tai_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }if(id_chu_ho_moi==-1){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xoá");
            alert.setHeaderText("Bạn có muốn chọn nhân khẩu này là chủ hộ mới?");
            alert.setContentText("Có hoặc Không:");

            ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
            ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(buttonYes,buttonNo);

            Optional<ButtonType> result = alert.showAndWait();

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Thông báo!");

            if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
                id_chu_ho_moi=a.getIdNhanKhau();
                ho_ten_chu_ho_moi.setText(a.getHoTen());
                list_hk_hien_tai.remove(a);
                hk_hien_tai_tab.setItems(list_hk_hien_tai);
                alert1.setContentText("Thành công");
                alert1.show();
                return;
            }
            else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
                alert1.setContentText("Thất bại");
                alert1.show();
                return;
            }
        }
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập quan hệ với chủ hộ mới:");
        td.showAndWait();
        String quan_he_chu_ho=td.getEditor().getText();

        HoKhauNhanKhau b = new HoKhauNhanKhau(a.getIdNhanKhau(),quan_he_chu_ho,a.getHoTen(),a.getNgaySinh());
        list_hk_hien_tai.remove(a);
        list_hk_moi.add(b);
        hk_hien_tai_tab.setItems(list_hk_hien_tai);
        hk_moi_tab.setItems(list_hk_moi);

    }

    public void chon_nk_sang_hk_hien_tai(ActionEvent e){
        HoKhauNhanKhau a = hk_moi_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập quan hệ với chủ hộ cũ:");
        td.showAndWait();
        String quan_he_chu_ho=td.getEditor().getText();

        HoKhauNhanKhau b = new HoKhauNhanKhau(a.getIdNhanKhau(),quan_he_chu_ho,a.getHoTen(),a.getNgaySinh());
        list_hk_moi.remove(a);
        list_hk_hien_tai.add(b);
        hk_hien_tai_tab.setItems(list_hk_hien_tai);
        hk_moi_tab.setItems(list_hk_moi);
    }

    public void tach_hk(HoKhau a){
        ho_ten_chu_ho_hien_tai.setText(a.getHotenChuho());
        ma_ho_khau_hien_tai=a.getIdHoKhau();
        loadData();
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void xac_nhan_button(ActionEvent e){
        if(check_empty()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Còn trường trống hoặc chưa chọn chủ hộ mới!");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        HoKhauRepo.clear_hknk(ma_ho_khau_hien_tai);
        if(!this.list_hk_hien_tai.isEmpty()){
            HoKhauRepo.themNhanKhau(ma_ho_khau_hien_tai,this.list_hk_hien_tai);
        }

        HoKhauRepo.them_ho_khau_moi(id_chu_ho_moi,tinh_thanh_moi.getText(),quan_huyen_moi.getText(),phuong_xa_moi.getText(),dia_chi_moi.getText(),Date.valueOf(this.NTMgetText()));
        if(!this.list_hk_moi.isEmpty()) {
            int idHoKhauNew = HoKhauRepo.idHoKhau_moi_nhat();
            HoKhauRepo.themNhanKhau(idHoKhauNew, this.list_hk_moi);
        }
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Tách hộ khẩu thành công.");
        m.show();

        close_button(e);
    }

    //B0: CHeck các trường có trống không:
    private boolean check_empty(){
        if(dia_chi_moi.getText().equals("")) return true;
        if(tinh_thanh_moi.getText().equals("")) return true;
        if(quan_huyen_moi.getText().equals("")) return true;
        if(phuong_xa_moi.getText().equals("")) return true;
        if(this.NTMgetText() == null) return true;
        if(this.NTMgetText().equals("")) return true;
        if(id_chu_ho_moi==-1) return true;

        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
    }

    private void initCol(){
        hoten_hien_tai_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinh_hien_tai_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanhe_hien_tai_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
        hoten_moi_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinh_moi_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanhe_moi_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
    }

    private void loadData(){
        list_hk_hien_tai.clear();
        loadDataNK();
    }

    private void loadDataNK(){
        list_hk_hien_tai.addAll(HoKhauRepo.loadDataNKTachHKController(ma_ho_khau_hien_tai));
        hk_hien_tai_tab.setItems(list_hk_hien_tai);
    }

}
