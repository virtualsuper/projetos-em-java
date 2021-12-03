package caracteristicas.thisSuper;

public class Car extends Veiculo{


  private int quantidadedePortas;

  public Car(String modelo, String marca, double valorVenal) {
    super(modelo, marca, valorVenal);
  }

  public Car(String modelo, String marca, double valorVenal, int quantidadedePortas) {
    super(modelo, marca, valorVenal);
    this.quantidadedePortas = quantidadedePortas;
  }

  public int getQuantidadedePortas() {
    return quantidadedePortas;
  }

  public void setQuantidadedePortas(int quantidadedePortas) {
    this.quantidadedePortas = quantidadedePortas;
  }
}
