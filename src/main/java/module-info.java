module com.coffeemachine.coffeemachine {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.coffeemachine.coffeemachine to javafx.fxml;
    exports com.coffeemachine.coffeemachine;
    exports com.coffeemachine.coffeemachine.controller;
    opens com.coffeemachine.coffeemachine.controller to javafx.fxml;
}