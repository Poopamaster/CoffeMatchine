package com.coffeemachine.coffeemachine.controller;

import com.coffeemachine.coffeemachine.HelloApplication;
import com.coffeemachine.coffeemachine.machine.CoffeeMachine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WithdrawController {
    CoffeeMachine coffeeMachine;
    @FXML
    private Button backButton;
    @FXML
    private Label message;

    public WithdrawController(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @FXML
    public void initialize() {

        //แสดงข้อความหลังจาก เอาเงินออกจากเครื่อง
        message.setText(coffeeMachine.doTakeMoney() +" baht has withdraw from the machine.");



        backButton.setOnAction(e -> {
            try {
                navigateToMenuPage();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }

    private void navigateToMenuPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        MenuController menuController = new MenuController(coffeeMachine);
        loader.setController(menuController);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) backButton.getScene().getWindow();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);

    }
}
