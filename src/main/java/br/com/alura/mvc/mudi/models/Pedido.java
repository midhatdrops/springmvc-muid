package br.com.alura.mvc.mudi.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nomeProduto;
  private BigDecimal valor;
  private LocalDate dataEntrega;
  private String urlProduto;
  private String descricao;
  private String urlImagem;

  @Enumerated(EnumType.STRING)
  private StatusPedido status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;

  @Deprecated
  public Pedido() {
    // default
  }

  public Pedido(String nomeProduto, BigDecimal valor, LocalDate dataEntrega, String urlProduto, String descricao,
      String urlImagem) {
    this.nomeProduto = nomeProduto;
    this.valor = valor;
    this.dataEntrega = dataEntrega;
    this.urlProduto = urlProduto;
    this.descricao = descricao;
    this.urlImagem = urlImagem;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public StatusPedido getStatus() {
    return status;
  }

  public void setStatus(StatusPedido status) {
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeProduto() {
    return this.nomeProduto;
  }

  public void setNomeProduto(String nomeProduto) {
    this.nomeProduto = nomeProduto;
  }

  public BigDecimal getValor() {
    return this.valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public LocalDate getDataEntrega() {
    return this.dataEntrega;
  }

  public void setDataEntrega(LocalDate dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public String getUrlProduto() {
    return this.urlProduto;
  }

  public void setUrlProduto(String urlProduto) {
    this.urlProduto = urlProduto;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getUrlImagem() {
    return this.urlImagem;
  }

  public void setUrlImagem(String urlImagem) {
    this.urlImagem = urlImagem;
  }

}
