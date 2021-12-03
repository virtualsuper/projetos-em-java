<p align="center">
  <img src="../.github/exceptions.png" width=300 alt="Tratamento de exceções" /> 
</p>

<p align="center">
  <img src="https://img.shields.io/badge/author-Nadia%20Ligia-FF7600?style=plastic">&nbsp;

  <a href="https://www.linkedin.com/in/nlnadialigia/">
  <img alt="Linkedin" src="https://img.shields.io/badge/-Linkedin -FF7600?style=plastic&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/nlnadialigia/" />
  </a>&nbsp;
  <a href="mailto:nlnadialigia@gmail.com">
    <img alt="Email" src="https://img.shields.io/badge/-Email-FF7600?style=plastic&logo=Gmail&logoColor=white&link=mailto:nlnadialigia@gmail.com" />
  </a>&nbsp;
</p>

<br>

# Índice

- [Aprenda o funcionamento de Exceções em Java](#📌-aprenda-o-funcionamento-de-exceções-em-java)
- [Exceções esperadas e não esperadas](#📌-exceções-esperadas-e-não-esperadas)
- [Conheça finally e throw](#📌-conheça-finally-e-throw)

<br>

## 📌 Aprenda o funcionamento de Exceções em Java

### 📎 Exceptions

> Exceções são todos os erros que ocorrem durante o processamento de um método e podem ser esperados ou não esperados. Como o próprio nome já diz "Exceptions" são as possíveis exceções. São falhas que, idealmente, não devem ocorrer no fluxo do sistema.

- No exemplo abaixo temos um método para a criação de um arquivo dentro de um bloco **`try/catch`** que, no caso de não conseguir executar a função **`java.io.FileInputStream()`** lança uma exceção do tipo **`java.io.FileNotFoundException`** com a mensagem "Não foi possível abrir o aquivo para consulta":

  ```java
  public static void method(String[] args) {
    try {
       new java.io.FileInputStream("arquivo.txt");
    }catch (java.io.FileNotFoundException e) {
       System.out.println("Não foi possível abrir o aquivo para consulta");
    }
  }

  ```

- Fluxo de como está encadeado o objeto

  ![Fluxo](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2dfa9d36-ea3b-46f8-afe5-877a02c6ec4b/Captura_de_tela_de_2021-07-24_15-50-29.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210724%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210724T192105Z&X-Amz-Expires=86400&X-Amz-Signature=4c0d1ae71d1c2350547da848ad4253608d2d2bce1c390f5c84f7ee673f0b6511&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Captura_de_tela_de_2021-07-24_15-50-29.png%22)

## 📌 Exceções esperadas e não esperadas

### 📎 Checked Exceptions:

> Checked exception ou exceções esperadas são aquelas cujo fluxo ou método de um sistema foi preparado para receber. Um bom exemplo é uma exceção de negócio, ou se deseja informar um erro caso a exceção esperada ocorra.

- No exemplo abaixo o meu bloco **`try/catch`** está lançando uma exceção customizada que é a **`AcessoADadosException`** para o caso da minha `query` não ser executada com sucesso.

  ```java
  try{
    PreparedStatement stmt = con.prepareStatement(query);
    //...
  }catch (SQLException e) {
    throw new AcessoADadosException("Problema na criação do Statement", e)
  }

  ```

### 📎 Unchecked Exceptions:

> As unchecked exceptions não são esperadas para o fluxo ou método de um sistema, um bom exemplo é a famosa **`NullPointException`** que ocorre quando se tenta acessar uma referencia de memória que está vazia ou recuperar uma instancia que não existe.

- No exemplo a baixo, o bloco **`try/catch`** cria um objeto carro e acessa o método `getPlaca()`, mas como não foi atribuído nenhum valor à propriedade placa teremos um `NullPointException` que é uma unchecked exception.

  ```java
  try{
    Carro carro = new Carro();
    carro.getPlaca();
  }catch (IntegrationException e) {
    throw new BusinessException("Erro na criação do objeto carro", e);
  }

  ```

### 📎 Bloco Try catch

> O bloco `try/catch` é utilizado sempre quando no processo que será executado dentro de um método é esperado um erro, para isso cria-se um bloco "protegido" onde qualquer erro que ocorra dentro do trecho **`try`** é direcionado para o trecho **`catch`** e sofrerá o devido tratamento de erro.

```java
try{
  PreparedStatement stmt = con.prepareStatement(query);
  //...
}catch (SQLException e) {
  throw new AcessoADadosException("Problema na criação do Statement", e)
}

```

## 📌 Conheça finally e throw

### 📎 Finally

> O bloco finally é um bloco de codigo que **pode ou não ser utilizado** junto ao `try/catch`, este trecho de código sempre será executado independente se ocorrer erro ou não dentro do fluxo onde existe o `try/catch`. Normalmente o finally é usado para liberar recursos ou para dar continuidade em um fluxo que deve ocorrer independente de eventuais erros.

Exemplo:

```java
try{
  PreparedStatement stmt = con.prepareStatement(query);
  //...
}catch (SQLException e) {
  throw new AcessoADadosException("Problema na criação do Statement", e);
} finally {
 stmt.close();
}

```

### 📎 Throws

> É a assinatura do método que será retornado caso ocorra erro para o método que fez a chamada, dentro de um fluxo encadeado.

### 📎 Throw

> É usado para lançar a execução desejada, juntamente com a mensagem de erro, para o método que fez a chamada.

Exemplo:

```java
public String recuperaIdUsuaruio(String query) throws AcessoADadosException {
	try {
	  PreparedStatement stmt = con.prepareStatement(query);
	  //...
	} catch (SQLException e) {
	  throw new AcessoADadosException("Problema na criação do Statement", e);
	} finally {
	 stmt.close();
	}
}

```
