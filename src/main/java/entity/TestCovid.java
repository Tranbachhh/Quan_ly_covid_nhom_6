package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestCovid {
    private Integer id;
    private String hoTen;
    private Integer idNhanKhau;
    private String CMND;
    private Date thoiDiemTest;
    private String hinhThucTest;
    private String ketQua;

    public void copy_test(TestCovid a){
        this.id=a.getId();
        this.hoTen=a.getHoTen();
        this.idNhanKhau=a.getIdNhanKhau();
        this.CMND=a.getCMND();
        this.thoiDiemTest=a.getThoiDiemTest();
        this.hinhThucTest=a.getHinhThucTest();
        this.ketQua=a.getKetQua();
    }

    public TestCovid(int idNhanKhau,Date thoiDiemTest,String hinhThucTest,String ketQua){
        this.idNhanKhau=idNhanKhau;
        this.thoiDiemTest=thoiDiemTest;
        this.hinhThucTest=hinhThucTest;
        this.ketQua=ketQua;
    }

}

