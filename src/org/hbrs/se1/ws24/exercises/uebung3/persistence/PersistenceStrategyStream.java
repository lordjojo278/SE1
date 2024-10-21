package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // Pfad zur Datei, in der die Objekte gespeichert werden
    private String location = "objects.ser";

    // Backdoor-Methode für Tests, um den Pfad zu ändern
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void save(List<E> members) throws PersistenceException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(location))) {
            oos.writeObject(members);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fehler beim Speichern der Objekte");
        }
    }

    @Override
    public List<E> load() throws PersistenceException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(location))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return (List<E>) obj;
            } else {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Ungültige Daten in der Datei");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fehler beim Laden der Objekte");
        }
    }
}
