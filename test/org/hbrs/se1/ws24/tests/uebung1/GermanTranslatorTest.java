package org.hbrs.se1.ws24.tests.uebung1;
import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws24.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {

    private Translator translator;

    @BeforeEach
    public void setUp() {
        translator = new GermanTranslator();
    }

    // Positivfälle: Gültige Zahlen zwischen 1 und 10
    @Test
    public void testValidNumbers() {
        assertEquals("eins", translator.translateNumber(1));
        assertEquals("zwei", translator.translateNumber(2));
        assertEquals("zehn", translator.translateNumber(10));
    }

    // Negativfälle: Ungültige Zahlen außerhalb des Bereichs
    @Test
    public void testInvalidNumbers() {
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)", translator.translateNumber(0));
        assertEquals("Übersetzung der Zahl 11 nicht möglich (1.0)", translator.translateNumber(11));
        assertEquals("Übersetzung der Zahl -5 nicht möglich (1.0)", translator.translateNumber(-5));
    }

}