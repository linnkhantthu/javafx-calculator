module com.linn.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.linn.calculator to javafx.fxml;
    exports com.linn.calculator;
}