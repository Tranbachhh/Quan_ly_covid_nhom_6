package repository;

import entity.TestCovid;
import javafx.collections.ObservableList;

public interface TestCovidRepository {
    public ObservableList<TestCovid> loadDataTestController();
    public int tongTest();
    public void delete_test(int id);
    public void themTest(TestCovid f);
    public int tongDuongTinh();
    public int tongPCR();
    public int tongTestCovidTheoThang(int month);

}
