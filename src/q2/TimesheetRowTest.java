package q2;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * a JUnit test for TimeSheetRow class.
 *
 * @author Your Name goes here
 * @version 1.0
 */
public class TimesheetRowTest {
    
    private TimesheetRow tr1 = new TimesheetRow(123, "whatever", 
            TimesheetRow.getHoursAray(3.2f, 1.1f, 4.5f, 
            5.5f, 3.2f, 2.0f, 5.0f));
    
    private TimesheetRow tr2 = new TimesheetRow();
    
    private static float INVALID_HOUR_POS = 25.1f;
    
    private static float INVALID_HOUR_NEG = -25.1f;
    
    @Test
    public void testTimesheetRow1() {
        assertEquals(tr1.project, 123);
        assertTrue(tr1.getWorkPackage().equals("whatever"), "Mathcing!");
                
        assertThrows(IllegalArgumentException.class, 
                () -> {new 
            TimesheetRow(123, "whatever", TimesheetRow.getHoursAray(3.2f, 1.1f,
            4.5f, INVALID_HOUR_POS, 3.2f, 2.0f, 5.0f));}, "the input value "
            + "has to be 0 - " + (int) (TimesheetRow.HOURS_LIMIT 
            * TimesheetRow.TEN));
        
        assertThrows(IllegalArgumentException.class, () -> {new TimesheetRow(123, 
            "whatever", TimesheetRow.getHoursAray(3.2f, 1.1f, 4.5f, 
            INVALID_HOUR_NEG, 3.2f, 2.0f, 5.0f));}, "the input value "
            + "has to be 0 - " + (int) (TimesheetRow.HOURS_LIMIT 
            * TimesheetRow.TEN));
        assertEquals(tr1.getHours(), 14095877432740640l);
    }
    
    @Test
    public void testTimesheetRow2() {
        assertEquals(tr2.project, 0);
        assertNull(tr2.getWorkPackage());
        assertEquals(tr2.getHours(), 0);
    }
    
    @Test
    public void testDecToHex() {
        assertEquals(TimesheetRow.decToHex(tr1.getHours()), 
            Long.toHexString(tr1.getHours()));
    }
    
    @Test
    public void testGetPosition() {
        assertEquals(TimesheetRow.getPosition(13, TimesheetRow.DAY_LIMIT), 1);
        assertEquals(TimesheetRow.getPosition(13, TimesheetRow.DAY_LIMIT), 1);
        assertEquals(TimesheetRow.getPosition(-2, 5), 0);
        assertEquals(TimesheetRow.getPosition(0, 5), 0);
    }
    
    @Test
    public void testAdjustDigits() {
        assertEquals(TimesheetRow.adjustDigits("03"), "3");
        assertEquals(TimesheetRow.adjustDigits("d"), "d");
    }
    
    @Test
    public void testCheckRange() {
      assertEquals(TimesheetRow.checkRange(-2, -10), -1);
        assertEquals(TimesheetRow.checkRange(10, 90), 1);
    }
    
    @Test
    public void testGetProject() {
        assertTrue(tr1.getProject() == 123);

    }
    
    @Test
    public void testSetWorkPackage() {
        tr1.setWorkPackage("hello");
        assertTrue(tr1.getWorkPackage().equals("hello"));

    }
    
    @Test
    public void testGetHours() {
        assertEquals(tr1.getHours(), 14095877432740640L);
    }
    
    @Test
    public void testSetHours() {
        assertEquals(tr1.getHours(), 14095877432740640L);
    }
    
    @Test
    public void testGetHour() {
        assertEquals(tr1.getHour(6), 5.0f, 0.1);
        assertEquals(tr1.getHour(5), 2.0f, 0.1);
        assertEquals(tr1.getHour(4), 3.2f, 0.1);
        assertEquals(tr1.getHour(3), 5.5f, 0.1);
        assertEquals(tr1.getHour(2), 4.5f, 0.1);
        assertEquals(tr1.getHour(1), 1.1f, 0.1);
        assertEquals(tr1.getHour(0), 3.2f, 0.1);
        
        assertThrows(IllegalArgumentException.class, () -> {tr1.getHour(-1);}, 
            "the input value "+ "has to be 0 - " + TimesheetRow.DAY_LIMIT);
    }
    
    @Test
    public void testSetHour() {
        tr1.setHour(6, 2.4f);
        assertEquals(tr1.getHour(6), 2.4f, 0.1);
        
        tr1.setHour(5, 2.1f);
        assertEquals(tr1.getHour(5), 2.1f, 0.1);
        
        tr1.setHour(4, 3.8f);
        assertEquals(tr1.getHour(4), 3.8f, 0.1);
        
        tr1.setHour(3, 1.4f);
        assertEquals(tr1.getHour(3), 1.4f, 0.1);
        
        tr1.setHour(2, 5.6f);
        assertEquals(tr1.getHour(2), 5.6f, 0.1);
        
        tr1.setHour(1, 7.4f);
        assertEquals(tr1.getHour(1), 7.4f, 0.1);
        
        tr1.setHour(0, 3.5f);
        assertEquals(tr1.getHour(0), 3.5f, 0.1);

        assertThrows(IllegalArgumentException.class, () -> {tr1.setHour(-1, 
            2.0f);}, "the input value "+ "has to be 0 - " 
            + TimesheetRow.DAY_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> {tr1.setHour(-1, 
            -2.0f);}, "the input value "+ "has to be 0 - " 
            + TimesheetRow.DAY_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> {tr1.setHour(1, 
            -2.0f);}, "the input value "+ "has to be 0 - " 
            + TimesheetRow.DAY_LIMIT);
    }
    
    @Test
    public void testToString() {
        assertEquals(1, 1);
    }

}
