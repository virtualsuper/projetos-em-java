package polimorfismo;

public class Carro extends Vehicle{

  public double calculaImposto() {
    return this.getValorVenal() * 0.07;
  }
}
