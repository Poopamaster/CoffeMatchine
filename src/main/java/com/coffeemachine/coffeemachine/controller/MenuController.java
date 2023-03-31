package com.coffeemachine.coffeemachine.controller;

import com.coffeemachine.coffeemachine.HelloApplication;
import com.coffeemachine.coffeemachine.machine.CoffeeMachine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button buyButton;
    @FXML
    private Button fillButton;
    @FXML
    private Button withDrawButton;
    @FXML
    private Button statusButton;
    @FXML
    private ImageView buyIconImageView;

    @FXML
    private ImageView fillIconImageView;

    @FXML
    private ImageView withDrawIconImageView;

    @FXML
    private ImageView statusIconImageView;

    @FXML
    private ImageView mainIcon;

    private CoffeeMachine coffeeMachine;

    public MenuController(CoffeeMachine coffeeMachine){
        this.coffeeMachine = coffeeMachine;
    }

    // ส่วนนี้เป็นฟังก์ชันเริ่มต้นของคลาส MenuController

    @FXML
    public void initialize() {
        // โหลดไอคอนที่จำเป็นสำหรับส่วนต่างๆ
        Image main = new Image(getClass().getResourceAsStream("/coffee-cup.png"));
        Image buyIcon = new Image(getClass().getResourceAsStream("/coffee-cup.png"));
        Image fillIcon = new Image(getClass().getResourceAsStream("/water.png"));
        Image withDrawIcon = new Image(getClass().getResourceAsStream("/cash.png"));
        Image statusIcon = new Image(getClass().getResourceAsStream("/beans.png"));
        // กำหนดค่าไอคอนให้กับ ImageView ต่างๆ
        buyIconImageView.setImage(buyIcon);
        fillIconImageView.setImage(fillIcon);
        withDrawIconImageView.setImage(withDrawIcon);
        statusIconImageView.setImage(statusIcon);

        mainIcon.setImage(main);
        // กำหนดการดำเนินการของปุ่ม
        buyButton.setOnAction(e -> {
            try {
                navigateToBuyPage();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        fillButton.setOnAction(e -> {
            try {
                navigateToFillPage();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        withDrawButton.setOnAction(e -> {
            try {
                navigateToWithdrawPage();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        statusButton.setOnAction(e -> {
            try {
                navigateToStatusPage();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }
    // ฟังก์ชันสำหรับนำไปหน้าต่าง ๆ
    private void navigateToBuyPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("buy.fxml"));
        BuyController buyController = new BuyController(coffeeMachine);
        loader.setController(buyController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) buyButton.getScene().getWindow();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
    }
    private void navigateToFillPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("fill.fxml"));
        FillController fillController = new FillController(coffeeMachine);
        loader.setController(fillController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) fillButton.getScene().getWindow();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
    }
    private void navigateToWithdrawPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("withdraw.fxml"));
        WithdrawController withdrawController = new WithdrawController(coffeeMachine);
        loader.setController(withdrawController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) withDrawButton.getScene().getWindow();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
    }
    private void navigateToStatusPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("status.fxml"));
        StatusController withdrawController = new StatusController(coffeeMachine);
        loader.setController(withdrawController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) statusButton.getScene().getWindow();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
    }
}