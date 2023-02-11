package controller.NhanKhau;

import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;

public class ChiTietNhanKhauController {
    @FXML
    Label hoTenLabel;
    @FXML
    Label ngaySinhLabel;
    @FXML
    Label noiSinhLabel;
    @FXML
    Label gioiTinhLabel;
    @FXML
    Label nguyenQuanLabel;
    @FXML
    Label danTocLabel;
    @FXML
    Label tonGiaoLabel;
    @FXML
    Label quocTichLabel;
    @FXML
    Label ngheNghiepLabel;
    @FXML
    Label noiLamViecLabel;
    @FXML
    Label CMNDLabel;
    @FXML
    Label ngayCapLabel;
    @FXML
    Label noiCapLabel;
    @FXML
    Label chuyenDenNgayLabel;
    @FXML
    Label noiThuongTruTruocLabel;
    @FXML
    Label trangThaiLabel;
    NhanKhau f=new NhanKhau();
    static NhanKhauRepository NhanKhauRepo = new NhanKhauRepositoryImpl();

    public void setNhanKhau(NhanKhau nk)  {
        clean();
        loadData(nk.getId());
    }
    @FXML
    private void loadData(int id_NK) {
     f= NhanKhauRepo.chiTietNhanKhauData(id_NK);

        hoTenLabel.setText(f.getHoTen());
        ngaySinhLabel.setText(f.getBieuDienNgaySinh());
        noiSinhLabel.setText(f.getNoiSinh());
        gioiTinhLabel.setText(f.getGioiTinh());
        nguyenQuanLabel.setText(f.getNguyenQuan());
        danTocLabel.setText(f.getDanToc());
        tonGiaoLabel.setText(f.getTonGiao());
        quocTichLabel.setText(f.getQuocTich());
        ngheNghiepLabel.setText(f.getNgheNghiep());
        noiLamViecLabel.setText(f.getNoiLamViec());
        CMNDLabel.setText(f.getCMND());
        ngayCapLabel.setText(f.getBieuDienNgayCap());
        noiCapLabel.setText(f.getNoiCap());
        chuyenDenNgayLabel.setText(f.getBieuDienChuyenDenNgay());
        noiThuongTruTruocLabel.setText(f.getNoiThuongTruTruoc());
        trangThaiLabel.setText(f.getTrangThai());
    }

    @FXML
    private void clean() {
        hoTenLabel.setText(null);
        ngaySinhLabel.setText(null);
        noiSinhLabel.setText(null);
        gioiTinhLabel.setText(null);
        nguyenQuanLabel.setText(null);
        danTocLabel.setText(null);
        tonGiaoLabel.setText(null);
        quocTichLabel.setText(null);
        ngheNghiepLabel.setText(null);
        noiLamViecLabel.setText(null);
        CMNDLabel.setText(null);
        ngayCapLabel.setText(null);
        noiCapLabel.setText(null);
        chuyenDenNgayLabel.setText(null);
        noiThuongTruTruocLabel.setText(null);
        trangThaiLabel.setText(null);
    }

}