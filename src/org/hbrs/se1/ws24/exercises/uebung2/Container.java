package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<>();

    // Methode zum Hinzufügen eines Members
    public void addMember(Member member) throws ContainerException {
        for (Member m : members) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
            }
        }
        members.add(member);
    }

    // Methode zum Löschen eines Members anhand der ID
    public String deleteMember(Integer id) {
        for (Member m : members) {
            if (m.getID().equals(id)) {
                members.remove(m);
                return "Member mit der ID " + id + " erfolgreich gelöscht.";
            }
        }
        return "Kein Member mit der ID " + id + " gefunden.";
    }

    // Methode zur Ausgabe aller Member-IDs
    public void dump() {
        for (Member m : members) {
            System.out.println(m.toString());
        }
    }

    // Methode zur Ermittlung der Anzahl gespeicherter Member
    public int size() {
        return members.size();
    }
}
