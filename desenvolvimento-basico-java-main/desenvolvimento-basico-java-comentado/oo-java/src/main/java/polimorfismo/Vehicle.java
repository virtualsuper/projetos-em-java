package polimorfismo;

public class Vehicle {

  private double valorVenal;

  public double calculaImposto() {
    return this.valorVenal * 0.01;
  }

  public double getValorVenal() {
    return valorVenal;
  }

  public void setValorVenal(double valorVenal) {
    this.valorVenal = valorVenal;
  }
}
