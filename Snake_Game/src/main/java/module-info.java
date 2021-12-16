module com.example.snake_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires java.security.jgss;
    requires javafx.media;
    requires json.simple;

    opens com.example.snake_game to javafx.fxml;
    exports com.example.snake_game;
}