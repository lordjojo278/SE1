package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
public class Container {
    // Singleton-Instanz
    private static Container instance;

    // Interne Liste zur Abspeicherung der Member-Objekte
    private List<Member> liste = new ArrayList<>();

    // Persistenzstrategie
    private PersistenceStrategy<Member> strategy;

    // Privater Konstruktor (für das Singleton)
    private Container() {}

    // Statische Methode zur Rückgabe der einzigen Instanz
    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    // Methode zum Setzen der Persistenzstrategie
    public void setPersistenceStrategy(PersistenceStrategy<Member> strategy) {
        this.strategy = strategy;
    }

    // Methode zum Speichern der Member-Objekte
    public void store() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenzstrategie gesetzt!");
        }
        strategy.save(liste);
    }

    // Methode zum Laden der Member-Objekte
    public void load() throws PersistenceException {
        if (strategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenzstrategie gesetzt!");
        }
        liste = strategy.load();
    }

    // Methode zum Hinzufügen von Member-Objekten
    public void addMember(Member member) throws ContainerException {
        if (member == null) {
            throw new ContainerException("Member-Objekt ist null");
        }
        if (contains(member)) {
            throw new ContainerException("Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }
        liste.add(member);
    }

    // Methode zur Überprüfung, ob ein Member-Objekt bereits vorhanden ist
    private boolean contains(Member member) {
        for (Member m : liste) {
            if (m.getID().equals(member.getID())) {
                return true;
            }
        }
        return false;
    }

    // Methode zum Löschen eines Member-Objekts
    public String deleteMember(Integer id) {
        Member member = getMember(id);
        if (member != null) {
            liste.remove(member);
            return "Member mit der ID " + id + " wurde gelöscht.";
        } else {
            return "Member mit der ID " + id + " nicht gefunden.";
        }
    }

    // Methode zum Abrufen der aktuellen Liste von Member-Objekten
    public List<Member> getCurrentList() {
        return new ArrayList<>(liste);
    }

    // Interne Methode zur Ermittlung eines Member-Objekts
    private Member getMember(Integer id) {
        for (Member m : liste) {
            if (m.getID().equals(id)) {
                return m;
            }
        }
        return null;
    }
}
