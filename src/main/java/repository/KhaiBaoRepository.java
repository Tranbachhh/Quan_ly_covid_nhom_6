package repository;

import entity.KhaiBao;
import javafx.collections.ObservableList;

public interface KhaiBaoRepository {
    //KhaiBaoController
    public ObservableList<KhaiBao> loadDataKhaiBaoController();

    //themKhaiBao
    public void themKhaiBao(KhaiBao f);
    //Xoá Khai Báo
    public void delete_kb(int idKhaiBao);

    public int tongKhaiBao();
    public int tongTrieuChungCo();
    public int tongBinhThuong();
    public int tongHo();
    public int tongSot();
    public int tongKhaiBaoTheoThang(int month);
}
