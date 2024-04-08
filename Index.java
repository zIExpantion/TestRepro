import java.util.*;
import java.io.*;

public class Index {
	// Attribute
	private final int MAX = 10;
	private String dateiname = "Indexdatei.txt";
	private int indextabelle[]; // 0 .. MAX-1
	private RandomAccessFile eineIndexDatei;
	
	// Konstruktor
	public Index()
	{
		indextabelle = new int[MAX];
		// Initialisierung der indextabelle
		for(int i = 0; i < MAX; i++) indextabelle[i] = -1;
		// Kein Datensatz zu Schluessel vorhanden
	}
	
	// Methoden
	public void erzeugeEintrag(int schluessel, int index) throws IOException
	{
		/** Speichert zu einen Schluessel den zugehoerigen
		 * Datensatz-Index in der indextabelle
		 */
		if(schluessel < MAX)
			indextabelle[schluessel] = index;
		// Aktualisieren der Indexdatei,
		// d. h. Abspeichern der Datei
		aktualisiereIndexDatei(schluessel);
	}
	
	public int gibIndexZuSchluessel(int schluessel)
	{
		// Gibt zu dem Schluessel den gefundenen
		// Datensatz-Index zurueck
		if(schluessel < MAX)
			return indextabelle[schluessel];
		// oder -1, wenn Schluessel zu gross ist
		else
			return -1;
	}
	
	public void ladeIndexDatei() throws IOException
	{
		/** Liest die Indextabelle vollstaendig aus einer Datei
		 * Dies geschieht nur beim Start des Programms
		 */
		eineIndexDatei = new RandomAccessFile(dateiname, "r");
		int index;
		for(int schluessel = 0; schluessel < MAX; schluessel++)
		{
			index = eineIndexDatei.readInt();
			indextabelle[schluessel] = index;
		}
		eineIndexDatei.close();
	}
	
	public void speichereIndexDatei() throws IOException
	{
		/** Speichert die Indextabelle vollstaendig in einer Datei
		 * Dies geschlieht beim beenden des Programs
		 */
		eineIndexDatei = new RandomAccessFile(dateiname, "rw");
		for(int schluessel = 0; schluessel < MAX; schluessel++)
				eineIndexDatei.writeInt(indextabelle[schluessel]);
		eineIndexDatei.close();
	}
	
	private void aktualisiereIndexDatei(int schluessel) throws IOException
	{
		/** Aktualisiert die indextabelle in der Indexdatei
		 * Dies geschliet beim Hinzufuegen eines neuen
		 * Indexes oder Aendern eines alten Indexes
		 */
		eineIndexDatei = new RandomAccessFile(dateiname, "rw");
		// eine int-Zahl belegt 4 Bytes
		eineIndexDatei.seek((long)(schluessel * 4));
		eineIndexDatei.writeInt(indextabelle[schluessel]);
		eineIndexDatei.close();
	}
	
	// Zum Testen
	public void gibIndextabelleAus()
	{
		int schluessel = 0;
		for(int element : indextabelle)
		{
			System.out.println(schluessel + " " + element);
			schluessel++;
		}
	}
}
