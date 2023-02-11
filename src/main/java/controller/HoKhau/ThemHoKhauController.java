package controller.HoKhau;

import entity.HoKhauNhanKhau;
import entity.NhanKhau;
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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemHoKhauController implements Initializable {

    @FXML
    private TextField address_ho_khau_text;
    @FXML
    private TextField thanhpho_text;
    @FXML
    private TextField quanhuyen_text;
    @FXML
    private TextField phuongxa_text;
    @FXML
    private String ngaytao_text;
    @FXML
    private TextField hovaten_chuho;
    @FXML
    private TextField ngaysinh_chuho;
    @FXML
    private TextField CMND_chuho;
    @FXML
    private TableView<NhanKhau> nhankhauTab;
    @FXML
    private TableColumn<NhanKhau,String> hovaten_nkCol;
    @FXML
    private TableColumn<NhanKhau,Date> ngaysinh_nkCol;
    @FXML
    private TableColumn<NhanKhau,String> CMND_nkCol;

    private ObservableList<NhanKhau> dsnk = FXCollections.observableArrayList();

    private ObservableList<NhanKhau> search_dsnk = FXCollections.observableArrayList();

    public int id_chu_ho = -1;

    private void NTsetText(String a){
        this.ngaytao_text = a;
    }

    private String NTgetText(){
        return this.ngaytao_text;
    }

    //Datepicker:

    @FXML
    private DatePicker ngay_tao_datepicker;

    //Nhan Khau:
    @FXML
    private TableView<HoKhauNhanKhau> nk_table;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hoten_nk_Col;
    @FXML
    private TableColumn<HoKhauNhanKhau,Date> ngaysinh_nk_Col;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> quanhe_nk_Col;

    private ObservableList<HoKhauNhanKhau> hknk_list = FXCollections.observableArrayList();

    @FXML
    private Label ma_chu_ho_duoc_chon;
    @FXML
    private Label ho_ten_chu_ho_duoc_chon;

    //Repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    public void ngaytao_datepicker(ActionEvent e){
        LocalDate a = ngay_tao_datepicker.getValue();
        this.NTsetText(a.toString());
    }

    public void chon_chu_ho_button(ActionEvent e) throws IOException {
        NhanKhau a = nhankhauTab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            this.id_chu_ho = -1;
            return;
        }
        if(HoKhauRepo.check_chu_ho(a.getId())){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Nhân khẩu đã là chủ hộ rồi.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            this.id_chu_ho = -1;
            return;
        }
        if(HoKhauRepo.check_nhan_khau_exist_nk(a.getId())){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Nhân khẩu đã thuộc một hộ khẩu khác");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }
        if(a.getTrangThai().equals("Tạm trú")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu này là nhân khẩu tạm trú!!!");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }

        this.id_chu_ho = a.getId();
        ma_chu_ho_duoc_chon.setText(String.valueOf(a.getId()));
        ho_ten_chu_ho_duoc_chon.setText(a.getHoTen());

        ObservableList<HoKhauNhanKhau> fx = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : hknk_list){
            if(i.getIdNhanKhau() == this.id_chu_ho){
                continue;
            }
            HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdHoKhau(),i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh(),i.getCmnd());
            fx.add(t);
        }
        hknk_list.clear();
        hknk_list.addAll(fx);
        nk_table.setItems(hknk_list);

        Alert me = new Alert(Alert.AlertType.INFORMATION);
        me.setTitle("Alert!");
        me.setHeaderText("Chọn chủ hộ thành công.");
        me.setContentText("Bạn có thể chọn lại chủ hộ.");
        me.show();
    }

    private boolean check_data(){
        if(address_ho_khau_text.getText().equals("")) return false;
        if(thanhpho_text.getText().equals("")) return false;
        if(quanhuyen_text.getText().equals("")) return false;
        if(phuongxa_text.getText().equals("")) return false;
        if(this.NTgetText() == null) return false;
        if(this.NTgetText().equals("")) return false;
        if(this.id_chu_ho == -1) return false;
        return true;
    }

    public void tao_ho_khau(ActionEvent e) throws IOException {
        if(!check_data()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Các trường bắt buộc còn trống");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }
        HoKhauRepo.them_ho_khau_moi(id_chu_ho,thanhpho_text.getText(),quanhuyen_text.getText(),phuongxa_text.getText(),address_ho_khau_text.getText(),Date.valueOf(this.NTgetText()));
        int idHoKhau = HoKhauRepo.idHoKhau_moi_nhat();
        HoKhauRepo.themNhanKhau(idHoKhau,this.hknk_list);
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Thêm hộ khẩu mới thành công");
        m.show();

        huy_button(e);
    }

    public void tim_button(ActionEvent e) throws IOException {
        search_dsnk.clear();
        String search_hovaten = hovaten_chuho.getText().trim().toLowerCase();
        String search_ngaysinh = ngaysinh_chuho.getText().trim().toLowerCase();
        String search_CMND = CMND_chuho.getText().trim().toLowerCase();
        for(NhanKhau a : dsnk){
            if(a.getCMND() == null){
                a.setCMND("");
            }
            if((a.getHoTen().trim().toLowerCase()).contains(search_hovaten) && (String.valueOf(a.getNgaySinh()).trim().toLowerCase()).contains(search_ngaysinh) && (a.getCMND().trim().toLowerCase()).contains(search_CMND)){
                NhanKhau b = new NhanKhau(a.getId(),a.getHoTen(),a.getNgaySinh(),a.getNoiSinh(),a.getGioiTinh(),a.getCMND(),a.getDanToc(),a.getTonGiao(),a.getQuocTich(),a.getTrangThai());
                search_dsnk.add(b);
            }
        }
        nhankhauTab.setItems(search_dsnk);
        hovaten_chuho.setText("");
        ngaysinh_chuho.setText("");
        CMND_chuho.setText("");
    }

    public void huy_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void them_nk_button(ActionEvent e){
        NhanKhau a = nhankhauTab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(a.getId() == this.id_chu_ho){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu trùng với chủ hộ");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(HoKhauRepo.check_nhan_khau_exist_nk(a.getId()) || HoKhauRepo.check_chu_ho(a.getId())){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu đã thuộc hộ khẩu khác");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(a.getTrangThai().equals("Tạm trú")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu là nhân khẩu Tạm trú!!!");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        for(HoKhauNhanKhau t : hknk_list){
            if(a.getId() == t.getIdNhanKhau()){
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Thông báo!");
                m.setHeaderText("Nhân khẩu đã được thêm");
                m.setContentText("Vui lòng chọn lại");
                m.show();
                return;
            }
        }
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập quan hệ với chủ hộ");
        td.showAndWait();
        String quan_he_chu_ho=td.getEditor().getText();

        HoKhauNhanKhau b = new HoKhauNhanKhau(a.getId(),quan_he_chu_ho,a.getHoTen(),a.getNgaySinh());
        hknk_list.add(b);
        nk_table.setItems(hknk_list);

        return;
    }

    public void sua_button(ActionEvent e){
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập quan hệ với chủ hộ");
        td.showAndWait();
        String quan_he_chu_ho=td.getEditor().getText();

        ObservableList<HoKhauNhanKhau> f = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : hknk_list){
            if(i.getIdNhanKhau() == a.getIdNhanKhau()){
                HoKhauNhanKhau b = new HoKhauNhanKhau(i.getIdNhanKhau(),quan_he_chu_ho,i.getHoTen(),i.getNgaySinh());
                f.add(b);
            }
            else {
                HoKhauNhanKhau b = new HoKhauNhanKhau(i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh());
                f.add(b);
            }
        }
        hknk_list.clear();
        hknk_list.addAll(f);
        nk_table.setItems(hknk_list);
        return;
    }

    public void xoa_button(ActionEvent e){
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        hknk_list.remove(a);
        nk_table.setItems(hknk_list);
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Xoá nhân khẩu thành công");
        m.show();
        return;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void initCol(){
        hovaten_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("hoTen"));
        ngaysinh_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,Date>("ngaySinh"));
        CMND_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("CMND"));
        hoten_nk_Col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinh_nk_Col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanhe_nk_Col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
    }
    private void loadData(){
        dsnk.clear();
        dsnk.addAll(HoKhauRepo.loadDataThemHKController());
        nhankhauTab.setItems(dsnk);
    }
}
