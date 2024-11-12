package org.hbrs.se1.ws24.exercises.uebung4.prototype;
    public class UserStory {
        // ToDo: Sind die Attribute der Klasse UserStory vollständig? Wie sieht es mit den
        //  Sichtbarkeiten aus? (F4)

        String titel;
        int id = 0;
        double prio = 0.0;
        String project;

        // Neue Attribute für die Prioritätsberechnung
        int value;
        int risk;
        int urgency;

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        // Konstruktor mit allen Attributen für die Prioritätsberechnung
        public UserStory(int id, String titel, String project, int value, int risk, int urgency) {
            this.id = id;
            this.titel = titel;
            this.project = project;
            this.value = value;
            this.risk = risk;
            this.urgency = urgency;
            calculatePriority(); // Berechnung der Priorität bei der Erstellung
        }

        public UserStory(int id, String titel, double prio) {
            this.id = id;
            this.titel = titel;
            this.prio = prio;
        }

        public double getPrio() {
            return prio;
        }

        public void setPrio(double prio) {
            this.prio = prio;
        }

        public String getTitel() {
            return titel;
        }

        public void setTitel(String titel) {
            this.titel = titel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        // Getter und Setter für die neuen Attribute
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
            calculatePriority(); // Aktualisiert die Priorität
        }

        public int getRisk() {
            return risk;
        }

        public void setRisk(int risk) {
            this.risk = risk;
            calculatePriority(); // Aktualisiert die Priorität
        }

        public int getUrgency() {
            return urgency;
        }

        public void setUrgency(int urgency) {
            this.urgency = urgency;
            calculatePriority(); // Aktualisiert die Priorität
        }

        // Methode zur Berechnung der Priorität basierend auf Wert, Risiko und Dringlichkeit
        public void calculatePriority() {
            this.prio = value * risk * urgency;
        }

        // Methode zur Prioritätsabfrage
        public double getPriority() {
            return this.prio;
        }

        // Überschreibe toString für eine sinnvolle Ausgabe
        @Override
        public String toString() {
            return "UserStory{" +
                    "id=" + id +
                    ", titel='" + titel + '\'' +
                    ", priority=" + prio +
                    ", project='" + project + '\'' +
                    '}';
        }
    }




