package javaLocalDate;

import java.time.LocalDateTime;

/* MANIPULAÇÃO DO LocalDateTime */

public class Exemplo014 {
    public static void main(String[] args) {

        LocalDateTime agora = LocalDateTime.now();

        System.out.println(agora);
        // 2021-07-23T18:25:47.877702086

        LocalDateTime futuro = agora.plusHours(1).plusDays(2).plusSeconds(12);

        System.out.println(futuro);
        // 2021-07-23T18:25:47.877702086

    }
}
