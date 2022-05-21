module com.example.demo2 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.junit.jupiter.api;
    requires junit;

    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
}