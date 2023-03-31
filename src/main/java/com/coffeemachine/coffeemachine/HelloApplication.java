package com.coffeemachine.coffeemachine;

import com.coffeemachine.coffeemachine.controller.MenuController;
import com.coffeemachine.coffeemachine.machine.CoffeeMachine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    //HelloApplication สืบทอดจากคลาส Application ของ JavaFX ฟังก์ชัน start
    // นำเสนอหน้าต่างหลักของแอปพลิเคชันด้วยหน้า Menu ฟังก์ชันนี้ใช้ FXMLLoader เพื่อโหลดไฟล์ FXML
    // และตั้งค่า controller สำหรับหน้าต่างด้วยคลาส MenuController โดยมีการสร้างอินสแตนซ์ของ
    // CoffeeMachine ใหม่ก่อนนำไปใช้กับ MenuController ตัวอย่างนี้แสดงวิธีการตั้งค่าและเริ่มต้นแอปพลิเคชัน
    // JavaFX ที่มีการใช้งานกับคลาส CoffeeMachine และ Controller ต่าง ๆ ที่สร้างขึ้น
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        MenuController menuController = new MenuController(machine);
        fxmlLoader.setController(menuController);
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Coffee Machine!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}