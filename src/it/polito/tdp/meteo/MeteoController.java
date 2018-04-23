package it.polito.tdp.meteo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;

public class MeteoController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<Integer> boxMese;

	@FXML
	private Button btnCalcola;

	@FXML
	private Button btnUmidita;

	@FXML
	private TextArea txtResult;
	
	private Model model ;

	@FXML
	void doCalcolaSequenza(ActionEvent event) {
		txtResult.clear() ;
		try {
			Integer i = boxMese.getValue() ;
			String result = "Sequenza ottimale:\n";
			result+=model.trovaSequenza(i) ;
			txtResult.setText(result);
		}
		catch (Exception e) {
			txtResult.setText("Devi selezionare un mese");
		}		
	}

	@FXML
	void doCalcolaUmidita(ActionEvent event) {
		txtResult.clear() ;
		try {
			Integer i = boxMese.getValue() ;
			model.getUmiditaMedia(i) ;
			txtResult.setText(model.getUmiditaMedia(i));
		}
		catch (Exception e) {
			txtResult.setText("Devi selezionare un mese");
		}		
	}

	@FXML
	void initialize() {
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
	}
	
	public void setModel(Model m) {
		List<Integer> mesi = new ArrayList<Integer>() ;
		for (int i = 1 ; i<=12; i++) {
			Integer j = i ;
			mesi.add(j) ;
		}
		boxMese.getItems().addAll(mesi) ;
		
		model = new Model() ;
	}

}
