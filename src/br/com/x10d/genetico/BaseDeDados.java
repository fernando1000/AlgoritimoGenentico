package br.com.x10d.genetico;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDados {
	
	 public static List<Produto> pegaListaComProdutos() {
	    	
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
	        
	        return listaProdutos;
	    }

}
