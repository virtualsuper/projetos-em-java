package Exercicios.polimorfismo;

public class Gerente extends Funcionarios{

  public double calculaImpostos() {
    return this.getSalario() * 0.1;
  }
}
