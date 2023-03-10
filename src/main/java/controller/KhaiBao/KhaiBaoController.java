package controller.KhaiBao;

import entity.KhaiBao;
import entity.NhanKhau;
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
import repository.KhaiBaoRepository;
import repository.KhaiBaoRepositoryImpl;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;


public class KhaiBaoController implements Initializable {
    @FXML
    private TableView<KhaiBao>table;
    @FXML
    private TableColumn<KhaiBao,Integer>  idColumn;
    @FXML
    private TableColumn<KhaiBao,String>  hoTenColumn;
    @FXML
    private TableColumn<KhaiBao, Date>  ngayKhaiBaoColumn;
    @FXML
    private TableColumn<KhaiBao,String>  trieuChungColumn;
    @FXML
    private TableColumn<KhaiBao,String>  CMNDColumn;
    @FXML
    private TableColumn<KhaiBao,String>  tinhTrangColumn;
    @FXML
    private Button themKhaiBao;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    ObservableList<KhaiBao>  khaiBaoList = FXCollections.observableArrayList();
    ObservableList<KhaiBao>  searchList = FXCollections.observableArrayList();
    private int id_NK;
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();
    static KhaiBaoRepository KhaiBaoRepo = new KhaiBaoRepositoryImpl();

    //checked//
    public void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/KhaiBao/themKhaiBao.fxml"));
        Parent them_khai_bao = loader.load();
        Scene scene = new Scene(them_khai_bao);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Th??m khai b??o");
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event){
        KhaiBao kb = table.getSelectionModel().getSelectedItem();
        if (kb == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Th??ng b??o!");
            m.setHeaderText("Kh??ng c?? khai b??o n??o ???????c ch???n.");
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
            int idKhaiBao = kb.getId();
            KhaiBaoRepo.delete_kb(idKhaiBao);
            khaiBaoList.remove(kb);
            alert1.setContentText("Th??nh c??ng");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Th???t b???i");
            alert1.show();
        }
    }

    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {
        searchList.clear();
        String search_text = duLieuF.getText().trim().toLowerCase();
        String truongTraCuu = truongTraCuuF.getValue();
        try {
            if (truongTraCuu.equals("H??? t??n")) {
                for (KhaiBao a : khaiBaoList) {
                    if ((a.getHoTen().toLowerCase()).contains(search_text)) {
                        KhaiBao clone_kb = new KhaiBao();
                        clone_kb.copy_kb(a);
                        searchList.add(clone_kb);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("Ch???ng minh nh??n d??n")) {
                for (KhaiBao a : khaiBaoList) {
                    if ((a.getCMND().toLowerCase()).contains(search_text)) {
                        KhaiBao clone_kb = new KhaiBao();
                        clone_kb.copy_kb(a);
                        searchList.add(clone_kb);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("Ng??y khai b??o")) {
                for (KhaiBao a : khaiBaoList) {
                    if ((String.valueOf(a.getNgayKhaiBao())).contains(search_text)) {
                        KhaiBao clone_kb = new KhaiBao();
                        clone_kb.copy_kb(a);
                        searchList.add(clone_kb);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("Tri???u ch???ng")) {
                for (KhaiBao a : khaiBaoList) {
                    if ((a.getTrieuChung().toLowerCase()).contains(search_text)) {
                        KhaiBao clone_kb = new KhaiBao();
                        clone_kb.copy_kb(a);
                        searchList.add(clone_kb);
                    }
                }
                table.setItems(searchList);
            } else if (truongTraCuu.equals("T??nh tr???ng")) {
                for (KhaiBao a : khaiBaoList) {
                    if ((a.getTinhTrangSucKhoe().toLowerCase()).contains(search_text)) {
                        KhaiBao clone_kb = new KhaiBao();
                        clone_kb.copy_kb(a);
                        searchList.add(clone_kb);
                    }
                }
                table.setItems(searchList);
            }
        }catch(NullPointerException ex){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Th??ng b??o!");
            m.setHeaderText("Ch??a ch???n tr?????ng t??m ki???m!");
            m.setContentText("M???i ch???n l???i!");
            m.show();
            return;
        }

    }

    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("H??? t??n","Ch???ng minh nh??n d??n","Tri???u ch???ng","Ng??y khai b??o","T??nh tr???ng");
    @FXML
    private void loadDataKhaiBao() {
        khaiBaoList.clear();
        khaiBaoList.addAll(KhaiBaoRepo.loadDataKhaiBaoController());
        table.setItems(khaiBaoList);
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    private void initCol(){
        idColumn.setCellValueFactory(new PropertyValueFactory<KhaiBao,Integer>("id"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<KhaiBao,String>("hoTen"));
        ngayKhaiBaoColumn.setCellValueFactory(new PropertyValueFactory<KhaiBao, Date>("ngayKhaiBao"));
        trieuChungColumn.setCellValueFactory(new PropertyValueFactory<KhaiBao,String>("trieuChung"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<KhaiBao,String>("CMND"));
        tinhTrangColumn.setCellValueFactory(new PropertyValueFactory<KhaiBao,String>("tinhTrangSucKhoe"));
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadDataKhaiBao();
    }

}
