module mediatech {
    requires transitive java.desktop;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens mediatech to javafx.fxml;
    exports mediatech;
    exports mediatech.View;    
    exports mediatech.Controller; 
}