package org.hbrs.se1.ws24.exercises.uebung1.control;

public class Factory {
    public static Translator createTranslator() {
        // GermanTranslator oder austausch durch einen anderen Translator
        return new GermanTranslator();
    }
}
