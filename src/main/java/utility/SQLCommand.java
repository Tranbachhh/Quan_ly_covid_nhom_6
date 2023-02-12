package utility;

public class SQLCommand {
    // Users
    public static String USER_QUERY_DANG_NHAP = "SELECT * FROM users WHERE Username = ? AND Password = ?";
    //Nhân khẩu
    public static String NHAN_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Thường trú'";
    public static String NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Đã chuyển đi'";
    public static String NHAN_KHAU_QUERY_TONG_DA_MAT = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Đã mất'";
    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Tạm trú'";
    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Tạm vắng'";
    public static String NHAN_KHAU_QUERY_NAM = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nam'";
    public static String NHAN_KHAU_QUERY_NU = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nữ'";
    public static String NHAN_KHAU_QUERY_DUOI_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) <= 17";
    public static String NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 18 AND YEAR(NOW()) - YEAR(ngaySinh) <= 64";
    public static String NHAN_KHAU_QUERY_TREN_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 65";

    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";
    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_ID = "SELECT * FROM nhan_khau WHERE idNhanKhau = ?" ;
    public static String  NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_HO_TEN="SELECT * FROM `nhan_khau` WHERE hoTen like ?";
    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_CMND="SELECT * FROM `nhan_khau` WHERE cmnd like ?";
    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_TRANG_THAI="SELECT * FROM `nhan_khau` WHERE trangThai like ?";
    public static String  NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_NGAY_SINH="SELECT * FROM `nhan_khau` WHERE ngaySinh like ?";

    public static String NHAN_KHAU_QUERY_INSERT_NHANKHAU="INSERT INTO `nhan_khau`( `hoTen`, `ngaySinh`, `noiSinh`, `gioiTinh`, `nguyenQuan`, `danToc`, `tonGiao`, `quocTich`, `ngheNghiep`, `noiLamViec`, `cmnd`, `ngayCap`, `noiCap`, `chuyenDenNgay`, `noiThuongTruTruoc`, `trangThai`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_KHAITU="INSERT INTO `khai_tu`(`idNguoiMat`, `idNguoiKhai`, `ngayKhai`, `ngayMat`, `liDoMat`) VALUES (?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_TAMVANG="INSERT INTO `tam_vang`( `idNhanKhau`, `noiTamTru`, `tuNgay`,`denNgay`, `lyDo`) VALUES (?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_TAMTRU="INSERT INTO `tam_tru`( `idNhanKhau`, `noiThuongTru`, `noiTamTru`, `tuNgay`,`denNgay`, `lyDo`) VALUES (?,?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_CHUYENNHANKHAU="INSERT INTO `chuyen_nhan_khau`( `idNhanKhau`, `ngayChuyenDi`, `noiChuyenDen`, `ghiChu`) VALUES (?,?,?,?)";

    public static String NHAN_KHAU_QUERY_UPDATE_TRANGTHAI    ="UPDATE `nhan_khau` SET " +
            "`trangThai`=?  WHERE idNhanKhau  =";
    public static String NHAN_KHAU_QUERY_UPDATE =  "UPDATE `nhan_khau` SET " +
            "`hoTen`=?," +
            "`ngaySinh`=?," +
            "`noiSinh`=?," +
            "`gioiTinh`=?," +
            "`nguyenQuan`=?," +
            "`danToc`=?," +
            "`tonGiao`=?," +
            "`quocTich`=?," +
            "`ngheNghiep`=?," +
            "`noiLamViec`=?," +
            "`cmnd`=?," +
            "`ngayCap`=?," +
            "`noiCap`=?," +
            "`chuyenDenNgay`=?," +
            "`noiThuongTruTruoc`=?  WHERE idNhanKhau  = ";
    public static String NHAN_KHAU_DELETE_NK = "DELETE FROM `nhan_khau` WHERE idNhanKHau = ?";

    // ho_khau
    public static String HO_KHAU_QUERY_TONG = "SELECT COUNT(*) FROM ho_khau";
    public static String HO_KHAU_QUERY_IDHOKHAU_MOI_NHAT = "SELECT idHoKhau FROM `ho_khau` ORDER BY idHoKhau DESC";

    public static String HO_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM ho_khau";
    public static String HO_KHAU_QUERY_LOADDATAHOKHAUCONTROLLER = "SELECT hk.*, nk.hoTen FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuHo = nk.idNhanKhau";
    public static String HO_KHAU_QUERY_LOADDATAXEMHOKHAUCONTROLLER = "SELECT hknk.idHoKhau, hknk.idNhanKhau, hknk.quanHeChuHo, nk.hoTen, nk.ngaySinh, nk.cmnd FROM `ho_khau_nhan_khau` hknk, `nhan_khau` nk WHERE hknk.idNhanKhau = nk.idNhanKhau and hknk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_LOADDATASUAHKCONTROLLER = "SELECT hknk.idHoKhau, hknk.idNhanKhau, hknk.quanHeChuHo, nk.cmnd, nk.hoTen, nk.ngaySinh FROM `ho_khau_nhan_khau` hknk, `nhan_khau` nk WHERE hknk.idNhanKhau = nk.idNhanKhau and hknk.idHoKhau = ?";

    public static String HO_KHAU_QUERY_HOTEN_CHU_HO = "SELECT nk.hoTen FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK = "SELECT * FROM `ho_khau_nhan_khau`";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK_1 = "SELECT * FROM `ho_khau_nhan_khau` WHERE idHoKhau != ?";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_HK = "SELECT * FROM `ho_khau` WHERE idHoKhau != ?";

    public static String HO_KHAU_QUERY_THEMNHANKHAU_TO_nkhk = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_THEMHOKHAU = "INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao) VALUES (?,?,?,?,?,?)";

    public static String HO_KHAU_QUERY_CHANGE_ID_CHUHO = "UPDATE `ho_khau` SET idChuHo = ? WHERE idHoKhau = ?";

    public static String HO_KHAU_QUERY_CLEAR_HKNK = "DELETE FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE = "SELECT nk.hoTen, nk.idNhanKhau FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";

    public static String HO_KHAU_QUERY_DELETE_HK = "DELETE FROM `ho_khau` WHERE idHoKhau = ?";

    //Khai_bao
    public static String KHAI_BAO_QUERY_LOADDATAKHAIBAOCONTROLLER ="SELECT kb.*,nk.hoTen,nk.cmnd FROM `khai_bao` kb, `nhan_khau` nk WHERE kb.idNhanKhau=nk.idNhanKhau";
    public static String KHAI_BAO_QUERY_THEMKHAIBAO="INSERT INTO `khai_bao`(idNhanKhau,ngayKhaiBao,trieuChung,tinhTrangSucKhoe) VALUES (?,?,?,?)";
    public static String KHAI_BAO_QUERY_DELETE_KB="DELETE FROM `khai_bao` WHERE id = ?";
    public static String KHAI_BAO_QUERY_TONG="SELECT COUNT(*) FROM khai_bao";
    public static String KHAI_BAO_QUERY_TONG_TRIEUCHUNGCO="SELECT COUNT(*) FROM `khai_bao` WHERE trieuChung =N'Có'";
    public static String KHAI_BAO_QUERY_TONG_THEOTHANG="SELECT COUNT(*) FROM `khai_bao` WHERE MONTH(ngayKhaiBao) = ?";
    public static String KHAI_BAO_QUERY_TONG_BINHTHUONG="SELECT COUNT(*) FROM `khai_bao` WHERE tinhTrangSucKhoe like '%Bình thường%'";
    public static String KHAI_BAO_QUERY_TONG_HO="SELECT COUNT(*) FROM `khai_bao` WHERE tinhTrangSucKhoe like '%Ho%'";
    public static String KHAI_BAO_QUERY_TONG_SOT="SELECT COUNT(*) FROM `khai_bao` WHERE tinhTrangSucKhoe like '%Sốt%'";

    //Test
    public static String TEST_COVID_QUERY_LOADDATATESTCONTROLLER="SELECT test.*,nk.hoTen,nk.cmnd FROM `test_covid` test, `nhan_khau` nk WHERE test.idNhanKhau=nk.idNhanKhau";
    public static String TEST_COVID_QUERY_DELETE_TEST="DELETE FROM `test_covid` WHERE id = ?";
    public static String TEST_COVID_QUERY_TONG="SELECT COUNT(*) FROM test_covid";
    public static String TEST_COVID_QUERY_THEMTEST="INSERT INTO `test_covid`(idNhanKhau,thoiDiemTest,hinhThucTest,ketQua) VALUES (?,?,?,?)";
    public static String TEST_COVID_QUERY_TONG_DUONGTINH="SELECT COUNT(*) FROM `test_covid` WHERE ketQua =N'Dương tính'";
    public static String TEST_COVID_QUERY_TONG_PCR="SELECT COUNT(*) FROM `test_covid` WHERE hinhThucTest =N'PCR'";
    public static String TEST_COVID_QUERY_TONG_THEOTHANG="SELECT COUNT(*) FROM `test_covid` WHERE MONTH(thoiDiemTest) = ?";

    //Cách Ly
    public static String CACH_LY_QUERY_LOADDATACACHLYCONTROLLER="SELECT cl.*,nk.hoTen,nk.cmnd FROM `cach_ly` cl, `nhan_khau` nk WHERE cl.idNhanKhau=nk.idNhanKhau";
    public static String CACH_LY_QUERY_DELETE_CACHLY="DELETE FROM `cach_ly` WHERE id = ?";
    public static String CACH_LY_QUERY_THEMCACHLY="INSERT INTO `cach_ly`(idNhanKhau,ngayBatDau,soNgayCachLy,noiCachLy,mucDoCachLy,lyDoCachLy) VALUES (?,?,?,?,?,?)";
    public static String CACH_LY_QUERY_TONG="SELECT COUNT(*) FROM cach_ly";
    public static String CACH_LY_QUERY_TONG_FO="SELECT COUNT(*) FROM `cach_ly` WHERE mucDoCachLy =N'F0'";
    public static String CACH_LY_QUERY_TONG_F1="SELECT COUNT(*) FROM `cach_ly` WHERE mucDoCachLy =N'F1'";
    public static String CACH_LY_QUERY_TONG_F2="SELECT COUNT(*) FROM `cach_ly` WHERE mucDoCachLy =N'F2'";
    public static String CACH_LY_QUERY_TONG_CHUARO="SELECT COUNT(*) FROM `cach_ly` WHERE mucDoCachLy =N'Chưa rõ'";
    public static String CACH_LY_QUERY_TONG_TAINHA="SELECT COUNT(*) FROM `cach_ly` WHERE noiCachLy =N'Tại nhà'";
    public static String CACH_LY_QUERY_TONG_THEOTHANG="SELECT COUNT(*) FROM `cach_ly` WHERE MONTH(ngayBatDau) = ?";
    public static String TIEM_COVID_QUERY_LOADDATATIEMCOVIDCONTROLLER="SELECT tcv.*,nk.hoTen,nk.cmnd FROM `tiem_covid` tcv, `nhan_khau` nk WHERE tcv.idNhanKhau=nk.idNhanKhau";
    public static String TIEM_COVID_QUERY_THEMTIEMCOVIDLY="INSERT INTO `tiem_covid`(idNhanKhau,noiTiem,soMui) values (?,?,?)";
    public static String TIEM_COVID_QUERY_DELETE_TIEMCOVID="DELETE FROM `tiem_covid` WHERE id = ?";
}
