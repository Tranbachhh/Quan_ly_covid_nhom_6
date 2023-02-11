package controller.CachLy;

import entity.CachLy;
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


public class CachLyController implements Initializable {
    @FXML
    private TableView<CachLy>table;
    @FXML
    private TableColumn<CachLy,Integer>  idColumn;
    @FXML
    private TableColumn<CachLy,String>  hoTenColumn;
    @FXML
    private TableColumn<CachLy, Date>  ngayBatDauColumn;
    @FXML
    private TableColumn<CachLy, Date>  ngayKetThucColumn;
    @FXML
    private TableColumn<CachLy,String>  noiCachLyColumn;
    @FXML
    private TableColumn<CachLy,String>  mucDoColumn;
    @FXML
    private Button themCachLy;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    ObservableList<CachLy>  cachLyList = FXCollections.observableArrayList();
    ObservableList<CachLy>  searchList = FXCollections.observableArrayList();
    private int id_NK;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static CachLyRepository CachLyRepo = new CachLyRepositoryImpl();

    public void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CachLy/themCachLy.fxml"));
        Parent them_cachly = loader.load();
        Scene scene = new Scene(them_cachly);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Thêm Cách Ly");
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event){
        CachLy cachly = table.getSelectionModel().getSelectedItem();
        if (cachly == null) {
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
            int idCachLy = cachly.getId();
            CachLyRepo.delete_cl(idCachLy);
            cachLyList.remove(cachly);
            alert1.setContentText("Thành công");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Thất bại");
            alert1.show();
        }
    }

    public void xemCachLy(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CachLy/xemCachLy.fxml"));
        Parent xemCachLy = loader.load();
        XemCachLyController xemCachLyController = loader.getController();
        CachLy selectedCachLy = table.getSelectionModel().getSelectedItem();
        if (selectedCachLy == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không bản ghi nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        xemCachLyController.setThongTin(selectedCachLy);
        Stage stage = new Stage();
        stage.setTitle("Thông tin cách ly");
        Scene scene = new Scene(xemCachLy);
        stage.setScene(scene);
        stage.show();
    }

    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {
        searchList.clear();
        String search_text = duLieuF.getText().trim().toLowerCase();
        String truongTraCuu = truongTraCuuF.getValue();
        try {
            if (truongTraCuu.equals("Họ tên")) {
                for (CachLy a : cachLyList) {
                    if ((a.getHoTen().toLowerCase()).contains(search_text)) {
                        CachLy clone_cachly = new CachLy();
                        clone_cachly.copy_cachly(a);
                        searchList.add(clone_cachly);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("Nơi cách ly")) {
                for (CachLy a : cachLyList) {
                    if ((a.getNoiCachLy().toLowerCase()).contains(search_text)) {
                        CachLy clone_cachly = new CachLy();
                        clone_cachly.copy_cachly(a);
                        searchList.add(clone_cachly);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("Ngày bắt đầu")) {
                for (CachLy a : cachLyList) {
                    if ((String.valueOf(a.getNgayBatDau())).contains(search_text)) {
                        CachLy clone_cachly = new CachLy();
                        clone_cachly.copy_cachly(a);
                        searchList.add(clone_cachly);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("Ngày kết thúc")) {
                for (CachLy a : cachLyList) {
                    if ((String.valueOf(a.getNgayKetThuc())).contains(search_text)) {
                        CachLy clone_cachly = new CachLy();
                        clone_cachly.copy_cachly(a);
                        searchList.add(clone_cachly);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("Mức độ")) {
                for (CachLy a : cachLyList) {
                    if ((a.getMucDoCachLy().toLowerCase()).contains(search_text)) {
                        CachLy clone_cachly = new CachLy();
                        clone_cachly.copy_cachly(a);
                        searchList.add(clone_cachly);
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

    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Họ tên","Ngày bắt đầu","Ngày kết thúc","Nơi cách ly","Mức độ");
    @FXML
    private void loadData() {
        cachLyList.clear();
        cachLyList.addAll(CachLyRepo.loadDataCachLyController());
        table.setItems(cachLyList);
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    private void initCol(){
        idColumn.setCellValueFactory(new PropertyValueFactory<CachLy,Integer>("id"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<CachLy,String>("hoTen"));
        ngayBatDauColumn.setCellValueFactory(new PropertyValueFactory<CachLy, Date>("ngayBatDau"));
        ngayKetThucColumn.setCellValueFactory(new PropertyValueFactory<CachLy, Date>("ngayKetThuc"));
        noiCachLyColumn.setCellValueFactory(new PropertyValueFactory<CachLy,String>("noiCachLy"));
        mucDoColumn.setCellValueFactory(new PropertyValueFactory<CachLy,String>("mucDoCachLy"));
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

}


