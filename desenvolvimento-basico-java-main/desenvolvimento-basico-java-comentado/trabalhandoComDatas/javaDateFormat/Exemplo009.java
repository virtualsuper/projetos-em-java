package javaDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exemplo009 {
    public static void main(String[] args) {

        Date agora = new Date();

        /* FORMATAÇÃO DE DATA COM SimpleDateFormat */

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String dataFormatada = formatter.format(agora);

        System.out.println(dataFormatada);
        // 14/07/2019
    }
}
