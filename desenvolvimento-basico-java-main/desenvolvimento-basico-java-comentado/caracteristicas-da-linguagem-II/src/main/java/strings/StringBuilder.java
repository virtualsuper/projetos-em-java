package strings;

public class StringBuilder {
    public static void main(String[] args) {
        var name = "Nadia";

        final var builder = new java.lang.StringBuilder(name);
        System.out.println(builder.append(" Ligia"));

        final var reverse = builder.reverse();

        System.out.println(reverse);

        final var insert = reverse.insert(0, "#").insert(reverse.length(), "#");
        System.out.println(insert);
    }
}
