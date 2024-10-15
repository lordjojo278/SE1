package org.hbrs.se1.ws24.exercises.uebung2;

public class ContainerException extends Exception {

    // Konstruktor, der eine Fehlermeldung aufnimmt
    public ContainerException(String message) {
        super(message);  // Übergibt die Nachricht an die Exception-Klasse
    }
}
