public class ContainerTest {
    public static void main(String[] args) {
        // Container erstellen
        Container container = new Container();

        // Zwei Member-Objekte mit unterschiedlichen IDs erstellen
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);

        // Testfall 1: Member 1 hinzufügen
        try {
            container.addMember(member1);
            System.out.println("Member 1 hinzugefügt.");
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }

        // Testfall 2: Member 1 erneut hinzufügen (sollte Exception werfen)
        try {
            container.addMember(member1); // Doppelte ID
        } catch (ContainerException e) {
            System.out.println(e.getMessage()); // Erwartet: Exception
        }

        // Testfall 3: Member 2 hinzufügen
        try {
            container.addMember(member2);
            System.out.println("Member 2 hinzugefügt.");
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }

        // Testfall 4: Anzahl der Member überprüfen
        System.out.println("Anzahl der Member: " + container.size()); // Erwartet: 2

        // Testfall 5: IDs der Member ausgeben
        container.dump(); // Erwartete Ausgabe: IDs von member1 und member2

        // Testfall 6: Member 1 löschen
        System.out.println(container.deleteMember(1)); // Member 1 erfolgreich gelöscht.

        // Testfall 7: Anzahl nach Löschen überprüfen
        System.out.println("Anzahl der Member: " + container.size()); // Erwartet: 1
    }
}
