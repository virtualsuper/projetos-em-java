package com.nlnadialigia;

import com.nlnadialigia.imc.CalculadorDeImc;
import com.nlnadialigia.pessoa.Pessoa;

public class Programa {

  public static void main(final String[] args) {
    final Pessoa pessoa = new Pessoa("André", 1.9, 100.00);

    final var calculadorDeImc = new CalculadorDeImc();
    final var imc = calculadorDeImc.calcula(pessoa);

    System.out.printf("IMC = %.2f", imc);
  }

}
