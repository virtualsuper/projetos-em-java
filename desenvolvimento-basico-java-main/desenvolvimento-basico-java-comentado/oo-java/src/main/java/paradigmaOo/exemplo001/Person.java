package paradigmaOo.exemplo001;

public class Person {
  final private String name = "Nadia Ligia";

  public String getName() {
    return name;
  }

  public String sayMyName() {
    return "Olá, meu nome é " + name;
  }

  public String walk() {
    return "Walking...";
  }
}
