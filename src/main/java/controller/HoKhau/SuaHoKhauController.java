package controller.HoKhau;

import entity.HoKhau;
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
import java.util.ResourceBundle;

public class SuaHoKhauController implements Initializable {
    @FXML
    private Label id_ho_khau_change;
    @FXML
    private Label id_chu_ho_change;

    private int id_chu_ho_new = 0;

    public int getId_chu_ho_new() {
        return id_chu_ho_new;
    }

    public void setId_chu_ho_new(int id_chu_ho_new) {
        this.id_chu_ho_new = id_chu_ho_new;
    }

    @FXML
    private Label diachi_change;
    @FXML
    private Label tinhthanh_change;
    @FXML
    private Label quanhuyen_change;
    @FXML
    private Label phuongxa_change;
    @FXML
    private Label hoten_chu_ho_change;
    //nk_table:
    @FXML
    private TableView<HoKhauNhanKhau> nk_table;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hotenNhanKhau;
    @FXML
    private TableColumn<HoKhauNhanKhau, Date> ngaysinhNhanKhau;
    @FXML
    private TableColumn<HoKhauNhanKhau, String> quanheChuHo;

    private ObservableList<HoKhauNhanKhau> list = FXCollections.observableArrayList();

    //nk_table_search:
    @FXML
    private TableView<NhanKhau> nk_table_search;
    @FXML
    private TableColumn<NhanKhau,String> hotenNhanKhau_search;
    @FXML
    private TableColumn<NhanKhau,Date> ngaySinhNhanKhau_search;
    @FXML
    private TableColumn<NhanKhau,String> CMNDNhanKhau_search;
    @FXML
    private TextField hoten_search;
    @FXML
    private TextField ngaysinh_search;
    @FXML
    private TextField CMND_search;

    private ObservableList<NhanKhau> list_nk = FXCollections.observableArrayList();

    private ObservableList<NhanKhau> list_nk_search = FXCollections.observableArrayList();

    //Repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    //function:
    public void change_hk(HoKhau hk){
        id_ho_khau_change.setText(String.valueOf(hk.getIdHoKhau()));
        id_chu_ho_change.setText(String.valueOf(hk.getIdChuHo()));
        tinhthanh_change.setText(hk.getTinhThanhPho());
        quanhuyen_change.setText(hk.getQuanHuyen());
        phuongxa_change.setText(hk.getPhuongXa());
        diachi_change.setText(hk.getDiachi());

        loadData();
        hoten_chu_ho_change();
    }

    public void save_button(ActionEvent e){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());

        HoKhauRepo.clear_hknk(idHoKhau);
        HoKhauRepo.change_id_chuho(idHoKhau,this.getId_chu_ho_new());
        HoKhauRepo.themNhanKhau(idHoKhau,this.list);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Sửa hộ khẩu thành công");
        m.show();

        close_button(e);
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void add_button(ActionEvent e) throws IOException {
        NhanKhau c = nk_table_search.getSelectionModel().getSelectedItem();
        int idhokhau = Integer.parseInt(id_ho_khau_change.getText());
        if(c == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        for(HoKhauNhanKhau i : list){
            if(c.sosanh(i)){
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Thông báo!");
                m.setHeaderText("Nhân khẩu đã được thêm");
                m.setContentText("Vui lòng chọn lại");
                m.show();
                return;
            }
        }
        if(c.getId() == this.getId_chu_ho_new()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu trùng với chủ hộ");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(HoKhauRepo.check_nhan_khau_exist_hk(c,idhokhau) || HoKhauRepo.check_nhan_khau_exist_nk_1(c,idhokhau)){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu đã thuộc một hộ khẩu khác");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(c.getTrangThai().equals("Tạm trú")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu là nhân khẩu tạm trú");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }

        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập quan hệ với chủ hộ");
        td.showAndWait();
        String quan_he_chu_ho=td.getEditor().getText();

        HoKhauNhanKhau new_hknk = new HoKhauNhanKhau(idhokhau,c.getId(),quan_he_chu_ho,c.getHoTen(),c.getNgaySinh(),c.getCMND());
        list.add(new_hknk);
        nk_table.setItems(list);

        return;
    }

    public void change_button(ActionEvent e) throws IOException {
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không có nhân khẩu nào được chọn");
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
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        for(HoKhauNhanKhau i : list){
            if(i.getIdNhanKhau() == a.getIdNhanKhau()){
                HoKhauNhanKhau b = new HoKhauNhanKhau(idHoKhau,i.getIdNhanKhau(),quan_he_chu_ho,i.getHoTen(),i.getNgaySinh(),i.getCmnd());
                f.add(b);
            }
            else {
                HoKhauNhanKhau b = new HoKhauNhanKhau(idHoKhau,i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh(),i.getCmnd());
                f.add(b);
            }
        }
        list.clear();
        list.addAll(f);
        nk_table.setItems(list);

        return;
    }

    public void search_button(ActionEvent e) throws IOException {
        list_nk_search.clear();
        String search_hovaten = hoten_search.getText().trim().toLowerCase();
        String search_ngaysinh = ngaysinh_search.getText().trim().toLowerCase();
        String search_CMND = CMND_search.getText().trim().toLowerCase();
        for(NhanKhau a : list_nk){
            if(a.getCMND() == null){
                a.setCMND("");
            }
            if((a.getHoTen().trim().toLowerCase()).contains(search_hovaten) && (String.valueOf(a.getNgaySinh()).trim().toLowerCase()).contains(search_ngaysinh) && (a.getCMND().trim().toLowerCase()).contains(search_CMND)){
                NhanKhau b = new NhanKhau(a.getId(),a.getHoTen(),a.getNgaySinh(),a.getNoiSinh(),a.getGioiTinh(),a.getCMND(),a.getDanToc(),a.getTonGiao(),a.getQuocTich(),a.getTrangThai());
                list_nk_search.add(b);
            }
        }
        nk_table_search.setItems(list_nk_search);
        hoten_search.setText("");
        ngaysinh_search.setText("");
        CMND_search.setText("");
    }

    public void chon_chu_ho_button(ActionEvent e) throws IOException {
        NhanKhau a = nk_table_search.getSelectionModel().getSelectedItem();
        int idhokhau = Integer.parseInt(id_ho_khau_change.getText());
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(HoKhauRepo.check_nhan_khau_exist_hk(a,idhokhau) || HoKhauRepo.check_nhan_khau_exist_nk_1(a,idhokhau)){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu đã thuộc hộ khẩu khác");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(a.getTrangThai().equals("Tạm trú") || a.getTrangThai().equals("Đã mất")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu đã mất hoặc là nhân khẩu tạm trú!!");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        this.setId_chu_ho_new(a.getId());
        id_chu_ho_change.setText(String.valueOf(this.getId_chu_ho_new()));
        hoten_chu_ho_change.setText(a.getHoTen());

        ObservableList<HoKhauNhanKhau> f = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : list){
            if(i.getIdNhanKhau() == this.getId_chu_ho_new()){
                continue;
            }
            HoKhauNhanKhau t = new HoKhauNhanKhau(idhokhau,i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh(),i.getCmnd());
            f.add(t);
        }
        list.clear();
        list.addAll(f);
        nk_table.setItems(list);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Chọn chủ hộ thành công");
        m.show();
        return;
    }

    public void delete_button(ActionEvent e){
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không có nhân khẩu nào được chọn");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        list.remove(a);
        nk_table.setItems(list);
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Đã xoá thành công");
        m.show();
        return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadNK();
    }

    private void initCol(){
        hotenNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinhNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanheChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
        hotenNhanKhau_search.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("hoTen"));
        ngaySinhNhanKhau_search.setCellValueFactory(new PropertyValueFactory<NhanKhau,Date>("ngaySinh"));
        CMNDNhanKhau_search.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("CMND"));
    }

    private void loadData(){
        list.clear();
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        list.addAll(HoKhauRepo.loadDataSuaHKController(idHoKhau));
        nk_table.setItems(list);
    }

    private void loadNK(){
        list_nk.clear();
        list_nk.addAll(HoKhauRepo.loadNKSuaHKController());
        nk_table_search.setItems(list_nk);
    }

    private void hoten_chu_ho_change(){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        this.setId_chu_ho_new(HoKhauRepo.id_chu_ho_change(idHoKhau));
        hoten_chu_ho_change.setText(HoKhauRepo.hoten_chu_ho_change(idHoKhau));
    }
}
