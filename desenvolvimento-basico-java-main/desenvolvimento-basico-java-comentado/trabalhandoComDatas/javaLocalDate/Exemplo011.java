package javaLocalDate;

import java.time.LocalDate;

/* MANIPULAÇÃO DO LocalDate */

public class Exemplo011 {
    public static void main(String[] args) {

        LocalDate hoje = LocalDate.now();

        LocalDate ontem = hoje.minusDays(1);

        System.out.println(hoje);
        // 2021-07-23

        System.out.println(ontem);
        // 2021-07-22

    }
}
