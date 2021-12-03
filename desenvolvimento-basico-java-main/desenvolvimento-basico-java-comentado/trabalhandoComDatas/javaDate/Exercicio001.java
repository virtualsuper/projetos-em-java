package javaDate;

import java.util.Date;

/*
    - Descubra o timeInMillis do dia que você nasceu;
    - Converta em um objeto Date;
    - Verifique se ele é anterior ou posterior a 15 de maio
        de 2010.
*/

public class Exercicio001 {
    public static void main(String[] args) {

        Date birth = new Date(245679510000L);

        System.out.println(birth);

        Date newDate = new Date(1273925910000L);

        System.out.println(newDate);

        boolean before = birth.before(newDate);
        System.out.println("Antes? " + before);

        boolean after = birth.after(newDate);
        System.out.println("Depois? " + after);



    }

}
