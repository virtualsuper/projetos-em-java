<p align="center">
  <img src="../.github/date.png" alt="Trabalhando com datas" /> 
</p>

<p align="center">
  <img src="https://img.shields.io/badge/author-Nadia%20Ligia-FFA900?style=plastic">&nbsp;

  <a href="https://www.linkedin.com/in/nlnadialigia/">
  <img alt="Linkedin" src="https://img.shields.io/badge/-Linkedin -FFA900?style=plastic&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/nlnadialigia/" />
  </a>&nbsp;
  <a href="mailto:nlnadialigia@gmail.com">
    <img alt="Email" src="https://img.shields.io/badge/-Email-FFA900?style=plastic&logo=Gmail&logoColor=white&link=mailto:nlnadialigia@gmail.com" />
  </a>&nbsp;
  <img alt="GitHub last commit (branch)" src="https://img.shields.io/github/last-commit/nlnadialigia/desenvolvimento-basico-java/date?color=FFA900&style=plastic">
</p>

<br>

# Índice

- [Objetivos da aula](#📌-objetivos-da-aula)
- [O java.util.Date](#📌-o-date)
  - [Date( )](#📎-date)
  - [Date(long date)](#📎-long-date)
  - [Métodos úteis](#📎-métodos-úteis)
  - [Classe Instant](#📎-classe-instant)
- [O java.util.Calendar](#📌-o-calendar)
- [O java.text.DateFormat](#📌-o-dateformat)
  - [SimpleDateFormat](#📎-simpledateformat)
- [Datas no Java 8+](#📌-datas-no-java-8)
  - [LocalDate](#📎-localdate)
  - [LocalTime](#📎-localtime)
  - [LocalDateTime](#📎-localdatetime)

<br>

## 📌 Objetivos da aula

- Aprender a manipular datas
- Aprender a formatar datas
- Entender a evolução do tratamento de datas no Java

<br>

## 📌 O Date

- Antes de qualquer coisa, vamos definir aqui o ponto que estamos.
- A implementação do java.util.Date está na JDK desde sua versão 1.0
- Ou seja... É de se esperar que algumas coisas não se mostrem tão interessantes nos dias atuais, dado a sua idade.
- Nesse primeiro momento, vamos ver como podemos trabalhar com a manipulação de datas a Classe java.util.Date do Java.
- E o nosso primeiro passo é dar uma olhada na documentação oficial.
- Vamos usar como referência o[ Java 8](https://docs.oracle.com/javase/8/docs/api/java/util/Date.html).
- E para começar a falar sobre o Java Date, vamos falar sobre seus construtores. São eles:

  ```java
  Date( )
  Date (long date)
  ```

### 📎 date

- Este construtor vai alocar um objeto da classe Date e o **inicializará com o milissegundo mais próximo** do período da sua execução.

  ```java
    public static void main(String[] args) {

      Date novaData = new Date();

      System.out.println(novaData);
    }
  ```

### 📎 long date

- Diferente do construtor anterior, esse construtor espera que você passe os milissegundos com base padrão de tempo (epoch) que usa como referência **1 de janeiro de 1970 00:00:00**.

O que é o Epoch ? - "O epoch timestamp é um padrão largamente aceito para representar uma data como um inteiro 32-bits a partir do início do Unix Epoch..."

- Vamos testar como base no **System.currentTimeMillis( )**
- Esse método estático vai nos retornar o milissegundo mais próximo de sua execução com base no Sistema Operacional.

  ```java
    public static void main(String[] args) {

      Long currentTimeMillis = System.currentTimeMillis();

      System.out.println(currentTimeMillis);
      // 1563127311564

      Date novaData = new Date(currentTimeMillis);

      System.out.println(novaData);
      // Sun Jul 14 15:01:51 BRT 2019
    }
  ```

### 📎 Métodos úteis

- Alguns métodos da classe Date são muito úteis e serão usados com frequência durante a manipulação de datas. São eles:

  ![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/bbea24b4-f79f-47ec-adb6-cea26826d88e/Captura_de_tela_de_2021-07-19_14-30-24.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210723%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210723T220408Z&X-Amz-Expires=86400&X-Amz-Signature=5ddcf90fe0fd66a52786e76a80f00b7c8a5342add99524c7948296e165a115b5&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Captura_de_tela_de_2021-07-19_14-30-24.png%22)

- **Exemplos**

  - _After/Before_

    ```java
    public static void main(String[] args) {

      Date dataNoPassado = new Date(1513124807691L);
      //Tue Dec 12 22:26:47 BRST 2017

      Date dataNoFuturo = new Date(1613124807691L);
      //Fri Feb 12 08:13:27 BRST 2021

      /* Comparando se a dataNoPassado é posterior a dataNoFuturo */
      boolean isAfter = dataNoPassado.after(dataNoFuturo);

      System.out.println(isAfter);
      //false

      /* Comparando se a dataNoPassado é anterior a dataNoFuturo */
      boolean isBefore = dataNoPassado.before(dataNoFuturo);

      System.out.println(isBefore);
      //true
    }
    ```

  - _compareTo/equals_

    ```java
    public static void main(String[] args) {

      Date dataNoPassado = new Date(1513124807691L); //Tue Dec 12 22:26:47 BRST 2017

      Date dataNoFuturo = new Date(1613124807691L); //Fri Feb 12 08:13:27 BRST 2021

      Date mesmaDataNoFuturo = new Date(1613124807691L); //Fri Feb 12 08:13:27 BRST 2021

      /* Comparando se as datas são iguais */
      boolean isEquals = dataNoFuturo.equals(mesmaDataNoFuturo);

      System.out.println(isEquals); //true

      /* Comparando uma data com a outra */
      int compareCase1 = dataNoPassado.compareTo(dataNoFuturo); //passado -> futuro

      int compareCase2 = dataNoFuturo.compareTo(dataNoPassado); //futuro -> passado

      int compareCase3 = dataNoFuturo.compareTo(mesmaDataNoFuturo); //datas equivalentes

      System.out.println(compareCase1); // -1

      System.out.println(compareCase2); // 1

      System.out.println(compareCase3); // 0
    }
    ```

### 📎 Classe Instant

- Surgiu na JDK 1.8
- Imutável e Thread safe.
- Modela um ponto instantâneo de uma linha do tempo.
- Indicado para gravar marcações temporais em eventos da sua aplicação.

  ```java
  public static void main(String[] args) {

    Date dataInicio = new Date(1513124807691L);
    System.out.println(dataInicio);
    // Tue Dec 12 22:26:47 BRST 2017

    Instant instant = dataInicio.toInstant();
    System.out.println(instant);
    // 2017-12-13T00:26:47.691Z
  }
  ```

### 📎 Referências

- [https://docs.oracle.com/javase/8/docs/api/java/util/Date.html](https://docs.oracle.com/javase/8/docs/api/java/util/Date.html)
- [https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#currentTimeMillis--](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#currentTimeMillis--)
- [https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html)
- [https://javatpoint.com/java-util-date](https://javatpoint.com/java-util-date)

<br>

## 📌 O Calendar

- Já na JDK 1.1 foi observada a necessidade de facilitar alguns recursos que a class Date oferecia.
- Sendo assim, a classe **Calendar** foi criada.
- Com isso uma série de métodos e construtores da classe Date foi depreciada. Por exemplo o contrutor Date(int year, int month, int date).
- **Calendar** é uma classe abstrata que provê métodos para converter data entre um instante específico.
- O Calendar possui alguns campos específicos para manipulação como MONTH, YEAR, HOUR etc.

### 📎 Referências

- [https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)
- [https://www.javatpoint.com/java-util-calendar](https://www.javatpoint.com/java-util-calendar)
- [https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)

<br>

## 📌 O DateFormat

- Nesse ponto em que estamos existem, basicamente, duas classes para formatação de datas. O **DateFormat** e o **SimpleDateFormat**.
- Ambos oferecem maneiras de formatar e parsear a saída das datas.

  ```java
  public static void main(String[] args) {

    Date agora = new Date();

    /* FORMATAÇÃO DE DATA COM DateFormat */

    String dateToStr = DateFormat.getInstance().format(agora);
    System.out.println(dateToStr);
    // 23/07/21 22:13

    dateToStr = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(agora);
    System.out.println(dateToStr);
    // 15 de Julho de 2019 22:13


    dateToStr = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG).format(agora);
    System.out.println(dateToStr);
    // 23 de Julho de 2021 22h13min55s BRT
  }
  ```

### 📎 SimpleDateFormat

- Traz uma grande facilidade que é definir um padrão de formatação para a saída de data que você deseja.

  ```java
  public static void main(String[] args) {

    Date agora = new Date();

    /* FORMATAÇÃO DE DATA COM SimpleDateFormat */

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    String dataFormatada = formatter.format(agora);

    System.out.println(dataFormatada);
    // 23/07/2021
  }
  ```

### 📎 Referências

- [https://docs.oracle.com/javase/8/docs/api/java/text/DateFormat.html](https://docs.oracle.com/javase/8/docs/api/java/text/DateFormat.html)
- [https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html](https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html)

<br>

## 📌 Datas no Java 8+

- O Java 9 veio com uma série de novidades para facilitar o trabalho com Datas.
- E a grande melhoria está no pacote java.time que foi herdado do projeto Joda Time.
- [https://www.joda.org/joda-time/](https://www.joda.org/joda-time/)
- Trabalhar com datas nunca foi tão fácil com esse novo pacote.
- Nele destacam-se três classes: **LocalDate**, **LocalTime**, **LocalDateTime**
- Basicamente, o que tínhamos até então eram as classes que vimos até agora: Date e Calendar.
- Com o uso constante, elas se mostram confusas e trabalhosas.
- Além de serem mutáveis.

### 📎 LocalDate

- É uma classe imutável para representar uma data.
- Seu formato padrão é **yyyy-MM-dd**

### 📎 LocalTime

- É uma classe imutável que representa um padrão de hora-minuto-segundo
- Pode ser representado até o nível de nanosegundos. **12:22:10:123212345**
- Sua utilização é similar ao **LocalDate**

### 📎 LocalDateTime

- Funciona como uma espécie de junção entre o LocalTime e o LocalDate.
- Também é uma classe imutável e você consegue trabalhar com dia e hora de uma só vez.
- Você pode manipular a data e hora com precisão de nanosegundos.

### 📎 Referências:

- [https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html](https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html)
- [https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)
- [https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html)
