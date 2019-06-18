package br.com.x10d.genetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class AlgoritmoGenetico {

	private int tamanhoPopulacao;
	private List<Individuo> populacao = new ArrayList<>();
	private int geracao;
	private Individuo melhorSolucao;
	private List<Individuo> melhoresCromossomos = new ArrayList<>();

	public AlgoritmoGenetico(int tamanhoPopulacao) {
		this.tamanhoPopulacao = tamanhoPopulacao;
	}

	public void inicializaPopulacao(List<Double> espacos, List<Double> valores, Double limiteEspacos) {
		for (int i = 0; i < this.tamanhoPopulacao; i++) {
			this.populacao.add(new Individuo(espacos, valores, limiteEspacos));
		}
		this.melhorSolucao = this.populacao.get(0);
	}

	public void ordenaPopulacao() {
		Collections.sort(this.populacao);
	}

	public void melhorIndividuo(Individuo individuo) {
		if (individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {
			this.melhorSolucao = individuo;
		}
	}

	public Double somaAvaliacoes() {
		Double soma = 0.0;
		for (Individuo individuo : this.populacao) {
			soma += individuo.getNotaAvaliacao();
		}
		return soma;
	}

	public int selecionaPai(Double somaAvaliacao) {
		int pai = -1;
		Double valorSorteado = Math.random() * somaAvaliacao;
		Double soma = 0.0;
		int i = 0;
		while (i < this.populacao.size() && soma < valorSorteado) {
			soma += this.populacao.get(i).getNotaAvaliacao();
			pai += 1;
			i += 1;
		}
		return pai;
	}

	public void visualizaGeracao() {
		Individuo melhor = this.populacao.get(0);
		this.melhoresCromossomos.add(melhor);
		System.out.println("G: " + melhor.getGeracao() + " Valor: " + melhor.getNotaAvaliacao() + " Espaco usado: "
				+ melhor.getEspacoUsado() + " Cromossomo: " + melhor.getCromossomo());
	}

	public List<String> efetuaAnalise(Double taxaMutacao, int numeroGeracoes, List<Double> espacos, List<Double> valores, Double limiteEspacos) {

		this.inicializaPopulacao(espacos, valores, limiteEspacos);
		for (Individuo individuo : this.populacao) {
			individuo.avaliacao();
		}
		this.ordenaPopulacao();
		this.visualizaGeracao();

		for (int geracao = 0; geracao < numeroGeracoes; geracao++) {
			Double somaAvaliacao = this.somaAvaliacoes();
			List<Individuo> novaPopulacao = new ArrayList<>();

			for (int i = 0; i < this.populacao.size() / 2; i++) {
				int pai1 = this.selecionaPai(somaAvaliacao);
				int pai2 = this.selecionaPai(somaAvaliacao);

				List<Individuo> filhos = this.getPopulacao().get(pai1).crossover(this.getPopulacao().get(pai2));
				novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
				novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));
			}

			this.setPopulacao(novaPopulacao);
			for (Individuo individuo : this.getPopulacao()) {
				individuo.avaliacao();
			}
			this.ordenaPopulacao();
			this.visualizaGeracao();
			Individuo melhor = this.populacao.get(0);
			this.melhorIndividuo(melhor);

		}
		System.out.println("Melhor solucao G -> " + this.melhorSolucao.getGeracao() + " Valor: "
				+ this.melhorSolucao.getNotaAvaliacao() + " Espaco: " + this.melhorSolucao.getEspacoUsado()
				+ " Cromossomo: " + this.melhorSolucao.getCromossomo());
		return this.melhorSolucao.getCromossomo();
	}
	
}
