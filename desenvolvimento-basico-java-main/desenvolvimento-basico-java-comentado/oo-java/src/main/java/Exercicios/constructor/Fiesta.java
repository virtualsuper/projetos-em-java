package Exercicios.constructor;

public class Fiesta {

  public static void main(String[] args) {

    Car fiesta = new Car("Volskwagen", "Hatch", 2015);

    System.out.printf("Marca: %s \n", fiesta.getBrand());
    System.out.printf("Modelo: %s \n", fiesta.getModel());
    System.out.printf("Ano: %s", fiesta.getYear());
  }
}
