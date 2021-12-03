package constructors.exemplo003;

public class InstanciaObjeto {

  public static void main(String[] args) {

    Pessoa person = new Pessoa();
    Pessoa greatPerson = new Pessoa("Maria Gadu");

    person.setName("Nadia Ligia");

    System.out.println(person.getName());

    System.out.println(greatPerson.getName());
  }
}
