package it.polito.tdp.meteo.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.bean.Rilevamento;

public class TestMeteoDAO {

	public static void main(String[] args) {

		MeteoDAO dao = new MeteoDAO();

//		List<Rilevamento> list = dao.getAllRilevamenti();

		// STAMPA: localita, giorno, mese, anno, umidita (%)
//		for (Rilevamento r : list) {
//			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
//		}
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(1, "Genova"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(1, "Genova"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Milano"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Milano"));
//		
//		System.out.println(dao.getAllRilevamentiLocalitaMese(5, "Torino"));
//		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, "Torino"));
		
//		Map<String, Double> umiditam = dao.getAvgUmiditaMese(1) ;
//		System.out.println(umiditam);
		
		List<String> nomiCitta = dao.getCitta() ;
		for (String s : nomiCitta) {
			System.out.println(s);
		}
		
//		List<Rilevamento> rilevamenti = dao.getAllRilevamentiLocalitaMese(1, "Genova") ;
//		for (Rilevamento r : rilevamenti)
//			System.out.println(r);
//		
		
	}

}
