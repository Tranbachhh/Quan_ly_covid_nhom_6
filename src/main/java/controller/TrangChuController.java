package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import repository.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TrangChuController implements Initializable {
    @FXML
    private Label tongNhanKhauThuongTru;

    @FXML
    private Label tongNhanKhauTamTru;

    @FXML
    private Label tongNhanKhauTamVang;

    @FXML
    private Label tongHoKhauThuongTru;

    @FXML
    private Label tongCachLy;

    @FXML
    private Label tongTest;

    @FXML
    private Label tongKhaiBaoYTe;
//
    NhanKhauRepository nhanKhauRepository = new NhanKhauRepositoryImpl();
    HoKhauRepository hoKhauRepository = new HoKhauRepositoryImpl();
    KhaiBaoRepository khaiBaoRepository = new KhaiBaoRepositoryImpl();
    TestCovidRepository testCovidRepository = new TestCovidRepositoryImpl();
    CachLyRepository cachLyRepository = new CachLyRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tongNhanKhauThuongTru.setText(String.valueOf(nhanKhauRepository.tongNhanKhauThuongTru()));
        tongNhanKhauTamTru.setText(String.valueOf(nhanKhauRepository.tongNhanKhauTamTru()));
        tongNhanKhauTamVang.setText(String.valueOf(nhanKhauRepository.tongNhanKhauTamVang()));
        tongHoKhauThuongTru.setText(String.valueOf(hoKhauRepository.tongHoKhau()));
        tongKhaiBaoYTe.setText(String.valueOf(khaiBaoRepository.tongKhaiBao()));
        tongTest.setText(String.valueOf(testCovidRepository.tongTest()));
        tongCachLy.setText(String.valueOf(cachLyRepository.tongCachLy()));
    }
}
