package controller.HoKhau;

import entity.HoKhau;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import repository.HoKhauRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class HoKhauController implements Initializable {
    @FXML
    private TableView<HoKhau> table;

    @FXML
    private TableColumn<HoKhau, Integer> id_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, Integer> id_chu_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> address_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> hoten_chu_hoCol;

    @FXML
    private TableColumn<HoKhau, Date> ngay_taoCol;


    @FXML
    private TextField search_ho_khau;

    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<String> list_combo_box = FXCollections.observableArrayList("Địa chỉ","Tên chủ hộ","Ngày tạo");

    private ObservableList<HoKhau> hokhauList = FXCollections.observableArrayList();

    private ObservableList<HoKhau> searchList = FXCollections.observableArrayList();

    //repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    //checked//
    public void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/themHoKhau.fxml"));
        Parent them_ho_khau = loader.load();
        Scene scene = new Scene(them_ho_khau);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Thêm hộ khẩu");
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void delete(ActionEvent event){
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xoá");
        alert.setHeaderText("Bạn có muốn xoá không?");
        alert.setContentText("Có hoặc Không:");

        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonYes,buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Thông báo!");

        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            int idHoKhau = hk.getIdHoKhau();
            HoKhauRepo.delete_hk(idHoKhau);
            hokhauList.remove(hk);
            alert1.setContentText("Thành công");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Thất bại");
            alert1.show();
        }

    }

    public void show(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/xemHoKhau.fxml"));
        Parent show_ho_khau = loader.load();
        XemHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.show_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Xem hộ khẩu");
        Scene scene = new Scene(show_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void change(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/suaHoKhau.fxml"));
        Parent change_ho_khau = loader.load();
        SuaHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.change_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Sửa hộ khẩu");
        Scene scene = new Scene(change_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void tach(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/tachHoKhau.fxml"));
        Parent tach_ho_khau = loader.load();
        TachHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.tach_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Tách hộ khẩu");
        Scene scene = new Scene(tach_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadDataHKController();
    }

    private void initCol(){
        id_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("idHoKhau"));
        id_chu_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("idChuHo"));
        address_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("diachi"));
        hoten_chu_hoCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("hotenChuho"));
        ngay_taoCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Date>("ngayTao"));
    }

    private void loadDataHKController(){
        hokhauList.clear();
        hokhauList.addAll(HoKhauRepo.loadDataHKController());
        table.setItems(hokhauList);
        comboBox.setItems(list_combo_box);
    }

    public void searchClick(MouseEvent mouseEvent) {
        searchList.clear();
        String search_text = search_ho_khau.getText().trim().toLowerCase(); ;
        String sc = comboBox.getValue();
        try{
            if(sc.equals("Địa chỉ")){
                for(HoKhau a : hokhauList){
                    if((a.getDiachi().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Tên chủ hộ")){
                for(HoKhau a : hokhauList){
                    if((a.getHotenChuho().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Ngày tạo")){
                for(HoKhau a : hokhauList){
                    if((String.valueOf(a.getNgayTao())).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
        }catch(NullPointerException ex){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn trường tìm kiếm!");
            m.setContentText("Mời chọn lại!");
            m.show();
            return;
        }
    }

    public void refresh_button(ActionEvent e){
        search_ho_khau.setText("");
        comboBox.valueProperty().set(null);
        loadDataHKController();
    }
}
