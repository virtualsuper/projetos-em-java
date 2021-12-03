package Exercicios.polimorfismo;

public class Funcionarios {

  private double salario;
  private double taxa;

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  public double calculaImpostos() {
    return this.salario * taxa;
  }
}
