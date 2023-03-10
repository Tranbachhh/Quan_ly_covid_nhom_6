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
        stage.setTitle("Th??m Ti??m Covid");
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event) {
        TiemCovid tiemCovid = table.getSelectionModel().getSelectedItem();
        if (tiemCovid == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Th??ng b??o!");
            m.setHeaderText("Kh??ng c?? b???n ghi n??o ???????c ch???n.");
            m.setContentText("Vui l??ng ch???n l???i.");
            m.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xo??");
        alert.setHeaderText("B???n c?? mu???n xo?? kh??ng?");
        alert.setContentText("C?? ho???c Kh??ng:");

        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonYes,buttonNo);
        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Th??ng b??o!");
        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            int id = tiemCovid.getId();
            TiemCovidRepo.delete_cl(id);
            tiemCovidList.remove(tiemCovid);
            alert1.setContentText("Th??nh c??ng");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Th???t b???i");
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
//            m.setTitle("Th??ng b??o!");
//            m.setHeaderText("Kh??ng b???n ghi n??o ???????c ch???n.");
//            m.setContentText("Vui l??ng ch???n l???i.");
//            m.show();
//            return;
//        }
//        xemTiemCovidController.setThongTin(selectedCachLy);
//        Stage stage = new Stage();
//        stage.setTitle("Th??ng tin c??ch ly");
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
//            if (truongTraCuu.equals("H??? t??n")) {
//                for (TiemCovid a : tiemCovidList) {
//                    if ((a.getHoTen().toLowerCase()).contains(search_text)) {
//                        TiemCovid clone_tiemcovid = new TiemCovid();
//                        clone_tiemcovid.copy_tiemcovid(a);
//                        searchList.add(clone_tiemcovid);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("N??i c??ch ly")) {
//                for (CachLy a : cachLyList) {
//                    if ((a.getNoiCachLy().toLowerCase()).contains(search_text)) {
//                        CachLy clone_cachly = new CachLy();
//                        clone_cachly.copy_cachly(a);
//                        searchList.add(clone_cachly);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("Ng??y b???t ?????u")) {
//                for (CachLy a : cachLyList) {
//                    if ((String.valueOf(a.getNgayBatDau())).contains(search_text)) {
//                        CachLy clone_cachly = new CachLy();
//                        clone_cachly.copy_cachly(a);
//                        searchList.add(clone_cachly);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("Ng??y k???t th??c")) {
//                for (CachLy a : cachLyList) {
//                    if ((String.valueOf(a.getNgayKetThuc())).contains(search_text)) {
//                        CachLy clone_cachly = new CachLy();
//                        clone_cachly.copy_cachly(a);
//                        searchList.add(clone_cachly);
//                    }
//                }
//                table.setItems(searchList);
//            } else if (truongTraCuu.equals("M???c ?????")) {
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
//            m.setTitle("Th??ng b??o!");
//            m.setHeaderText("Ch??a ch???n tr?????ng t??m ki???m!");
//            m.setContentText("M???i ch???n l???i!");
//            m.show();
//            return;
//        }
//    }

    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("H??? t??n","N??i Ti??m","S??? m??i");
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
