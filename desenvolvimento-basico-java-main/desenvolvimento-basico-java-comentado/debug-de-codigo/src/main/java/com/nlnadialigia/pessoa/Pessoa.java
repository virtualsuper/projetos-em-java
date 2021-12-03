package com.nlnadialigia.pessoa;

public class Pessoa {

  private final String nome;
  private final Double altura;
  private final Double peso;

  public Pessoa(final String nome, final Double altura, final Double peso) {
    this.nome = nome;
    this.altura = altura;
    this.peso = peso;
  }

  public String getNome() {
    return nome;
  }

  public Double getAltura() {
    return altura;
  }

  public Double getPeso() {
    return peso;
  }
}

