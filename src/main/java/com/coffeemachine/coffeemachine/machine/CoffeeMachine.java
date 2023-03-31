package com.coffeemachine.coffeemachine.machine;


import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CoffeeMachine {
    //ชนิดกาแฟ
    public enum Coffee {
        ESPRESSO,
        LATTE,
        CAPPUCCINO }
    //วัตถุดิบ
    public enum Resource {WATER,MILK,BEANS,PRICE}
    //วัตถุดิบสำหรับ ESPRESSO
    static final int ESPRESSO_WATER_ML_PER_CUP = 250;
    static final int ESPRESSO_MILK_ML_PER_CUP = 0;
    static final int ESPRESSO_BEANS_G_PER_CUP = 16;
    static final int ESPRESSO_PRICE = 4;
    //วัตถุดิบสำหรับ LATTE

    static final int LATTE_WATER_ML_PER_CUP = 350;
    static final int LATTE_MILK_ML_PER_CUP = 75;
    static final int LATTE_BEANS_G_PER_CUP = 20;
    static final int LATTE_PRICE = 7;
    //วัตถุดิบสำหรับ CAPPUCCINO

    static final int CAPPUCCINO_WATER_ML_PER_CUP = 200;
    static final int CAPPUCCINO_MILK_ML_PER_CUP = 100;
    static final int CAPPUCCINO_BEANS_G_PER_CUP = 12;
    static final int CAPPUCCINO_PRICE = 6;

    // วัตถุดิบที่มีในเครื่อง
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int cash;


    private static final Scanner scanner = new Scanner(System.in);
    //mapping สำหรับเอาไว้ ค้นหา กาแฟแต่ล่ะ ชนิดไช้ วัตถุดิบอะไรบ้างเท่าไหร่
    private EnumMap<Coffee,Map<Resource,Integer>> requiredResource = new EnumMap<>(Coffee.class);



    public CoffeeMachine(int water, int milk, int beans, int cups, int cash) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.cash = cash;
        // ทำการ mapping กาแฟแต่ล่ะชนิดกับ วัตถุดิบ
        Map<Resource, Integer> espressoMap = new HashMap<>();
        espressoMap.put(Resource.WATER, ESPRESSO_WATER_ML_PER_CUP);
        espressoMap.put(Resource.MILK, ESPRESSO_MILK_ML_PER_CUP);
        espressoMap.put(Resource.BEANS, ESPRESSO_BEANS_G_PER_CUP);
        espressoMap.put(Resource.PRICE, ESPRESSO_PRICE);
        requiredResource.put(Coffee.ESPRESSO, espressoMap);

        Map<Resource, Integer> cappuccinoMap = new HashMap<>();
        cappuccinoMap.put(Resource.WATER, CAPPUCCINO_WATER_ML_PER_CUP);
        cappuccinoMap.put(Resource.MILK, CAPPUCCINO_MILK_ML_PER_CUP);
        cappuccinoMap.put(Resource.BEANS, CAPPUCCINO_BEANS_G_PER_CUP);
        cappuccinoMap.put(Resource.PRICE, CAPPUCCINO_PRICE);
        requiredResource.put(Coffee.CAPPUCCINO, cappuccinoMap);

        Map<Resource, Integer> latteMap = new HashMap<>();
        latteMap.put(Resource.WATER, LATTE_WATER_ML_PER_CUP);
        latteMap.put(Resource.MILK, LATTE_MILK_ML_PER_CUP);
        latteMap.put(Resource.BEANS, LATTE_BEANS_G_PER_CUP);
        latteMap.put(Resource.PRICE, LATTE_PRICE);
        requiredResource.put(Coffee.LATTE, latteMap);

    }



    //getter สำหรับ วัตถุดิบ และ เงิน
    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCup() {
        return cups;
    }

    public int getCash(){
        return cash;
    }

    //สำหรับ ดึงค่า วัตถิดิบของกาแฟ เช่่น getRequiredAmount(Coffee.CAPPUCCINO,Resource.WATER) จะได้ น้ำที่ไช้ของ CAPPUCCINO
    public  Integer getRequiredAmount(Coffee coffee, Resource resource){
        return requiredResource.get(coffee).get(resource);
    }
    //เชคว่ามีวัตถุดิบพอทำ กาแฟมั้ย
    boolean canMakeCoffee(int waterNeeded, int milkNeeded, int beansNeeded) {
        return water >= waterNeeded &&
                milk >= milkNeeded &&
                beans >= beansNeeded &&
                cups >= 1;
    }
    //ทำกาแฟ
    public String makeCoffee(int waterNeeded, int milkNeeded, int beansNeeded, int price) {
        if (canMakeCoffee(waterNeeded, milkNeeded, beansNeeded)) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterNeeded;
            milk -= milkNeeded;
            beans -= beansNeeded;
            cups--;
            cash += price;
            return "I have enough resources, making you a coffee!";
        } else {
            StringBuilder sb = new StringBuilder();
            if (water < waterNeeded) {
                sb.append("water");
            } else if (milk < milkNeeded) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append("milk");
            } else if (beans < beansNeeded) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append("coffee beans");
            } else if (cups == 0) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append("disposable cups");
            }
            System.out.printf("Sorry, not enough %s!\n", sb.toString());
            return "Sorry, not enough " + sb.toString()+"\n";
        }
    }


    public String makeEspresso() {
        return makeCoffee(ESPRESSO_WATER_ML_PER_CUP,
                ESPRESSO_MILK_ML_PER_CUP,
                ESPRESSO_BEANS_G_PER_CUP,
                ESPRESSO_PRICE);
    }

    public String makeLatte() {
        return makeCoffee(LATTE_WATER_ML_PER_CUP,
                LATTE_MILK_ML_PER_CUP,
                LATTE_BEANS_G_PER_CUP,
                LATTE_PRICE);
    }

    public String makeCappuccino() {
        return makeCoffee(CAPPUCCINO_WATER_ML_PER_CUP,
                CAPPUCCINO_MILK_ML_PER_CUP,
                CAPPUCCINO_BEANS_G_PER_CUP,
                CAPPUCCINO_PRICE);
    }



    // 4 methods สำหรับ เตืมวัตถุดิบให้เครื่อง ตาม จำนวนที่ไส่
    public String doFillWater(int amount){
        String message = "Filling water "+ amount+" ml"+ "from "+water+" ml";
        water += amount;
        return message + " to " + water + " ml";
    }
    public String doFillMilk(int amount){
        String message = "Filling milk "+ amount+" ml "+"from "+milk+" ml";

        milk += amount;
        return message + " to " + milk + " ml";

    }
    public String doFillBeans(int amount){
        String message = "Filling beans " +amount+" g "+" from "+beans+" g";

        beans += amount;
        return message + " to " + beans + " g";

    }
    public String doFillCup(int amount){
        String message = "Filling cups " +amount+" cup(s) "+" from "+cups+" cup(s)";

        cups += amount;
        return message + " to " + cups + " cup(s)";
    }

    //นำ เงินออกจากเครื่อง
    public int doTakeMoney() {
        System.out.println("I gave you $" + cash);
        int takeOut = cash;
        cash = 0;
        return takeOut;
    }


}

