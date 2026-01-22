package Luokat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import Luokat.Reissu;

/**
 * Pitää sisällään kaikki reissut
 */
public class Reissut {

	    private List<Reissu> reissut;

	/**
	 * muodostaja
	 */
	public Reissut(Saaliit saaliit) {
		this.reissut = new ArrayList<>();
		luoReissut(saaliit);
	}

	
	private void luoReissut(Saaliit saaliit) {
		TiedostonHiplailija t = new TiedostonHiplailija(System.getProperty("user.dir") + "/../reissut.dat");
		List<Saalis> saaliitList = saaliit.getSaaliitList(); //Kaikki saaliit
		for (String s : t.lueRivit()) {
			Reissu a = new Reissu(s);
			List<Saalis> apuri = new ArrayList<>(); // Tässä luodaan tietyn reissun saaliiden lista
			for (Saalis saalis : saaliitList) {
				if (saalis.getReissunTagi() == a.getTag()) {
					apuri.add(saalis);
				}
			}
			a.setSaaliit(apuri);
			this.reissut.add(a);
		}
	}

	/**
	 * lisää reissun listaan
	 * @param reissu mikä reissu lisätään
	 */
	public void lisaaListaan(Reissu reissu) {
	   	reissut.add(reissu);
	}


	/**
	 * palauttaa reissu listan
	 * @return reissu lista
	 */
	public List<Reissu> getReissut() {
		return reissut;
	}
	
	
	public void tallenna(){
		TiedostonHiplailija reissu = new TiedostonHiplailija(System.getProperty("user.dir") + "/../reissut.dat",false);
		for (Reissu r : reissut) {
			System.out.println(r);
			reissu.kirjoita(r.toString());
		}
		reissu.sulje();
	}

	/**
	 * etsii reissun päivämäärän perusteella
	 * @param paivamaara mikä päivä
	 * @return em. päivän reissun
	 */
	
	public static Reissu etsi(String paivamaara, Reissut reissut) {
		
		for (Reissu t : reissut.getReissut()) {
			if (t.getPaivamaara().equals(paivamaara)) {
				return t;
			}
		}
		return null;
	}
	
	public void sort() {
		reissut.sort(Comparator.comparing(Reissu::getPaivamaara).reversed());
	}
	
	
	/**
	 * käy reissut läpi ja katsoo onko missään yksittäisessä reissussa poistetaanko 
	 * arvo = true. Tällöin reissu poistetaan
	 */
	
	
	public void removeReissu() {
		   Iterator<Reissu> iterator = reissut.iterator();
		    while (iterator.hasNext()) {
		        Reissu reissu = iterator.next();
		        if (reissu.getpoistetaanko()) {
		            iterator.remove();  
		            break;
		        }
		    }
	}
	
	/* testit
	/**@example
	* <pre name="test">
	* 	#import java.time.LocalDate;
		#import java.util.ArrayList;
		#import java.util.List;

		#import org.junit.Before;
		#import org.junit.Test;

		#import Luokat.Reissu;
		#import Luokat.Reissut;
		#import Luokat.Tulkki;
		#import Luokat.Saaliit;
		
	* 
	* 	Saaliit saaliita = new Saaliit(8);	
	*    Reissut test = new Reissut(saaliita); 
	*    test.getReissut().clear();
	*    
	*    Reissu t0 = new Reissu(Reissu.sToDate("1.12.2323"), "ka", "pilvi", new ArrayList<>(), false); 
	*    Reissu t1 = new Reissu(Reissu.sToDate("12.12.2023"), "jk", "sade", new ArrayList<>(), false); 
	*    Reissu t2 = new Reissu(Reissu.sToDate("12.12.1223"), "sk", "aurinko", new ArrayList<>(), false); 
	*    Reissu t3 = new Reissu(Reissu.sToDate("15.11.2024"), "ka", "pilvi", new ArrayList<>(), false); 
	*    test.lisaaListaan(t1); 
	*    test.lisaaListaan(t2); 
	*    test.lisaaListaan(t3); 
	*    test.lisaaListaan(t0);
	*    test.getReissut().size() === 4;
	*    t1.poistetaan();
	*    
	*    test.removeReissu();
	*     test.getReissut().size() === 3;
	* </pre>
	*/
	    // Testi

	//todo:
	//tallenna()
	    

	    // kesken...
	public int[] paivamaaraToInt(String pm) {
		String[] osat = pm.split(".");
		if (osat.length < 3) {
		return null;
		}
		int[] pv = new int[3];
		
		pv[0] = Integer.parseInt(osat[0]);
		pv[1] = Integer.parseInt(osat[1]);
		pv[2] = Integer.parseInt(osat[2]);
		return pv;
	
	}
//todo:
// tallenna()
	
	
}


