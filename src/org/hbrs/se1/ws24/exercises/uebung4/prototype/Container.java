package org.hbrs.se1.ws24.exercises.uebung4.prototype;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Klasse zum Management sowie zur Eingabe und Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2024, Version 1.1
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (VORHER: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Wie bewerten Sie diese Strategie? -> effizient
 *  Was ist ihre Strategie zur Wiederverwendung? (F1) -> Aufteilung der Verantwortlichkeiten in separate Klassen und Nutzung von generischen Typen in Container
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities)
 * sind in einer Klasse, d.h. Container?
 * ToDo: Wie bewerten Sie diese Entscheidung? -> alles in einer Klasse hier beeinträchtigt die Lesbarkeit + spätere Wartung
 *  Was wäre ein sinnvolle Aufteilung (F2, F6) -> z.B. separate Klassen für Eingabe, Ausgabe und Datenverwaltung
 *
 */

public class Container {

	// Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
	private List<UserStory> liste = new ArrayList<>();

	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
	// Todo: Bewertung Thread-Safeness (F1)
	// Todo: Bewertung Speicherbedarf (F1)
	private static final Container instance = new Container();
	// URL der Datei, in der die Objekte gespeichert werden
	final static String LOCATION = "allStories.ser";

	/**
	 * Liefert ein Singleton zurück.
	 * @return
	 */
	public static Container getInstance() {
		return instance;
	}

	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
	 * Nun auf private gesetzt! Vorher ohne Access Qualifier (--> dann package-private)
	 */
	public Container(){
		liste = new ArrayList<>();
	}

	/**
	 * Start-Methoden zum Starten des Programms
	 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
	 */
	public static void main (String[] args) throws Exception {
		// ToDo: Bewertung Exception-Handling (F3, F7) -> ist recht einfach; jedoch besser verständliche/spezifischere Fehlermeldungen/protokollierung wären sinnvoll
		Container con = Container.getInstance();
		con.startEingabe();
	}

	/*
	 * Diese Methode realisiert eine Eingabe ueber einen Scanner
	 * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
	 * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
	 */
	public void startEingabe() throws ContainerException, Exception {
		String strInput = null;

		// Initialisierung des Eingabe-View
		// ToDo: Funktionsweise des Scanners erklären (F3)
		Scanner scanner = new Scanner( System.in );

		while ( true ) {
			// Ausgabe eines Texts zur Begruessung
			System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");

			System.out.print( "> "  );
			strInput = scanner.nextLine();

			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");

			switch (strings[0]) {
				case "help":
					// 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
					System.out.println("Folgende Befehle stehen zur Verfügung: help, dump, enter, store, load, exit");
					break;
				case "dump":
					// Auswahl der bisher implementierten Befehle:
					startAusgabe();
					break;
				case "enter":
					// Auswahl der bisher implementierten Befehle:
					// Daten einlesen ... (Ihre Aufgabe!)
					// this.addUserStory( new UserStory( data ) ) um das Objekt in die Liste einzufügen.
					addNewUserStory(scanner);
					break;
				case "store":
					store();
					break;
				case "load":
					load();
					break;
				case "exit":
					System.out.println("Programm wird beendet.");
					scanner.close();
					return;
				default:
					System.out.println("Unbekannter Befehl, bitte erneut versuchen!");
					break;
			}
		} // Ende der Schleife
	}

	/**
	 * Diese Methode realisiert die Ausgabe.
	 */
	public void startAusgabe() {

		// Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
		// ausgeben. Allerdings weiss der Student hier nicht weiter!

		// [Sortierung ausgelassen]
		// Todo: Implementierung Sortierung (F4)
		List<UserStory> sortedList = liste.stream()
				.filter(story -> story.getProject().equals("Coll@HBRS")) // Beispiel für Filterung nach Projekt
				.sorted(Comparator.comparing(UserStory::getPriority).reversed()) // Priorität absteigend sortieren
				.collect(Collectors.toList());

		// Klassische Ausgabe ueber eine For-Each-Schleife
		for (UserStory story : liste) {
			System.out.println(story.toString());
		}

		//  [Variante mit forEach-Methode / Streams (--> Lösung Übung Nr. 2)?
		//  Gerne auch mit Beachtung der neuen US1
		//  (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und z.B. Prio >=3
		//  Todo: Implementierung Filterung mit Lambda (F5)
		sortedList.forEach(story -> System.out.println(story));
	}

	/*
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
	 *
	 */
	public void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);

			oos.writeObject( this.liste );
			System.out.println( this.size() + " UserStory wurden erfolgreich gespeichert!");
		}
		catch (IOException e) {
			e.printStackTrace();
		  //  Delegation in den aufrufendem Context
		  // (Anwendung Pattern "Chain Of Responsibility)
		  throw new ContainerException("Fehler beim Abspeichern");
		}
	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 *
	 */
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream( Container.LOCATION );
		  ois = new ObjectInputStream(fis);

		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  this.liste = (List) obj;
		  }
		  System.out.println("Es wurden " + this.size() + " UserStory erfolgreich reingeladen!");
		}
		catch (IOException e) {
			System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
	}

	/**
	 * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
	 * @param userStory
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory userStory ) throws ContainerException {
		if ( contains(userStory) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(userStory);
	}

	/**
	 * Prüft, ob eine UserStory bereits vorhanden ist
	 * @param userStory
	 * @return
	 */
	private boolean contains( UserStory userStory) {
		int ID = userStory.getId();
		for ( UserStory userStory1 : liste) {
			if ( userStory1.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory
	 * -Objekten
	 * @return
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<UserStory> getCurrentList() {
		return this.liste;
	}

	/**
	 * Liefert eine bestimmte UserStory zurück
	 * @param id
	 * @return
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory userStory : liste) {
			if (id == userStory.getId() ){
				return userStory;
			}
		}
		return null;
	}

	private void addNewUserStory(Scanner scanner) throws ContainerException {
		System.out.print("Geben Sie die ID der User Story ein: ");
		int id = Integer.parseInt(scanner.nextLine());

		System.out.print("Geben Sie den Titel ein: ");
		String titel = scanner.nextLine();

		System.out.print("Geben Sie das Projekt ein: ");
		String project = scanner.nextLine();

		System.out.print("Geben Sie den Wert ein: ");
		int value = Integer.parseInt(scanner.nextLine());

		System.out.print("Geben Sie das Risiko ein: ");
		int risk = Integer.parseInt(scanner.nextLine());

		System.out.print("Geben Sie die Dringlichkeit ein: ");
		int urgency = Integer.parseInt(scanner.nextLine());

		// Erstelle die UserStory mit den eingegebenen Daten
		UserStory userStory = new UserStory(id, titel, project, value, risk, urgency);

		// Füge die User Story zur Liste hinzu
		this.addUserStory(userStory);
		System.out.println("User Story hinzugefügt: " + userStory);
	}
}
