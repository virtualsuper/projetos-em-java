<p align="center">
  <img src="../.github/arrays.png" width=300 alt="Trabalhando com Arrays" /> 
</p>

<p align="center">
  <img src="https://img.shields.io/badge/author-Nadia%20Ligia-890596?style=plastic">&nbsp;

  <a href="https://www.linkedin.com/in/nlnadialigia/">
  <img alt="Linkedin" src="https://img.shields.io/badge/-Linkedin -890596?style=plastic&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/nlnadialigia/" />
  </a>&nbsp;
  <a href="mailto:nlnadialigia@gmail.com">
    <img alt="Email" src="https://img.shields.io/badge/-Email-890596?style=plastic&logo=Gmail&logoColor=white&link=mailto:nlnadialigia@gmail.com" />
  </a>&nbsp;
  <img alt="GitHub last commit (branch)" src="https://img.shields.io/github/last-commit/nlnadialigia/desenvolvimento-basico-java/arrays?color=890596&style=plastic">
</p>

<br>

# Índice

- [O que é um array?](#📌-o-que-é-um-array)
- [Declaração de arrays](#📌-declaração-de-arrays)
- [Comprimento do array](#📌-comprimento-do-array)
- [Percorrendo arrays](#📌-percorrendo-arrays)
- [Arrays multidimensionais](#📌-arrays-multidimensionais)

<br>

## 📌 O que é um array?

- Estrutura que recebe vários elementos
- Cada item em um array é chamado de elemento
- Cada elemento é acessado pela posição numérica (índice)
- O índice inicia a partir de 0.
- Ao se declarar um array, todos índices são inicializados em 0.
- Arrays não podem mudar de tamanho, a partir do momento que uma array foi criada, ela não pode mudar de tamanho.
- Se você precisar de mais espaço, será necessário criar uma nova array e, antes de se referir ela, copie os elementos da array velho.

## 📌 Declaração de arrays

- Utilizando o operador **`new`**

  ```java
  data Type[] arrayName = new dataType[arraySize];
  int meuArray = new int[7];
  ```

- Outra forma

  ```java
  data Type[] arrayName = {value0, value1, ..., valueN}
  int[] meuArray = {12,32,54,6,8,89,64};
  ```

- Para alterar o valor de um elemento específico, coloque o índice desse elemento

  ```java
  meuArray[0] = 50
  ```

## 📌 Comprimento do array

- Para descobrir quantos elementos um array possui, use a propriedade `**length**`

  ```java
  System.out.println(meuArray.length);
  ```

## 📌 Percorrendo arrays

- Para processar um array, devemos usar um laço de repetição (`for`, `forEach`, etc)

  ```java
  public class ScrollArray {
    public static void main(String[] args) {
      int[] myArray = {12, 32, 54, 6, 8, 89, 64};
      for (int i = 0; i< 7; i++) {
        System.out.println(myArray[i]);
      }
    }
  }
  ```

## 📌 Arrays multidimensionais

- é um array contendo um ou mais arrays internos.

  ```java
  int[][] myArrayMulti = {{1,2}, {4,5,6}}
  ```

- Processando um array multidimensional

  ```java
  public static void main(String[] args) {
      int[][] myArrayMulti = {{1, 2, 3, 4}, {5, 6, 7}};
      myArrayMulti = new int[][]{{1, 2, 3, 4}, {53, 63, 73}};

      for (int[] ints : myArrayMulti) {
        for (int anInt : ints) {
          System.out.println(anInt);
        }
      }
    }
  ```
