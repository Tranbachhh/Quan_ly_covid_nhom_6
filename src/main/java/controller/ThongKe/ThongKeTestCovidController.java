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
import repository.TestCovidRepository;
import repository.TestCovidRepositoryImpl;

import java.net.URL;
import java.util.ResourceBundle;


public class ThongKeTestCovidController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane ketQua;

    @FXML
    private Pane hinhThucTest;

    @FXML
    private Pane bieuDo;

    TestCovidRepository TestRepo = new TestCovidRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // noiCachLy
        ObservableList<PieChart.Data> dataKetQua = FXCollections.observableArrayList(
                new PieChart.Data("Dương tính", TestRepo.tongDuongTinh()),
                new PieChart.Data("Âm tính", TestRepo.tongTest()-TestRepo.tongDuongTinh()));
        final PieChart chartKetQua = new PieChart(dataKetQua);
        dataKetQua.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartKetQua.setPrefSize(350, 310);
        chartKetQua.setLegendVisible(false);

        ketQua.getChildren().add(chartKetQua);

        // mucDo
        ObservableList<PieChart.Data> dataHinhThuc = FXCollections.observableArrayList(
                new PieChart.Data("PCR", TestRepo.tongPCR()),
                new PieChart.Data("Test nhanh", TestRepo.tongTest()-TestRepo.tongPCR()));
        final PieChart chartHinhThuc = new PieChart(dataHinhThuc);
        dataHinhThuc.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartHinhThuc.setPrefSize(350, 310);
        chartHinhThuc.setLegendVisible(false);

        hinhThucTest.getChildren().add(chartHinhThuc);

        // bieuDo
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tháng");
        NumberAxis yAxis = new NumberAxis(0,5,1);//fix cứng cột y giới hạn từ 1 đến 5 sẽ update sau
        yAxis.setLabel("Số lượng");

        LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);

        XYChart.Series<String,Number> dataSeries = new XYChart.Series();
        dataSeries.setName("Biểu đồ Test Covid");
        dataSeries.getData().add(new XYChart.Data("Jan", TestRepo.tongTestCovidTheoThang(1)));
        dataSeries.getData().add(new XYChart.Data("Feb", TestRepo.tongTestCovidTheoThang(2)));
        dataSeries.getData().add(new XYChart.Data("Mar", TestRepo.tongTestCovidTheoThang(3)));
        dataSeries.getData().add(new XYChart.Data("Apr", TestRepo.tongTestCovidTheoThang(4)));
        dataSeries.getData().add(new XYChart.Data("May", TestRepo.tongTestCovidTheoThang(5)));
        dataSeries.getData().add(new XYChart.Data("Jun", TestRepo.tongTestCovidTheoThang(6)));
        dataSeries.getData().add(new XYChart.Data("Jul", TestRepo.tongTestCovidTheoThang(7)));
        dataSeries.getData().add(new XYChart.Data("Aug", TestRepo.tongTestCovidTheoThang(8)));
        dataSeries.getData().add(new XYChart.Data("Sep", TestRepo.tongTestCovidTheoThang(9)));
        dataSeries.getData().add(new XYChart.Data("Oct", TestRepo.tongTestCovidTheoThang(10)));
        dataSeries.getData().add(new XYChart.Data("Nov", TestRepo.tongTestCovidTheoThang(11)));
        dataSeries.getData().add(new XYChart.Data("Dec", TestRepo.tongTestCovidTheoThang(12)));
        lineChart.getData().add(dataSeries);

        lineChart.setPrefSize(600, 320);
        bieuDo.getChildren().add(lineChart);
    }

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
