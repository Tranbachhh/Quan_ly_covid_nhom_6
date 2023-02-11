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
public class KhaiBao {
    private Integer id;
    private String hoTen;
    private Integer idNhanKhau;
    private String CMND;
    private Date ngayKhaiBao;
    private String trieuChung;
    private String tinhTrangSucKhoe;

    public void copy_kb(KhaiBao a){
        this.id=a.getId();
        this.hoTen=a.getHoTen();
        this.idNhanKhau=a.getIdNhanKhau();
        this.CMND=a.getCMND();
        this.ngayKhaiBao=a.getNgayKhaiBao();
        this.trieuChung=a.getTrieuChung();
        this.tinhTrangSucKhoe=a.getTinhTrangSucKhoe();
    }

    public KhaiBao(int idNhanKhau,Date ngayKhaiBao,String trieuChung,String tinhTrangSucKhoe){
        this.idNhanKhau=idNhanKhau;
        this.ngayKhaiBao=ngayKhaiBao;
        this.trieuChung=trieuChung;
        this.tinhTrangSucKhoe=tinhTrangSucKhoe;
    }

}
