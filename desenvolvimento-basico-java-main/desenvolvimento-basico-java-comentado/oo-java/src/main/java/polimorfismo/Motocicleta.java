package polimorfismo;

public class Motocicleta extends Vehicle {

    public double calculaImposto() {
      return this.getValorVenal() * 0.03;
    }
}
