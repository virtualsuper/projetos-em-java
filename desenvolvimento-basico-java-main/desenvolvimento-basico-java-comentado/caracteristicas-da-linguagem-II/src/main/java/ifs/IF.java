package ifs;

public class IF {
    public static void main(String[] args) {

        final var status = false;

        if (status) {
            System.out.println("A condição é verdadeira");
        } else {
            System.out.println("A condição é falsa");
        }

        if (status) {
            System.out.println("Uma única linha...");
        }

        // IF TERNÁRIO
        final var ternario = status ? "é verdadeira" : "é falsa";

        System.out.println(ternario);
    }
}
