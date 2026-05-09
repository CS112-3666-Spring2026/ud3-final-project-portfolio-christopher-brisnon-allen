module cs112.ud3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs112.ud3 to javafx.fxml;
    opens cs112.ud3.controller to javafx.fxml;
    exports cs112.ud3;
    exports cs112.ud3.controller to javafx.fxml;
}