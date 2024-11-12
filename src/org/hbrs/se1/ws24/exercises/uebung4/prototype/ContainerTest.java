package org.hbrs.se1.ws24.exercises.uebung4.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
        container.getCurrentList().clear(); // Clear list before each test
    }

    @Test
    void testAddUserStory() throws ContainerException {
        UserStory userStory = new UserStory(1, "Test Story", "Coll@HBRS", 3, 5, 2);
        container.addUserStory(userStory);

        assertEquals(1, container.size());
        assertEquals("Test Story", container.getCurrentList().get(0).getTitel());
    }

    @Test
    void testDuplicateUserStoryIdThrowsException() {
        UserStory userStory1 = new UserStory(1, "Story 1", "Coll@HBRS", 2, 3, 4);
        UserStory userStory2 = new UserStory(1, "Story 2", "Coll@HBRS", 1, 2, 3);

        assertDoesNotThrow(() -> container.addUserStory(userStory1));

        assertThrows(ContainerException.class, () -> container.addUserStory(userStory2),
                "Adding a UserStory with a duplicate ID should throw a ContainerException");
    }

    @Test
    void testStoreAndLoad() throws ContainerException {
        // Hinzufügen von UserStories
        UserStory userStory1 = new UserStory(1, "Story 1", "Coll@HBRS", 3, 5, 2);
        UserStory userStory2 = new UserStory(2, "Story 2", "Coll@HBRS", 2, 4, 3);
        container.addUserStory(userStory1);
        container.addUserStory(userStory2);

        // Speichern in Datei
        container.store();

        // Leere die Liste und lade neu
        container.getCurrentList().clear();
        assertEquals(0, container.size(), "List should be empty after clearing");

        container.load();

        // Teste, ob UserStories korrekt geladen wurden
        assertEquals(2, container.size());
        assertEquals("Story 1", container.getCurrentList().get(0).getTitel());
        assertEquals("Story 2", container.getCurrentList().get(1).getTitel());

        // Bereinigen
        new File(Container.LOCATION).delete(); // Lösche Datei nach Test
    }

    @Test
    void testSortAndFilterByPriority() throws ContainerException {
        UserStory userStory1 = new UserStory(1, "Story 1", "Coll@HBRS", 3, 5, 2); // Priority: 30
        UserStory userStory2 = new UserStory(2, "Story 2", "OtherProject", 2, 4, 3); // Priority: 24
        UserStory userStory3 = new UserStory(3, "Story 3", "Coll@HBRS", 1, 4, 5); // Priority: 20

        container.addUserStory(userStory1);
        container.addUserStory(userStory2);
        container.addUserStory(userStory3);

        List<UserStory> filteredAndSorted = container.getCurrentList().stream()
                .filter(story -> story.getProject().equals("Coll@HBRS"))
                .sorted((s1, s2) -> Double.compare(s2.getPriority(), s1.getPriority()))
                .toList();

        assertEquals(2, filteredAndSorted.size(), "Filtered list should contain only stories from Coll@HBRS project");
        assertEquals("Story 1", filteredAndSorted.get(0).getTitel(), "First story should have highest priority (30)");
        assertEquals("Story 3", filteredAndSorted.get(1).getTitel(), "Second story should have next highest priority (20)");
    }
}
