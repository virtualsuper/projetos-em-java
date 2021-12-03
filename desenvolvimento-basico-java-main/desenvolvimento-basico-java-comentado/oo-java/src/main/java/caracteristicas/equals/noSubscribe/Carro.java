package caracteristicas.equals.noSubscribe;

import caracteristicas.equals.subscribe.Veiculo;

public class Carro extends Veiculo {

  private int quantidadedePortas;

  public Carro(String modelo, String marca, double valorVenal) {
    super(modelo, marca, valorVenal);
  }

  public Carro(String modelo, String marca, double valorVenal, int quantidadedePortas) {
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
