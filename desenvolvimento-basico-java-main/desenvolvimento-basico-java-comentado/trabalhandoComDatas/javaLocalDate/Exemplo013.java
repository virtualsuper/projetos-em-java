package javaLocalDate;

import java.time.LocalTime;

/* MANIPULAÇÃO DO LocalTime */

public class Exemplo013 {
    public static void main(String[] args) {

        LocalTime agora = LocalTime.now();

        System.out.println(agora);
        // 18:25:07.594019313

        LocalTime maisUmaHora = agora.plusHours(1);

        System.out.println(maisUmaHora);
        // 19:25:07.594019313

    }
}
