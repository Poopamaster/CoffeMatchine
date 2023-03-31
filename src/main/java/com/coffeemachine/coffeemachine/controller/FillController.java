package com.coffeemachine.coffeemachine.controller;

import com.coffeemachine.coffeemachine.HelloApplication;
import com.coffeemachine.coffeemachine.machine.CoffeeMachine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class FillController {
    @FXML
    private ImageView espressoImage;
    @FXML
    private ImageView cappuccinoImage;
    @FXML
    private ImageView latteImage;
    @FXML
    private ImageView backImage;
    @FXML
    private Button waterButton;
    @FXML
    private Button milkButton;
    @FXML
    private Button beansButton;
    @FXML
    private Button cupButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField    message;

    @FXML
    private Label water;

    @FXML
    private Label milk;

    @FXML
    private Label cup;

    @FXML
    private Label beans;

    int amount = 0;


    CoffeeMachine coffeeMachine;

    public FillController(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }
    // ฟังก์ชันสำหรับตรวจสอบว่าข้อความที่ป้อนเป็นตัวเลขหรือไม่
    private Boolean isNumeric(String number){
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if(number == null){
            return false;
        }
        return pattern.matcher(number).matches();
    }
    private void handleButtonClick() {
        // รับข้อความจาก TextField
        String inputText = message.getText();
        System.out.println("User entered: " + inputText);
        if(isNumeric(inputText)) {
            amount = Integer.parseInt(inputText);
        }else{
            message.setText("Please enter number only");

        }

    }
    // ฟังก์ชันสำหรับอัพเดทข้อความของแหล่งทรัพยากร
    private void updateResourceText(){
        water.setText(coffeeMachine.getWater()+" ml remaining");
        milk.setText(coffeeMachine.getMilk()+" ml remaining");
        beans.setText(coffeeMachine.getBeans()+" g remaining");
        cup.setText(coffeeMachine.getCup()+" cup(s) remaining");
    }

    @FXML
    public void initialize() {
        updateResourceText();

        message.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                message.clear();

            }
        });
        // กำหนดการตั้งค่าสำหรับแต่ละปุ่ม
        cupButton.setOnAction(e -> {
            handleButtonClick();

            message.setText(coffeeMachine.doFillCup(amount));
            updateResourceText();

        });

        waterButton.setOnAction(e -> {
            handleButtonClick();

            message.setText(coffeeMachine.doFillWater(amount));
            updateResourceText();

        });
        milkButton.setOnAction(e -> {
            handleButtonClick();

            message.setText(coffeeMachine.doFillMilk(amount));
            updateResourceText();

        });
        beansButton.setOnAction(e -> {
            handleButtonClick();

            message.setText(coffeeMachine.doFillBeans(amount));
            updateResourceText();

        });
        // กำหนดการตั้งค่าสำหรับปุ่มย้อนกลับ
        backButton.setOnAction(e -> {
            try {
                navigateToMenuPage();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }

    // ฟังก์ชันสำหรับนำเสนอหน้าเมนู
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
