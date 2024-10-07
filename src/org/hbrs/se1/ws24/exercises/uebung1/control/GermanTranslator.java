package org.hbrs.se1.ws24.exercises.uebung1.control;

public class GermanTranslator implements Translator {

	public String date = "Okt/2024"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ) {
		if(number > 10 || number < 1) {		// nur Zahlen im bereich zwischen 1 und 10 werden übersetzt sonst return fehlermeldung
			return "Übersetzung der Zahl " + number + " nicht möglich (" + Translator.version + ")";
		}
		String gerNum[] = {"eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "zehn"};
		return gerNum[number-1];		// übersetzung mittels index im string[]
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2024"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
