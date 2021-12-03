package DIP.problem;

import DIP.problem.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

  private MySqlConnection mySqlConnection;

  public ProdutoRepository(MySqlConnection mySqlConnection) {
    this.mySqlConnection = mySqlConnection;
  }

  public List<Produto> buscarProdutos() {
    // retorna a lista de produtos da base de dados
    return new ArrayList<>();
  }

  public void salvarProduto(Produto produto) {
    // salva a lista de produtos na base de dados
  }
}
