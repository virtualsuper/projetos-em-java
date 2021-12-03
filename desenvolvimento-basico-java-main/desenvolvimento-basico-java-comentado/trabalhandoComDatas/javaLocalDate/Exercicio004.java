package javaLocalDate;

/* Adicione 4 anos, 6 meses e 13 horas horas ao momento 15/05/2010 10:00:00 */

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Exercicio004 {

  public static void main(String[] args) {

    LocalDateTime dateChoice = LocalDateTime.of(2010, 5, 15, 10, 0, 0);
    System.out.println("Data escolhida: " + dateChoice);

    LocalDateTime dateResult = dateChoice.plusYears(4).plusMonths(6).plusHours(13);
    System.out.println("Resultado da operação: " + dateResult);

  }
}
