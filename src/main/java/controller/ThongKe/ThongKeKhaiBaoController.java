package controller.ThongKe;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;
import repository.KhaiBaoRepository;
import repository.KhaiBaoRepositoryImpl;

import java.net.URL;
import java.util.ResourceBundle;


public class ThongKeKhaiBaoController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane trieuChung;

    @FXML
    private Pane tinhTrangSucKhoe;

    @FXML
    private Pane bieuDo;

    KhaiBaoRepository KhaiBaoRepo = new KhaiBaoRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // noiCachLy
        ObservableList<PieChart.Data> dataTrieuChung = FXCollections.observableArrayList(
                new PieChart.Data("Có", KhaiBaoRepo.tongTrieuChungCo()),
                new PieChart.Data("Không", KhaiBaoRepo.tongKhaiBao()-KhaiBaoRepo.tongTrieuChungCo()));
        final PieChart chartTrieuChung = new PieChart(dataTrieuChung);
        dataTrieuChung.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartTrieuChung.setPrefSize(300, 270);
        chartTrieuChung.setLegendVisible(false);

        trieuChung.getChildren().add(chartTrieuChung);

        // mucDo
        ObservableList<PieChart.Data> dataSucKhoe = FXCollections.observableArrayList(
                new PieChart.Data("Bình thường", KhaiBaoRepo.tongBinhThuong()),
                new PieChart.Data("Ho", KhaiBaoRepo.tongHo()),
                new PieChart.Data("Sốt", KhaiBaoRepo.tongSot()));
        final PieChart chartSucKhoe = new PieChart(dataSucKhoe);
        dataSucKhoe.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartSucKhoe.setPrefSize(450, 500);
        chartSucKhoe.setLegendVisible(false);

        tinhTrangSucKhoe.getChildren().add(chartSucKhoe);

        // bieuDo
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tháng");
        NumberAxis yAxis = new NumberAxis(0,5,1);//fix cứng cột y giới hạn từ 1 đến 5 sẽ update sau
        yAxis.setLabel("Số lượng");

        LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

        XYChart.Series<String,Number> dataSeries = new XYChart.Series();
        dataSeries.setName("Biểu đồ khai báo");
        dataSeries.getData().add(new XYChart.Data("Jan", KhaiBaoRepo.tongKhaiBaoTheoThang(1)));
        dataSeries.getData().add(new XYChart.Data("Feb", KhaiBaoRepo.tongKhaiBaoTheoThang(2)));
        dataSeries.getData().add(new XYChart.Data("Mar", KhaiBaoRepo.tongKhaiBaoTheoThang(3)));
        dataSeries.getData().add(new XYChart.Data("Apr", KhaiBaoRepo.tongKhaiBaoTheoThang(4)));
        dataSeries.getData().add(new XYChart.Data("May", KhaiBaoRepo.tongKhaiBaoTheoThang(5)));
        dataSeries.getData().add(new XYChart.Data("Jun", KhaiBaoRepo.tongKhaiBaoTheoThang(6)));
        dataSeries.getData().add(new XYChart.Data("Jul", KhaiBaoRepo.tongKhaiBaoTheoThang(7)));
        dataSeries.getData().add(new XYChart.Data("Aug", KhaiBaoRepo.tongKhaiBaoTheoThang(8)));
        dataSeries.getData().add(new XYChart.Data("Sep", KhaiBaoRepo.tongKhaiBaoTheoThang(9)));
        dataSeries.getData().add(new XYChart.Data("Oct", KhaiBaoRepo.tongKhaiBaoTheoThang(10)));
        dataSeries.getData().add(new XYChart.Data("Nov", KhaiBaoRepo.tongKhaiBaoTheoThang(11)));
        dataSeries.getData().add(new XYChart.Data("Dec", KhaiBaoRepo.tongKhaiBaoTheoThang(12)));
        lineChart.getData().add(dataSeries);

        lineChart.setPrefSize(500, 300);
        bieuDo.getChildren().add(lineChart);
    }

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
