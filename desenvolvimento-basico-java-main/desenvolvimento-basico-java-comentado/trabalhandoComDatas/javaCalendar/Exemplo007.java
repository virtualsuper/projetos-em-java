package javaCalendar;

import java.util.Calendar;

public class Exemplo007 {
    public static void main(String[] args) {

        Calendar agora = Calendar.getInstance();
        System.out.println(agora.getTime());
        // Fri Jul 23 16:23:59 BRT 2021

        /* CONVERSÕES DE DATAS */

        System.out.printf("%tc\n", agora);
        // sex. jul. 23 16:23:59 BRT 2021

        System.out.printf("%tF\n", agora);
        // 2021-07-23

        System.out.printf("%tD\n", agora);
        // 07/23/21

        System.out.printf("%tr\n", agora);
        // 04:23:59 PM

        System.out.printf("%tT\n", agora);
        // 16:23:59

    }
}