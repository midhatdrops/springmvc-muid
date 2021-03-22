package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.models.Pedido;
import br.com.alura.mvc.mudi.models.StatusPedido;

public class RequisicaoPedido {

  @NotBlank
  private String productName;
  @NotBlank
  private String productURL;
  @NotBlank
  private String productImageURL;
  private String productDescription;

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductURL() {
    return this.productURL;
  }

  public void setProductURL(String productURL) {
    this.productURL = productURL;
  }

  public String getProductImageURL() {
    return this.productImageURL;
  }

  public void setProductImageURL(String productImageURL) {
    this.productImageURL = productImageURL;
  }

  public String getProductDescription() {
    return this.productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public Pedido toPedido() {
    Pedido pedido = new Pedido();
    pedido.setDescricao(productDescription);
    pedido.setNomeProduto(productName);
    pedido.setUrlImagem(productImageURL);
    pedido.setUrlProduto(productURL);
    pedido.setStatus(StatusPedido.AGUARDANDO);
    return pedido;
  }

}
