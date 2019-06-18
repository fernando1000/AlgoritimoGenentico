package br.com.x10d.genetico;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

public class Executar {
	
	private static final int TAMANHO_POPULACAO = 20;
	private static final Double TAXA_MUTACAO = 0.05;
	private static final int NUMERO_GERACOES = 60;
	private static final Double LIMITE = 10.0;
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
    	
    	List<Produto> listaComProdutos = BaseDeDados.pegaListaComProdutos();
        
        List<Double> listaComEspacos = new ArrayList<>();
        List<Double> listaComValores = new ArrayList<>();
        List<String> listaComNomes = new ArrayList<>();
        
        for (Produto produto: listaComProdutos) {
        	
            listaComEspacos.add(produto.getEspaco());
            listaComValores.add(produto.getValor());
            listaComNomes.add(produto.getNome());
        }
        
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(TAMANHO_POPULACAO);
        
        List<String> listaComResultados = algoritmoGenetico.efetuaAnalise(TAXA_MUTACAO, NUMERO_GERACOES, listaComEspacos, listaComValores, LIMITE);
        
        for (int i = 0; i < listaComProdutos.size(); i++) {
        	
            if (listaComResultados.get(i).equals("1")) {
                System.out.println("Nome: " + listaComProdutos.get(i).getNome());
            }
        }
        
        Grafico grafico = new Grafico("Algoritmo genetico", "Evolucao das solucoes", algoritmoGenetico.getMelhoresCromossomos());
        grafico.pack();
        RefineryUtilities.centerFrameOnScreen(grafico);
        grafico.setVisible(true);
    }
   
}
