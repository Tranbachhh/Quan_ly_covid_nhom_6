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
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;

import java.net.URL;
import java.util.ResourceBundle;


public class ThongKeNhanKhauController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane gioiTinh;

    @FXML
    private Pane trangThai;

    @FXML
    private Pane lopTuoi;

    NhanKhauRepository nhanKhauRepository = new NhanKhauRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // gioiTinh
        ObservableList<PieChart.Data> dataGioiTinh = FXCollections.observableArrayList(
                new PieChart.Data("Nam", nhanKhauRepository.tongNam()),
                new PieChart.Data("Nữ", nhanKhauRepository.tongNu()));
        final PieChart chartGioiTinh = new PieChart(dataGioiTinh);
        dataGioiTinh.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartGioiTinh.setPrefSize(300, 270);
        chartGioiTinh.setLegendVisible(false);

        gioiTinh.getChildren().add(chartGioiTinh);

        // trangThai
        ObservableList<PieChart.Data> dataTrangThai = FXCollections.observableArrayList(
                new PieChart.Data("Thường trú", nhanKhauRepository.tongNhanKhauThuongTru()),
                new PieChart.Data("Tạm trú", nhanKhauRepository.tongNhanKhauTamTru()),
                new PieChart.Data("Tạm vắng", nhanKhauRepository.tongNhanKhauTamVang()),
                new PieChart.Data("Đã mất", nhanKhauRepository.tongNhanKhauDaMat()),
                new PieChart.Data("Đã chuyển đi", nhanKhauRepository.tongNhanKhauDaChuyenDi()));
        final PieChart chartTrangThai = new PieChart(dataTrangThai);
        dataTrangThai.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartTrangThai.setPrefSize(450, 500);
        chartTrangThai.setLegendVisible(false);

        trangThai.getChildren().add(chartTrangThai);

        // lopTuoi
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Lớp tuổi");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Số người");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Lớp tuổi");
        dataSeries.getData().add(new XYChart.Data("Dưới tuổi lao động", nhanKhauRepository.tongDuoiTuoiLaoDong()));
        dataSeries.getData().add(new XYChart.Data("Độ tuổi lao động"  , nhanKhauRepository.tongDoTuoiLaoDong()));
        dataSeries.getData().add(new XYChart.Data("Trên tuổi lao động"  , nhanKhauRepository.tongTrenTuoiLaoDong()));
        barChart.getData().add(dataSeries);

        barChart.setPrefSize(500, 300);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lopTuoi.getChildren().add(barChart);
    }

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
