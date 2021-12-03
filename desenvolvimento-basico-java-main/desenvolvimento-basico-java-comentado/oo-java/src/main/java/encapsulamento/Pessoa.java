package encapsulamento;

import java.time.LocalDate;
import java.time.Period;


public class Pessoa {

  private String name;
  final private LocalDate birthDate;

  public Pessoa(String name, int day, int month, int year) {
    this.name = name;
    this.birthDate = LocalDate.of(year, month, day);
  }

  public int calculateAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }

  public String getName() {
    return name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setName(String name) {
    this.name = name;
  }
}
