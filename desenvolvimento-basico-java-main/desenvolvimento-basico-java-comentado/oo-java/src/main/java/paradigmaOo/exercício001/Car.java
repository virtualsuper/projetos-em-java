package paradigmaOo.exercício001;

public class Car {

  private Integer peopleInsideCar = 3;

  public Integer addPeople() {
    int maxPeople = 5;
    if (peopleInsideCar < maxPeople) {
      int addPeople = 1;
      peopleInsideCar += addPeople;
    } else {
      System.out.println("Carro com o número máximo de pessoas!");
    }

    return peopleInsideCar;
  }

  public Integer removePeople() {
    if (peopleInsideCar == 0) {
      System.out.println("Não tem ninguém dentro do carro!");
    } else {
      int removePeople = 1;
      peopleInsideCar -= removePeople;
    }

    return peopleInsideCar;
  }
}
