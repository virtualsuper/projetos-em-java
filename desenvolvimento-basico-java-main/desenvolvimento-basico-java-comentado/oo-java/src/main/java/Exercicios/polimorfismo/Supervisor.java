package Exercicios.polimorfismo;

public class Supervisor extends Funcionarios{

  @Override
  public double calculaImpostos() {
    return this.getSalario() * 0.05;
  }
}
