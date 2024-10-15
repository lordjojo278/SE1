package org.hbrs.se1.ws24.exercises.uebung2;

public class ConcreteMember implements Member {
    private Integer id;

    // Konstruktor, um die ID zu setzen
    public ConcreteMember(Integer id) {
        this.id = id;
    }

    // Methode aus dem Member-Interface, um die ID zurückzugeben
    @Override
    public Integer getID() {
        return this.id;
    }

    // Überschreiben der toString-Methode zur Ausgabe
    @Override
    public String toString() {
        return "Member (ID = " + id + ")";
    }
}

