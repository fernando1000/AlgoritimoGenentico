package br.com.x10d.genetico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

public class Executar {
	
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
    	
        List<Produto> listaProdutos = new ArrayList<>();
        
        /*
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/produtos", "root", "123456");
        Statement consulta = con.createStatement();
        ResultSet rs = consulta.executeQuery("select nome, valor, espaco, quantidade from produtos");
        while (rs.next()) {
            for (int i = 0; i < rs.getInt("quantidade"); i++) {
                //System.out.println("Nome: " + rs.getString("nome"));
                listaProdutos.add(new Produto(rs.getString("nome"), 
                    rs.getDouble("espaco"), rs.getDouble("valor")));
            }           
        }
         */
        
        listaProdutos.add(new Produto("Geladeira Dako", 0.751, 999.90));
        listaProdutos.add(new Produto("Iphone 6", 0.000089, 2911.12));
        listaProdutos.add(new Produto("TV 55' ", 0.400, 4346.99));
        listaProdutos.add(new Produto("TV 50' ", 0.290, 3999.90));
        listaProdutos.add(new Produto("TV 42' ", 0.200, 2999.00));
        listaProdutos.add(new Produto("Notebook Dell", 0.00350, 2499.90));
        listaProdutos.add(new Produto("Ventilador Panasonic", 0.496, 199.90));
        listaProdutos.add(new Produto("Microondas Electrolux", 0.0424, 308.66));
        listaProdutos.add(new Produto("Microondas LG", 0.0544, 429.90));
        listaProdutos.add(new Produto("Microondas Panasonic", 0.0319, 299.29));
        listaProdutos.add(new Produto("Geladeira Brastemp", 0.635, 849.00));
        listaProdutos.add(new Produto("Geladeira Consul", 0.870, 1199.89));
        listaProdutos.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        listaProdutos.add(new Produto("Notebook Asus", 0.527, 3999.00));
        
        List espacos = new ArrayList<>();
        List valores = new ArrayList<>();
        List nomes = new ArrayList<>();
        for (Produto produto: listaProdutos) {
            espacos.add(produto.getEspaco());
            valores.add(produto.getValor());
            nomes.add(produto.getNome());
        }
        Double limite = 10.0;
        int tamanhoPopulacao = 20;
        Double taxaMutacao = 0.05;
        int numeroGeracoes = 60;
        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanhoPopulacao);
        List resultado = ag.resolver(taxaMutacao, numeroGeracoes, espacos, valores, limite);
        for (int i = 0; i < listaProdutos.size(); i++) {
            if (resultado.get(i).equals("1")) {
                System.out.println("Nome: " + listaProdutos.get(i).getNome());
            }
        }
        
        Grafico g = new Grafico("Algoritmo genetico", "Evolucao das solucoes", ag.getMelhoresCromossomos());
        g.pack();
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);
    }
}
