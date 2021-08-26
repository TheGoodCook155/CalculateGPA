module com.calculate.gpa.calculategpa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.calculate.gpa.calculategpa to javafx.fxml;
    exports com.calculate.gpa.calculategpa;
}