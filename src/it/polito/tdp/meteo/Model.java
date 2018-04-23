package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	private int costoMin = Integer.MAX_VALUE;
	private List<Citta> soluzione;

	Set<Citta> cittaSet;

	MeteoDAO dao;

	public Model() {
		dao = new MeteoDAO();
	}

	public String getUmiditaMedia(int mese) {

		String result = "";
		Map<String, Double> umidita = dao.getAvgUmiditaMese(mese);
		for (String s : umidita.keySet()) {
			result += "Umidità media nel periodo 2013-" + mese + " nella città di " + s + ": " + umidita.get(s) + "\n";
		}
		return result.trim();
	}

	public String trovaSequenza(int mese) {

		costoMin = Integer.MAX_VALUE;
		this.popolaSetCitta(mese);

		List<Citta> parziale = new ArrayList<Citta>();

		this.cerca(parziale);

		String result = "";
		int i = 1;
		for (Citta c : soluzione) {
			result += "giorno " + i + ": " + c.getNome() + "\n";
			i++;
		}

		// System.out.println(costoMin);
		return result.trim();
	}

	public void popolaSetCitta(int mese) {
		cittaSet = new HashSet<Citta>();
		List<String> nomiCitta = dao.getCitta();
		for (String s : nomiCitta) {
			Citta c = new Citta(s, dao.getAllRilevamentiLocalitaMese(mese, s));
			cittaSet.add(c);
		}
	}

	private void cerca(List<Citta> parziale) {

		//System.out.println(parziale.size());
		if (parziale.size() == 15) {
			if (this.punteggioSoluzione(parziale) < costoMin && this.visitateTutte()) {
				soluzione = new ArrayList<Citta>(parziale);
				costoMin = this.punteggioSoluzione(parziale);
			}
			return;
		}

		for (Citta c : cittaSet) {
			if (this.piuSeiGiorni(c) == false && this.precedentePiuTreGiorni(parziale, c) == true) {
				parziale.add(c);
				c.setCounter(c.getCounter() + 1);
				cerca(parziale);
				parziale.remove(parziale.size()-1);
				c.setCounter(c.getCounter() - 1);
			}
		}

	}

	private boolean visitateTutte() {
		for (Citta c : cittaSet) {
			if (c.getCounter() == 0)
				return false;
		}

		return true;
	}

	public boolean precedentePiuTreGiorni(List<Citta> parziale, Citta citta) {
		if (parziale.size()==0)
			return true ;
			
		if (citta.equals(parziale.get(parziale.size()-1)))
			return true ;
		else { 
			if (parziale.size()<=2)
				return false ;
			Citta ultima = parziale.get(parziale.size()-1) ;
			if (!ultima.equals(parziale.get(parziale.size()-2)))
				return false ;
			if (!ultima.equals(parziale.get(parziale.size()-3)))
				return false ;
		}
		return true;
	}

	/**
	 * Determina del città c compare almeno 6 volte
	 * @param c
	 * @return
	 */
	public boolean piuSeiGiorni(Citta c) {
		if (c.getCounter()<6)
			return false ;
		return true ;
	}

	public int punteggioSoluzione(List<Citta> soluzioneCandidata) {

		int score = 0;
		int i = 0 ;
		for (Citta c : soluzioneCandidata) {
			score += c.getUmidita(i) ;
			if(i == 0) {
				i++;
				continue ;
			}
			if (!soluzioneCandidata.get(i).equals(soluzioneCandidata.get(i-1)))
				score += COST ;			
			i++ ;
		}
				
		return score;
	}

	public int getCostoMin() {
		return costoMin;
	}
	
	

}
