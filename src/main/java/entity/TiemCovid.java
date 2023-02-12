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
public class TiemCovid {
    private Integer id;
    private String hoTen;
    private String NoiTiem;
    private Integer soMui;
    private Integer idNhanKhau;
    public void copy_tiemcovid(TiemCovid a){
        this.id=a.getId();
        this.hoTen=a.getHoTen();
        this.NoiTiem=a.getNoiTiem();
        this.soMui=a.getSoMui();
        this.idNhanKhau=a.getIdNhanKhau();
    }

    public TiemCovid(int id, int idNhanKhau,String hoTen, String noiTiem, int soMui){
        this.id=id;
        this.NoiTiem=noiTiem;
        this.soMui=soMui;
        this.idNhanKhau=idNhanKhau;
        this.hoTen=hoTen;
    }
}
