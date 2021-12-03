package encapsulamento;

public class NewPerson {

  public static void main(String[] args) {

    Pessoa me = new Pessoa("Nadia", 14, 10, 1977);

    System.out.println(me.getName());
    System.out.println(me.getBirthDate());
    System.out.println(me.calculateAge());

    me.setName("Nadia Ligia");

    System.out.println(me.getName());
  }



}
