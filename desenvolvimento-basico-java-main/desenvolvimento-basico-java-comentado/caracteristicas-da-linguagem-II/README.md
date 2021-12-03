<p align="center">
  <img src="../.github/language-feature-II.png" alt="Características da Linguagem II" /> 
  
</p>

<p align="center">
  <img src="https://img.shields.io/badge/author-Nadia%20Ligia-ff7600?style=plastic">&nbsp;

  <a href="https://www.linkedin.com/in/nlnadialigia/">
  <img alt="Linkedin" src="https://img.shields.io/badge/-Linkedin -ff7600?style=plastic&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/nlnadialigia/" />
  </a>&nbsp;
  <a href="mailto:nlnadialigia@gmail.com">
    <img alt="Email" src="https://img.shields.io/badge/-Email-ff7600?style=plastic&logo=Gmail&logoColor=white&link=mailto:nlnadialigia@gmail.com" />
  </a>&nbsp;
  <img alt="GitHub last commit (branch)" src="https://img.shields.io/github/last-commit/nlnadialigia/desenvolvimento-basico-java/language-feature-II?color=ff7600&style=plastic">
</p>

<br>

# Índice

- [Strings e o pacote java.lang](#📌-strings-e-o-pacote-javalang)
- [Introdução a condicionais](#📌-introdução-a-condicionais)
- [Laços de repetição](#📌-laços-de-repetição)
- [Convenções de nomes](#📌-convenções-de-nomes)
- [Plugins](#📌-plugins)

<br>

## 📌 Strings e o pacote javalang

- é uma sequência de caracteres
- Pacote **java.lang**
- **Métodos**
  - `charAt(n)` ⇒ devolve o valor do caractere na posição `n`
  - `length()` ⇒ mostra a quantidade de caracteres da string
  - `trim()` ⇒ responsável por remover os caracteres em branco nas extremidades da string
  - `toLowerCase()` ⇒ tudo em minúsculos
  - `toUperCase()` ⇒ tudo maiúsculo
  - `contains("n")` ⇒ verifica se contém ou não o caractere `n`
  - `replace("x", "y")` ⇒ trocar caracteres
  - `equals("n")` ⇒ verifica se o `n` é igual a stri
  - `equalsIgnoreCase("n")` ⇒ ignora uperCase e lowerCase
  - `substring(x, y)` ⇒ pegar a string da posição `x` até a posição `y`
- **StringFormat**
- **BuilderDeString**

## 📌 Introdução a condicionais

- **If e If Ternário**
- **Operadores**
  - Igualdade
  - Lógicos
  - Incremento
    - `++numero` ⇒ incrementa primeiro e depois imprime o número
    - `numero++` ⇒ imprime o número e depois incrementa
    - a mesma lógica vale para `--`
  - Matemáticos
  - Relacionais

## 📌 Laços de repetição

- **For**

  - forma de fazermos interações
  - se form uma única linha não precisa ter a chave

- **While**

  - testa a condição antes de efetuar o código

  ```java
  while (x < 1) {
    System.out.println("Dentro do while...");
    x++;
  }
  ```

- **Do/While**

  - testa condição depois de efetuar o código

  ```java
  do {
    System.out.println("Dentro do do/while...");
  } while
  ```

## 📌 Convenções de nomes

### Nomes de Classes

- primeira letra maiúscula
- nomes compostos ⇒ camelCase

### Nomes de Métodos

- minúsculos
- nome composto ⇒ camelCase
- não pode colocar caracteres especiais

### Nomes de Variáveis

- sucinto
- não misturar número nos nomes
- minúsculas
- `var` é opcional ⇒ se não colocar tem que mostrar a tipagem

## 📌 Plugins

> As configurações dos plugins foram efetuadas em um branch separada => `code-style`

[Checkstyle](https://checkstyle.org/index.html)

[PMD](https://pmd.github.io/)

- utilização do Estilo de Código Java do Google

- Instalar no projeto

  - criar branch ⇒ **code-style**

  - `build.gradle`

    - adicionar `plugins`
    - fazer configurações ⇒ verificar a última versão disponível

  - criar os arquivos de configuração ⇒ baixar da internet

    - [/config/checkstyle/checkstyle.xml](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)

    - [/config/pmd/ruleset.xml\*\*](https://gist.github.com/nlnadialigia/06ddd02c275dcf2d4315c898e5ed4763)

  - refazer o `build`

- Desabilitar uma validação
  - procurar no xml
  - remover o que não é interessante
- Verificar os problemas
  - ambos geram um `report` dentro da pasta `build`
  - o report do `pmd` mantém um link para explicar o problema
