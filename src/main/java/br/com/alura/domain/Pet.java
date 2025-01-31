package br.com.alura.domain;

public class Pet {

  private Long id;
  private String tipo;
  private String nome;
  private String raca;
  private int idade;
  private String cor;
  private double peso;

  public Pet() {}

  public Pet(String tipo, String nome, String raca, int idade, String cor, double peso) {
    this.tipo = tipo;
    this.nome = nome;
    this.raca = raca;
    this.idade = idade;
    this.cor = cor;
    this.peso = peso;
  }

  public Long getId() {
    return id;
  }

  public String getTipo() {
    return tipo;
  }

  public String getNome() {
    return nome;
  }

  public String getRaca() {
    return raca;
  }

  public int getIdade() {
    return idade;
  }

  public String getCor() {
    return cor;
  }

  public double getPeso() {
    return peso;
  }

}
