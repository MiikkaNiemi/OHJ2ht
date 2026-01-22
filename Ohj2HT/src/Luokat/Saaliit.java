package Luokat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Saaliit {
    private Saalis[] saaliit;
    private int koko;
    private int index = 0;


    /**
     * muodostaja tyjhään taulukkoon
     * @param koko kuinka suuri
     */
    public Saaliit(int koko) {
        this.koko = koko;
        this.saaliit = new Saalis[koko];
    }

    public int getKoko() {
        return index;
    }

    /**
     * lisää saaliin olioon
     * @param saalis olio joka lisätään
     *@example <pre name="test">
     *  Saaliit saaliit = new Saaliit(1);
     * Saalis s = new Saalis("iso", "ahven", "lippa", "sininen");
     * saaliit.lisaa(s);
     * Saalis s2 = new Saalis("iso", "ahven", "lippa", "sininen");
     * saaliit.lisaa(s2);
     * saaliit.getKoko() === 2;
     * </pre>
     */
    public void lisaa(Saalis saalis) {
        if (index == koko){
            kasvataTaulukkoa();
            saaliit[index] = saalis;
            index++;
        }
        else {
            saaliit[index] = saalis;
            index++;
        }
    }

    /**
     * Kasvattaa taulukon eli siis luo uuden
     */
    private void kasvataTaulukkoa() {

        Saalis[] taulukko = new Saalis[koko + 10];
        for (int i = 0; i < koko; i++){
            taulukko[i] = saaliit[i];
        }
        saaliit = taulukko;

    }

    public Saalis getSaalis(int index) {
        return saaliit[index];
    }

    public void tallenna() {
        Tulkki lajit = new Tulkki(System.getProperty("user.dir") + "/../lajit.dat");
        Tulkki vieheTulkki = new Tulkki(System.getProperty("user.dir") + "/../vieheTyypit.dat");
        Tulkki kokoTulkki = new Tulkki(System.getProperty("user.dir") + "/../koot.dat");
        TiedostonHiplailija saalisData = new TiedostonHiplailija(System.getProperty("user.dir")+ "/../saaliit.dat",false);
        for (Saalis s : saaliit){
            if (s != null) {
                saalisData.kirjoita(s.tiedotTallenusMuodossa(lajit,vieheTulkki,kokoTulkki));
            }
        }
        saalisData.sulje();
    }

    public List<Saalis> getSaaliitList() {
        List<Saalis> saaliitList = new ArrayList<>();
        for (Saalis saalis : saaliit){
            if (saalis != null){
                saaliitList.add(saalis);
            }
        }
        return saaliitList;
    }

    public static void main(String[] args){
        Saaliit saaliit = new Saaliit(2);
        Saalis s = new Saalis("iso", "ahven", "lippa", "sininen");
        saaliit.lisaa(s);
        Saalis s2 = new Saalis("iso", "hauki", "vaappu", "sininen");
        saaliit.lisaa(s2);
        Saalis s3 = new Saalis("iso", "kuha", "lippa", "hopea");
        saaliit.lisaa(s3);
    }
}
