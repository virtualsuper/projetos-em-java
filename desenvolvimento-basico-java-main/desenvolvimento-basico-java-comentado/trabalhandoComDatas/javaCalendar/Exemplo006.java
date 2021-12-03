package javaCalendar;

import java.util.Calendar;

public class Exemplo006 {
    public static void main(String[] args) {

        Calendar agora = Calendar.getInstance();

        /* OEPRAÇÕES COM DATAS */

        System.out.println("A data corrente é : " + agora.getTime());
        // A data corrente é : Fri Jul 23 16:17:40 BRT 2021

        agora.add(Calendar.DATE, -15);
        System.out.println("15 dias atrás: " + agora.getTime());
        // 15 dias atrás: Thu Jul 08 16:18:20 BRT 2021

        agora.add(Calendar.MONTH, 4);
        System.out.println("4 meses depois: " + agora.getTime());
        // 4 meses depois: Mon Nov 08 16:18:56 BRT 2021

        agora.add(Calendar.YEAR, 2);
        System.out.println("2 anos depois: " + agora.getTime());
        // 2 anos depois: Wed Nov 08 16:19:21 BRT 2023

    }
}
