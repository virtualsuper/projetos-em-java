package javaDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/* Converta a Data atual no formato DD/MM/YYYY HH:MM:SS:MMM */

public class Exercise003 {

  public static void main(String[] args) {

    Date newDate = new Date();

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");

    System.out.println(formatter.format(newDate));

  }
}
