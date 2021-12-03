package strings;


import java.util.Arrays;

public class Exercise {
  public static void main(String[] args) {

    var frase = "Aula de Java";
    var arrayFrase = frase.split(" ");

    var curso = "Desenvolvimento-básico-de-Java";
    var arrayCurso = curso.split("-");

    var palavra = "EXEMPLO";
    var palavraToCharArray = palavra.toCharArray();
    var parteDaPalavra = palavra.toCharArray()[2];
    System.out.println("FIM");
//    System.out.println(palavra[2]);


  }
}
