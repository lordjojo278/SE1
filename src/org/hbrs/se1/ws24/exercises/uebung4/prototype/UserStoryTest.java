package org.hbrs.se1.ws24.exercises.uebung4.prototype;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    @Test
    void testPriorityCalculation() {
        UserStory userStory = new UserStory(1, "Test Story", "Coll@HBRS", 3, 5, 2);
        assertEquals(30, userStory.getPriority(), "Priority should be value * risk * urgency");
    }

    @Test
    void testGettersAndSetters() {
        UserStory userStory = new UserStory(0,"0","0",0,0,0);
        userStory.setId(1);
        userStory.setTitel("Test Story");
        userStory.setProject("Coll@HBRS");
        userStory.setValue(3);
        userStory.setRisk(4);
        userStory.setUrgency(2);
        userStory.calculatePriority();

        assertEquals(1, userStory.getId());
        assertEquals("Test Story", userStory.getTitel());
        assertEquals("Coll@HBRS", userStory.getProject());
        assertEquals(3, userStory.getValue());
        assertEquals(4, userStory.getRisk());
        assertEquals(2, userStory.getUrgency());
        assertEquals(24, userStory.getPriority(), "Priority should be 3 * 4 * 2 = 24");
    }
}
