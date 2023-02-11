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
public class CachLy {
    private Integer id;
    private String hoTen;
    private Integer idNhanKhau;
    private String cmnd;
    private Integer soNgayCachLy;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String noiCachLy;
    private String mucDoCachLy;
    private String lyDoCachLy;

    public void copy_cachly(CachLy a){
        this.id=a.getId();
        this.hoTen=a.getHoTen();
        this.idNhanKhau=a.getIdNhanKhau();
        this.ngayBatDau=a.getNgayBatDau();
        this.cmnd=a.getCmnd();
        this.soNgayCachLy=a.getSoNgayCachLy();
        this.ngayKetThuc=a.getNgayKetThuc();
        this.noiCachLy=a.getNoiCachLy();
        this.mucDoCachLy=a.getMucDoCachLy();
        this.lyDoCachLy=a.getLyDoCachLy();
    }

    public CachLy(int idNhanKhau,Date ngayBatDau,int soNgayCachLy, String noiCachLy,String mucDoCachLy,String lyDoCachLy){
        this.idNhanKhau=idNhanKhau;
        this.ngayBatDau=ngayBatDau;
        this.soNgayCachLy=soNgayCachLy;
        this.noiCachLy=noiCachLy;
        this.mucDoCachLy=mucDoCachLy;
        this.lyDoCachLy=lyDoCachLy;
    }

}
