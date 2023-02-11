module com.example.nmcnpm20221nhom6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens view to javafx.fxml;
    opens controller to javafx.fxml;
    opens entity to javafx.fxml;

    exports view;
    exports controller;
    exports entity;
    exports controller.ThongKe;
    opens controller.ThongKe to javafx.fxml;
    exports controller.NhanKhau;
    opens controller.NhanKhau to javafx.fxml;
    exports controller.HoKhau;
    opens controller.HoKhau to javafx.fxml;
    exports controller.KhaiBao;
    opens controller.KhaiBao to javafx.fxml;
    exports controller.TestCovid;
    opens controller.TestCovid to javafx.fxml;
    exports controller.CachLy;
    opens controller.CachLy to javafx.fxml;
}
