package Exercicios.polimorfismo;

public class Exercicio {

  public static void main(String[] args) {
    Gerente gerente = new Gerente();
    gerente.setSalario(8000);
    System.out.println("Gerente: R$" + gerente.calculaImpostos());

    Supervisor supervisor = new Supervisor();
    supervisor.setSalario(8000);
    System.out.println("Supervisor: R$" + supervisor.calculaImpostos());

    Atendente atendente = new Atendente();
    atendente.setSalario(8000);
    System.out.println("Atendente: R$" + atendente.calculaImpostos());

  }

}
