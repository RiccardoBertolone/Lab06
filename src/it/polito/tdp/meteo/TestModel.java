package it.polito.tdp.meteo;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.db.MeteoDAO;

public class TestModel {

	public static void main(String[] args) {

		Model m = new Model();
		
//		System.out.println(m.getUmiditaMedia(1));
		
//		m.popolaSetCitta(1) ;
//		System.out.println(m.cittaSet);
		
		
		
//		System.out.println(m.trovaSequenza(5));
		
//		System.out.println(m.trovaSequenza(4));
		
		List<Citta> c = new LinkedList<Citta>() ;
		MeteoDAO dao = new MeteoDAO() ;
		Citta torino = new Citta("Torino", dao.getAllRilevamentiLocalitaMese(1, "Torino")) ;
		Citta milano = new Citta("Milano", dao.getAllRilevamentiLocalitaMese(1, "Milano")) ;
		Citta genova = new Citta("Genova", dao.getAllRilevamentiLocalitaMese(1, "Genova")) ;

		c.add(torino) ;
		c.add(torino) ;
		c.add(milano) ;
		c.add(genova) ;
		
		System.out.println(m.punteggioSoluzione(c)) ;
		
	}

}
