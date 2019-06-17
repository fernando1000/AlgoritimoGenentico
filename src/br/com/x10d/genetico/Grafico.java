package br.com.x10d.genetico;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Grafico extends ApplicationFrame {

	private List<Individuo> melhoresCromossomos = new ArrayList<>();

	public Grafico(String tituloJanela, String tituloGrafico, List melhores) {
		super(tituloJanela);
		this.melhoresCromossomos = melhores;
		JFreeChart graficoLinha = ChartFactory.createLineChart(tituloGrafico, "Geracao", "Valor", carregarDados(),
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel janelaGrafico = new ChartPanel(graficoLinha);
		janelaGrafico.setPreferredSize(new java.awt.Dimension(800, 600));
		setContentPane(janelaGrafico);
	}

	private DefaultCategoryDataset carregarDados() {
		DefaultCategoryDataset dados = new DefaultCategoryDataset();
		for (int i = 0; i < melhoresCromossomos.size(); i++) {
			dados.addValue(melhoresCromossomos.get(i).getNotaAvaliacao(), "Melhor solucao", "" + i);
		}
		return dados;
	}
}
