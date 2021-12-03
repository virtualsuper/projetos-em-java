package operators;

public class Relational {
    public static void main(String[] args) {

        final var number = 23;

        if (number > 20) {
            System.out.println("O número é maior que 20");
        } else if (number >= 10) {
            System.out.println("O número é maior ou igual a 10");
        } else if (number <= 5) {
            System.out.println("O número é menor ou igual que 5");
        } else {
            System.out.println("nenhuma da anteriores");
        }
    }
}
