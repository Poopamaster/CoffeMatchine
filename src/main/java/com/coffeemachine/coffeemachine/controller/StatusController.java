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

public class StatusController {
    CoffeeMachine coffeeMachine;
    @FXML
    private Label water;

    @FXML
    private Label milk;

    @FXML
    private Label beans;

    @FXML
    private Label cups;

    @FXML
    private Label cash;
    @FXML
    private Button backButton;
    //ทำการ อัพเดทตัว coffeemachine
    public StatusController(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    //อัพเพทข้อมูล วัตถุดิบ
    private void updateResourceText(){
        water.setText(coffeeMachine.getWater()+" ml remaining");
        milk.setText(coffeeMachine.getMilk()+" ml remaining");
        beans.setText(coffeeMachine.getBeans()+" g remaining");
        cups.setText(coffeeMachine.getCup()+" cup(s) remaining");
        cash.setText(coffeeMachine.getCash()+ " baht in the machine");
    }

    //ตรงนี้จะทำการ เรียกใช้ เมื่อมาหน้านี้
    @FXML
    public void initialize() {

        updateResourceText();
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
