package herança;

import java.util.ArrayList;

public class Example {

  public static void main(String[] args) {

    Car newCar = new Car();

    newCar.setBrand("Nissan");
    newCar.setModel("March");
    newCar.setNumberOfDoors(4);

    System.out.printf("Marca: %s", newCar.getBrand());
    System.out.println();
    System.out.printf("Modelo: %s", newCar.getModel());
    System.out.println();
    System.out.printf("Quantidade de portas: %s", newCar.getNumberOfDoors());
    System.out.println("\n****************************");


    Motorcycle moto = new Motorcycle();

    moto.setBrand("DUCATI");
    moto.setModel("STREETFIGHTER");
    moto.setCylinderPower("859");

    System.out.printf("Marca: %s", moto.getBrand());
    System.out.println();
    System.out.printf("Modelo: %s", moto.getModel());
    System.out.println();
    System.out.printf("Cilindradas: %s", moto.getCylinderPower());
  }
}
