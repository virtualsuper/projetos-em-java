package caracteristicas.hashCode;

public class Subscribe {

  public static void main(String[] args) {

    Carro carro1 = new Carro("Palio", "Fiat", 20000.0);
    Carro carro2 = new Carro("Palio", "Fiat", 20000.0);

    System.out.println(carro1.equals(carro2));
  }

}
