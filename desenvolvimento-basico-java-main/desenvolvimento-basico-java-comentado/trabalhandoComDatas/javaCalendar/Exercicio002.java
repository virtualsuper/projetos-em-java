package javaCalendar;

import java.util.Calendar;

/*
  Um cliente tem 10 dias para pagar uma fatura após sua data de vencimento
  sem que os juros sejam cobrados.
  Caso essa data caia em um sábado ou domingo, o cliente pode pagar na
  segunda-feira seguinte.
  Crie uma estrutura que receba uma data de vencimento e calcule quantos dias
  ele tem para pagar.
*/

public class Exercicio002 {

  public static void main(String[] args) {
    Calendar duoDate = Calendar.getInstance();

    duoDate.add(Calendar.DATE, 10);

    System.out.println(duoDate.get(Calendar.DAY_OF_WEEK));

    if (duoDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
      System.out.println("FIM DE SEMANA");
      duoDate.add(Calendar.DATE, 2);
    } else if (duoDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
      System.out.println("FIM DE SEMANA");
      duoDate.add(Calendar.DATE, 1);
    }

    System.out.printf("O vencimento da fatura é dia %tD", duoDate);
  }
}
