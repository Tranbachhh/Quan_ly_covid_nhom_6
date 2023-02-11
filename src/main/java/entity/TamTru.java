package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TamTru {
    private Integer id;
    private Integer idNhanKhau;
    private String noiThuongTru;
    private String noiTamTru;
    private LocalDate tuNgay;
    private LocalDate denNgay;
    private String lyDo;
    private String bieuDienTuNgay;
    private String bieuDienDenNgay;

    public TamTru(int idNhanKhau,String noiThuongTru,String noiTamTru,String bieuDienTuNgay,String bieuDienDenNgay,String lyDo){
        this.idNhanKhau=idNhanKhau;
        this.noiThuongTru=noiThuongTru;
        this.noiTamTru=noiTamTru;
        this.bieuDienTuNgay=bieuDienTuNgay;
        this.bieuDienDenNgay=bieuDienDenNgay;
        this.lyDo=lyDo;
    }
}