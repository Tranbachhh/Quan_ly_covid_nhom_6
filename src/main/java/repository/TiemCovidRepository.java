package repository;

import entity.TiemCovid;
import javafx.collections.ObservableList;

public interface TiemCovidRepository {
    public ObservableList<TiemCovid> loadDataTiemCovidController();
    public void delete_cl(int id);
    public void themTiemCoivd(TiemCovid f);

}
