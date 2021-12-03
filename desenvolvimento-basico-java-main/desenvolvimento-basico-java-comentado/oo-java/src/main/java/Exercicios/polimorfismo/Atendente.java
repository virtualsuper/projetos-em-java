package Exercicios.polimorfismo;

public class Atendente extends Funcionarios{

  @Override
  public double calculaImpostos() {
    return this.getSalario() * 0.01;
  }
}
