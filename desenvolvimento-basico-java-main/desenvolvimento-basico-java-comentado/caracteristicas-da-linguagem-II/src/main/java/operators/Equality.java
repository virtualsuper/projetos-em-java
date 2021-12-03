package operators;

public class Equality {
    public static void main(String[] args) {
        final var number = 11;

        if (number == 10) {
            System.out.println("O número é 10");
        } else {
            System.out.println("O número não é 10");
        }

        if (number != 10) {
            System.out.println("O número não é 10");
        } else {
            System.out.println("O número é 10");
        }

        final var letter = "B";

        if ("A".equals(letter)) {
            System.out.println("É a letra A");
        }

        if (!letter.equals("A")) {
            System.out.println("Não é a letra A");
        }
    }
}
