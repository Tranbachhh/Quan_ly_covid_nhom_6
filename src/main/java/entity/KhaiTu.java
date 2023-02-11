package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KhaiTu {
    private Integer idNguoiMat;
    private Integer idNguoiKhai;
    private LocalDateTime ngayMat;
    private LocalDateTime ngayKhai;
    private String liDoMat;
    private String bieuDienNgayMat;
    private String bieuDienNgayKhai;

    public KhaiTu(int idNguoiMat,int idNguoiKhai,String bieuDienNgayKhai,String bieuDienNgayMat,String liDoMat){
        this.idNguoiMat=idNguoiMat;
        this.idNguoiKhai=idNguoiKhai;
        this.bieuDienNgayKhai=bieuDienNgayKhai;
        this.bieuDienNgayMat=bieuDienNgayMat;
        this.liDoMat=liDoMat;
    }
}