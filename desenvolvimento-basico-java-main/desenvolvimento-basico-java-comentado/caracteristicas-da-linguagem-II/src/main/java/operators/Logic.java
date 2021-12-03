package operators;

public class Logic {
    public static void main(String[] args) {
        final var number = 2;
        final var letter = "A";

        //Sort Circuit
        if (number < 5 && letter.equals("A")) {
            System.out.println("Atendeu a condição");
        }

        if (number < 5 || letter.equals("A")) {
            System.out.println("Atendeu a outracondição");
        }

        if ((10 - 5) > 1 && (5 - 3) > 1) {
            System.out.println("Lógica maluca...");
        }

        //Non Sort Circuit
        /* if (check(15) | check("A")) {
            System.out.println("OK");
        } else {
            System.out.println("Não OK");
        }*/
    }

    private static boolean check(String letter) {
        System.out.println("Verificando letra...");
        return letter.equals("A");
    }

    private static boolean check(Integer number) {
        System.out.println("Verificando número...");
        return number > 10;
    }

}
