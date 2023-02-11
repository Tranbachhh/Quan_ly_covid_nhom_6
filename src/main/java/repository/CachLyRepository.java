package repository;
import javafx.collections.ObservableList;
import entity.CachLy;


public interface CachLyRepository {
    public ObservableList<CachLy> loadDataCachLyController();
    public void delete_cl(int id);
    public void themcachly(CachLy f);
    public int tongCachLy();
    public int tongF0();
    public int tongF1();
    public int tongF2();
    public int tongChuaRo();
    public int tongTaiNha();
    public int tongCachLyTheoThang(int month);
}
