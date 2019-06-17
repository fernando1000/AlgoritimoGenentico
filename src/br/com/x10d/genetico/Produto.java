package br.com.x10d.genetico;

import lombok.Data;

@Data
public class Produto {

	private String nome;
	private Double espaco;
	private Double valor;

	public Produto(String nome, Double espaco, Double valor) {
		this.nome = nome;
		this.espaco = espaco;
		this.valor = valor;
	}
	
}
