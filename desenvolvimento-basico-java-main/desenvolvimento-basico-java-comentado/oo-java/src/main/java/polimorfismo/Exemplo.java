package polimorfismo;

public class Exemplo {

  public static void main(String[] args) {

    Vehicle generico = new Vehicle();
    generico.setValorVenal(1000);
    System.out.println("Genérico: " + generico.calculaImposto());

    Vehicle car = new Carro();
    car.setValorVenal(1000);
    System.out.println("Carro: " + car.calculaImposto());

    Vehicle moto = new Motocicleta();
    moto.setValorVenal(1000);
    System.out.println("Moto: " + moto.calculaImposto());

  }
}
