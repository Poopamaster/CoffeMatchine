package com.coffeemachine.coffeemachine.controller;

import com.coffeemachine.coffeemachine.HelloApplication;
import com.coffeemachine.coffeemachine.machine.CoffeeMachine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
// คลาส BuyController ใช้สำหรับควบคุมหน้าจอซื้อกาแฟ
public class BuyController {

    @FXML
    private ImageView espressoImage;
    @FXML
    private ImageView cappuccinoImage;
    @FXML
    private ImageView latteImage;
    @FXML
    private ImageView backImage;
    @FXML
    private Button espressoButton;
    @FXML
    private Button cappuccinoButton;
    @FXML
    private Button latteButton;
    @FXML
    private Button backButton;
    @FXML
    private Label message;

    @FXML
    private Label espressoWater;
    @FXML
    private Label espressoMilk;
    @FXML
    private Label espressoBean;
    @FXML
    private Label espressoPrice;

    @FXML
    private Label cappuccinoWater;
    @FXML
    private Label cappuccinoMilk;
    @FXML
    private Label cappuccinoBean;
    @FXML
    private Label cappuccinoPrice;

    @FXML
    private Label latteWater;
    @FXML
    private Label latteMilk;
    @FXML
    private Label latteBean;
    @FXML
    private Label lattePrice;
    CoffeeMachine coffeeMachine;
    // ฟังก์ชัน initialize จะถูกเรียกตอนเปิดหน้าจอซื้อกาแฟ
    @FXML
    public void initialize() {
        Image backIcon = new Image(getClass().getResourceAsStream("/coffee-cup.png"));
        Image coffeeIcon = new Image(getClass().getResourceAsStream("/coffee-cup.png"));

        // โหลดไอคอน




        setButtonLabel(CoffeeMachine.Coffee.ESPRESSO);
        setButtonLabel(CoffeeMachine.Coffee.CAPPUCCINO);
        setButtonLabel(CoffeeMachine.Coffee.LATTE);
        espressoImage.setImage(coffeeIcon);
        cappuccinoImage.setImage(coffeeIcon);
        latteImage.setImage(coffeeIcon);
        backImage.setImage(backIcon);
        // กำหนดการทำงานของปุ่มกาแฟต่าง ๆ
        espressoButton.setOnAction(e -> {
            message.setText(coffeeMachine.makeEspresso());
        });
        cappuccinoButton.setOnAction(e -> {
            message.setText(coffeeMachine.makeCappuccino());
        });
        latteButton.setOnAction(e -> {
            message.setText(coffeeMachine.makeLatte());
        });
        backButton.setOnAction(e -> {
            try {
                navigateToMenuPage();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }
    // ฟังก์ชันสำหรับนำทางกลับไปยังหน้าเมนู
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
    // ฟังก์ชันสำหรับตั้งค่า Label ใน buy.fxml กาแฟ
    private void setButtonLabel(CoffeeMachine.Coffee coffeeDrink) {
        // Get the required amounts and the price for the coffee drink
        // รับปริมาณที่จำเป็นและราคาสำหรับเครื่องดื่มกาแฟ
        int water = coffeeMachine.getRequiredAmount(coffeeDrink, CoffeeMachine.Resource.WATER);
        int milk = coffeeMachine.getRequiredAmount(coffeeDrink, CoffeeMachine.Resource.MILK);
        int beans = coffeeMachine.getRequiredAmount(coffeeDrink, CoffeeMachine.Resource.BEANS);
        int price = coffeeMachine.getRequiredAmount(coffeeDrink, CoffeeMachine.Resource.PRICE);

        // Set the text of the button with the required amounts and the price
        // ตั้งค่าข้อความของปุ่มด้วยปริมาณที่จำเป็นและราคา
        switch (coffeeDrink){
            case LATTE : {
                latteMilk.setText(milk+" ml");
                latteBean.setText(beans+" g");
                latteWater.setText(water+" ml");
                lattePrice.setText(price+" baht");
                break;
            }
            case ESPRESSO : {
                espressoMilk.setText(milk+" ml");
                espressoBean.setText(beans+" g");
                espressoWater.setText(water+" ml");
                espressoPrice.setText(price+" baht");
                break;
            }
            case CAPPUCCINO : {
                cappuccinoMilk.setText(milk+" ml");
                cappuccinoBean.setText(beans+" g");
                cappuccinoWater.setText(water+" ml");
                cappuccinoPrice.setText(price+" baht");
                break;
            }
        }
    }
    // คอนสตรักเตอร์ของคลาส BuyController
    public BuyController(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;

    }
}
