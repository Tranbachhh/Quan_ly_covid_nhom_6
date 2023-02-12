package controller.TiemCovid;

import entity.TiemCovid;
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
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import repository.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
public class TiemCovidController implements Initializable  {
    @FXML
    private TableView<TiemCovid>table;
    @FXML
    private TableColumn<TiemCovid,Integer>  idColumn;
    @FXML
    private TableColumn<TiemCovid,Integer>  idNhanKhauColumn;
    @FXML
    private TableColumn<TiemCovid,String>  hoTenColumn;
    @FXML
    private TableColumn<TiemCovid, String>  noiTiemColumn;
    @FXML
    private TableColumn<TiemCovid, Integer>  soMuiColumn;
    private Button themTiemCovid;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    ObservableList<TiemCovid>  tiemCovidList = FXCollections.observableArrayList();
    ObservableList<TiemCovid>  searchList = FXCollections.observableArrayList();
    private int id;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static TiemCovidRepository TiemCovidRepo = new TiemCovidRepositoryImpl();
    public void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TiemCovid/themTiemCovid.fxml"));
        Parent them_tiemcoivd = loader.load();
        Scene scene = new Scene(them_tiemcoivd);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Thêm Tiêm Covid");
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event) {
        TiemCovid tiemCovid = table.getSelectionModel().getSelectedItem();
        if (tiemCovid == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không có bản ghi nào được chọn.");
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
            int id = tiemCovid.getId();
            TiemCovidRepo.delete_cl(id);
            tiemCovidList.remove(tiemCovid);
            alert1.setContentText("Thành công");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Thất bại");
            alert1.show();
        }
    }
//    public void xemTiemCovid(ActionEvent e) throws IOException {
//
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/view/TiemCoivd/xemTiemCovid.fxml"));
//        Parent xemTiemCovid = loader.load();
//        XemTiemCovidController xemTiemCovidController = loader.getController();
//        TiemCovid selectedTiemCovid = table.getSelectionModel().getSelectedItem();
//        if (selectedTiemCovid == null) {
//            Alert m = new Alert(Alert.AlertType.INFORMATION);
//            m.setTitle("Thông báo!");
//            m.setHeaderText("Không bản ghi nào được chọn.");
//            m.setContentText("Vui lòng chọn lại.");
//            m.show();
//            return;
//        }
//        xemTiemCovidController.setThongTin(selectedCachLy);
//        Stage stage = new Stage();
//        stage.setTitle("Thông tin cách ly");
//        Scene scene = new Scene(xemCachLy);
//        stage.setScene(scene);
//        stage.show();
//    }

//    @SneakyThrows
//    @FXML
//    private void findF(MouseEvent event) {
//        searchList.clear();
//        String search_text = duLieuF.getText().trim().toLowerCase();
//        String truongTraCuu = truongTraCuuF.getValue();
//        try {
//            if (truongTraCuu.equals("Họ tên")) {
//                for (TiemCovid a : tiemCovidList) {
//                    if ((a.getHoTen().toLowerCase()).contains(search_text)) {
//                        TiemCovid clone_tiemcovid = new TiemCovid();
//                        clone_tiemcovid.copy_tiemcovid(a);
//                        searchList.add(clone_tiemcovid);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("Nơi cách ly")) {
//                for (CachLy a : cachLyList) {
//                    if ((a.getNoiCachLy().toLowerCase()).contains(search_text)) {
//                        CachLy clone_cachly = new CachLy();
//                        clone_cachly.copy_cachly(a);
//                        searchList.add(clone_cachly);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("Ngày bắt đầu")) {
//                for (CachLy a : cachLyList) {
//                    if ((String.valueOf(a.getNgayBatDau())).contains(search_text)) {
//                        CachLy clone_cachly = new CachLy();
//                        clone_cachly.copy_cachly(a);
//                        searchList.add(clone_cachly);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("Ngày kết thúc")) {
//                for (CachLy a : cachLyList) {
//                    if ((String.valueOf(a.getNgayKetThuc())).contains(search_text)) {
//                        CachLy clone_cachly = new CachLy();
//                        clone_cachly.copy_cachly(a);
//                        searchList.add(clone_cachly);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("Mức độ")) {
//                for (CachLy a : cachLyList) {
//                    if ((a.getMucDoCachLy().toLowerCase()).contains(search_text)) {
//                        CachLy clone_cachly = new CachLy();
//                        clone_cachly.copy_cachly(a);
//                        searchList.add(clone_cachly);
//                    }
//                }
//                table.setItems(searchList);
//            }
//        }catch(NullPointerException ex){
//            Alert m = new Alert(Alert.AlertType.INFORMATION);
//            m.setTitle("Thông báo!");
//            m.setHeaderText("Chưa chọn trường tìm kiếm!");
//            m.setContentText("Mời chọn lại!");
//            m.show();
//            return;
//        }
//    }

    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Họ tên","Nơi Tiêm","Số mũi");
    @FXML
    private void loadData() {
        tiemCovidList.clear();
        tiemCovidList.addAll(TiemCovidRepo.loadDataTiemCovidController());
        table.setItems(tiemCovidList);
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    private void initCol(){
        idColumn.setCellValueFactory(new PropertyValueFactory<TiemCovid,Integer>("id"));
        //idNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<TiemCovid,Integer>("idNhanKhau"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<TiemCovid,String>("hoTen"));
        noiTiemColumn.setCellValueFactory(new PropertyValueFactory<TiemCovid,String>("noiTiem"));
        soMuiColumn.setCellValueFactory(new PropertyValueFactory<TiemCovid,Integer>("soMui"));
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }
}
