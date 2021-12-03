package operators;

public class Increment {
    public static void main(String[] args) {
        var number = 1;

        // Incrementa antes de imprimir o número
        System.out.println(++number);

        // Incrementa depois de imprimir o número
        System.out.println(number++);

        var number2 = 10;

        System.out.println(number2--);
        System.out.println(number2);
    }
}
