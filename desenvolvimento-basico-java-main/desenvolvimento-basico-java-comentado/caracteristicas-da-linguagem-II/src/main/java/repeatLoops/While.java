package repeatLoops;

public class While {
    public static void main(String[] args) {

        var x = 0;

        //Testa a condição antes
        while (x < 1) {
            System.out.println(x + " - Dentro do while...");
            x++;
        }

        var y = -2;

        //Testa a condição depois
        do {
            System.out.println(y + " - Dentro do do/while...");
        } while (y++ < 1);
    }
}
