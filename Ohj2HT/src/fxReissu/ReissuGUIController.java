package fxReissu;

import java.time.LocalDate;
import java.util.List;

import Luokat.Reissu;
import Luokat.Saalis;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fxKalenteri.KalenteriMain;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author miikk
 * @version 29.1.2025
 * Controller tietyn reissun näyttämisikkunaa varten
 */
public class ReissuGUIController implements ModalControllerInterface<Reissu> {
	private String label;
	private Reissu r;
	
	
	@FXML private Label reissuLabel; 
    
    @FXML private Button jatkaNappi;
   
    @FXML
    private TextField ReissuKeli;

    @FXML
    private TextField ReissuPvm;

    @FXML
    private TextField ReissuSijainti;

    @FXML
    private TextArea ReissunSaalis;



    @Override
    public Reissu getResult() {
    	String pvm = ReissuPvm.getText();
		 
    	 return new Reissu(Reissu.sToDate(pvm),ReissuSijainti.getText(), ReissuKeli.getText(), List.of(), false);
    }

    @Override
    public void setDefault(Reissu reissu) {
    	 if (reissu == null) return;
    
    	 this.r = reissu;
    	 
    	ReissuPvm.appendText(reissu.getPvmString());
    	ReissuKeli.appendText(reissu.getKeli());
    	ReissuSijainti.appendText(reissu.getSijainti());
    	
    	ReissunSaalis.clear();
		for(Saalis s : reissu.getSaaliit()){
			 Platform.runLater(()->{ReissunSaalis.appendText( "Sait " + s.getLaji() + " " + s.getKoko()+ ", vieheellä "  +
		s.getViehe().vari()+ " "  + s.getViehe().tyyppi());ReissunSaalis.appendText("\n");});
		 }
    }

    @FXML public void handleLisaa() {
        lisaaSaaliita();
    }

    private void lisaaSaaliita() {
        LocalDate date = Reissu.sToDate(ReissuPvm.getText());
        if (date == null) {
            Dialogs.showMessageDialog("Virheellinen päivämäärämuoto: " + ReissuPvm.getText() + "\nSyötä muodossa pp-kk-vvvv.");
            return;
        }

        ModalController.showModal(KalenteriMain.class.getResource("/fxKaljoja/KalojaGUIView.fxml"),
                "Lisää saalis",null,r.getSaaliit());
        liitaKaloja();
    }

    public void liitaKaloja() {
        ReissunSaalis.clear();
        for (Saalis s : r.getSaaliit()) {
            Platform.runLater(() -> {
                ReissunSaalis.appendText(s.getLaji() + " " + s.getKoko() + " saatu vieheellä " + s.getViehe().vari() + " " + s.getViehe().tyyppi());
                ReissunSaalis.appendText("\n");
            });
        }
    }

    @FXML
    public void initialize() {
    
    }

    @Override
    public void handleShown() {

    }
    @FXML
    void handleJatka() {
        ModalController.closeStage(jatkaNappi);
    }
    @FXML
    void handlePoista() {
        r.poistetaan();
        
        ModalController.closeStage(ReissuSijainti);
    }
}