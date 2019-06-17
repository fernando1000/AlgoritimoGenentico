package br.com.x10d.genetico;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Individuo implements Comparable<Individuo> {

	private List espacos = new ArrayList<>();
	private List valores = new ArrayList<>();
	private Double limiteEspacos;
	private Double notaAvaliacao;
	private Double espacoUsado;
	private int geracao;
	private List cromossomo = new ArrayList<>();

	public Individuo(List espacos, List valores, Double limiteEspacos) {
		this.espacos = espacos;
		this.valores = valores;
		this.limiteEspacos = limiteEspacos;
		this.notaAvaliacao = 0.0;
		this.espacoUsado = 0.0;
		this.geracao = 0;

		for (int i = 0; i < this.espacos.size(); i++) {
			if (java.lang.Math.random() < 0.5) {
				this.cromossomo.add("0");
			} else {
				this.cromossomo.add("1");
			}
		}
	}

	public void avaliacao() {
		Double nota = 0.0;
		Double somaEspacos = 0.0;
		for (int i = 0; i < this.cromossomo.size(); i++) {
			if (this.cromossomo.get(i).equals("1")) {
				nota += (Double) this.valores.get(i);
				somaEspacos += (Double) this.espacos.get(i);
			}
		}

		if (somaEspacos > this.limiteEspacos) {
			nota = 1.0;
		}

		this.notaAvaliacao = nota;
		this.espacoUsado = somaEspacos;
	}

	public List crossover(Individuo outroIndividuo) {
		int corte = (int) Math.round(Math.random() * this.cromossomo.size());
		List filho1 = new ArrayList<>();
		filho1.addAll(outroIndividuo.getCromossomo().subList(0, corte));
		filho1.addAll(this.cromossomo.subList(corte, this.cromossomo.size()));

		List filho2 = new ArrayList<>();
		filho2.addAll(this.cromossomo.subList(0, corte));
		filho2.addAll(outroIndividuo.getCromossomo().subList(corte, this.cromossomo.size()));

		List<Individuo> filhos = new ArrayList<>();
		filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspacos));
		filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspacos));

		filhos.get(0).setCromossomo(filho1);
		filhos.get(0).setGeracao(this.geracao + 1);
		filhos.get(1).setCromossomo(filho2);
		filhos.get(1).setGeracao(this.geracao + 1);

		return filhos;
	}

	public Individuo mutacao(Double taxaMutacao) {
		// System.out.println("Antes da mutação: " + this.cromossomo);
		for (int i = 0; i < this.cromossomo.size(); i++) {
			if (Math.random() < taxaMutacao) {
				if (this.cromossomo.get(i).equals(("1"))) {
					this.cromossomo.set(i, "0");
				} else {
					this.cromossomo.set(i, "1");
				}
			}
		}
		// System.out.println("Depois da mutação: " + this.cromossomo);
		return this;
	}
	
	@Override
	public int compareTo(Individuo o) {
		if (this.notaAvaliacao > o.getNotaAvaliacao()) {
			return -1;
		}
		if (this.notaAvaliacao < o.getNotaAvaliacao()) {
			return 1;
		}
		return 0;
	}
}
