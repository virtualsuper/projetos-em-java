package SRP.solution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeCompra {
  private List<SRP.solution.Produto> produtos = new ArrayList<>();

  public void adicionarProduto(SRP.solution.Produto produto) {
    produtos.add(produto);
  }

  public void removerProduto(Produto produto) {
    produtos.remove(produto);
  }

  public BigDecimal calcularTota() {
    return produtos.stream().map(Produto::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
