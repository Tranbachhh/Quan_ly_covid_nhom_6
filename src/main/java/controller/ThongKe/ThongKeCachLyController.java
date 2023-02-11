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
import repository.CachLyRepository;
import repository.CachLyRepositoryImpl;

import java.net.URL;
import java.util.ResourceBundle;


public class ThongKeCachLyController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane noiCachLy;

    @FXML
    private Pane mucDo;

    @FXML
    private Pane bieuDo;

    CachLyRepository CachLyRepo = new CachLyRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // noiCachLy
        ObservableList<PieChart.Data> dataNoiCachLy = FXCollections.observableArrayList(
                new PieChart.Data("Tại nhà", CachLyRepo.tongTaiNha()),
                new PieChart.Data("Khu cách ly", CachLyRepo.tongCachLy()-CachLyRepo.tongTaiNha()));
        final PieChart chartNoiCachLy = new PieChart(dataNoiCachLy);
        dataNoiCachLy.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartNoiCachLy.setPrefSize(300, 270);
        chartNoiCachLy.setLegendVisible(false);

        noiCachLy.getChildren().add(chartNoiCachLy);

        // mucDo
        ObservableList<PieChart.Data> dataMucDo = FXCollections.observableArrayList(
                new PieChart.Data("F0", CachLyRepo.tongF0()),
                new PieChart.Data("F1", CachLyRepo.tongF1()),
                new PieChart.Data("F2", CachLyRepo.tongF2()),
                new PieChart.Data("Chưa rõ", CachLyRepo.tongChuaRo()));
        final PieChart chartMucDo = new PieChart(dataMucDo);
        dataMucDo.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartMucDo.setPrefSize(450, 500);
        chartMucDo.setLegendVisible(false);

        mucDo.getChildren().add(chartMucDo);

        // bieuDo
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tháng");
        NumberAxis yAxis = new NumberAxis(0,5,1);//fix cứng cột y giới hạn từ 1 đến 5 sẽ update sau
        yAxis.setLabel("Số lượng");

        LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

        XYChart.Series<String,Number> dataSeries = new XYChart.Series();
        dataSeries.setName("Biểu đồ cách ly");
        dataSeries.getData().add(new XYChart.Data("Jan", CachLyRepo.tongCachLyTheoThang(1)));
        dataSeries.getData().add(new XYChart.Data("Feb", CachLyRepo.tongCachLyTheoThang(2)));
        dataSeries.getData().add(new XYChart.Data("Mar", CachLyRepo.tongCachLyTheoThang(3)));
        dataSeries.getData().add(new XYChart.Data("Apr", CachLyRepo.tongCachLyTheoThang(4)));
        dataSeries.getData().add(new XYChart.Data("May", CachLyRepo.tongCachLyTheoThang(5)));
        dataSeries.getData().add(new XYChart.Data("Jun", CachLyRepo.tongCachLyTheoThang(6)));
        dataSeries.getData().add(new XYChart.Data("Jul", CachLyRepo.tongCachLyTheoThang(7)));
        dataSeries.getData().add(new XYChart.Data("Aug", CachLyRepo.tongCachLyTheoThang(8)));
        dataSeries.getData().add(new XYChart.Data("Sep", CachLyRepo.tongCachLyTheoThang(9)));
        dataSeries.getData().add(new XYChart.Data("Oct", CachLyRepo.tongCachLyTheoThang(10)));
        dataSeries.getData().add(new XYChart.Data("Nov", CachLyRepo.tongCachLyTheoThang(11)));
        dataSeries.getData().add(new XYChart.Data("Dec", CachLyRepo.tongCachLyTheoThang(12)));
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