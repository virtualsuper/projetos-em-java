package strings;

public class StringFormat {
    public static void main(String[] args) {
        var name = "Nadia";
        var lastName = "Santos";

        final var message = String.format("O cliente %s possui sobrenome %s", name, lastName);

        System.out.println(message);

        System.out.printf("Número %.2f %n", 1.2375d);
    }
}
