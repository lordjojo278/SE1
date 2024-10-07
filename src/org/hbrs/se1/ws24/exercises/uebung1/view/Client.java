package org.hbrs.se1.ws24.exercises.uebung1.view;

import com.sun.java.accessibility.util.Translator;
import org.hbrs.se1.ws24.exercises.uebung1.control.Factory;

public class Client{

	private Translator translator;

	// Konstruktor, der ein Translator-Objekt über die Factory zuweist
	public Client() {
		this.translator = Factory.createTranslator();
	}

		/**
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

			 String result = translator.translateNumber(aNumber);
			 System.out.println("Das Ergebnis der Berechnung: " + result);

		 }
}





