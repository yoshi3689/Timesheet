package q2;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * a JUnit test for TimeSheet class.
 *
 * @author Yoshiyuki Nagai, Set C
 * @version 1.0
 */
public class TimesheetTest {
    private final float[] workingHours1 = {3.2f, 1.1f, 4.5f, 
            5.5f, 3.2f, 2.0f, 5.0f};
    TimesheetRow tr1 = new TimesheetRow(1, "whatever", workingHours1);
    private Timesheet sheet1  = new Timesheet("A3902", LocalDate.of(2021, 
        11, 19));
    private Timesheet emptySheet  = new Timesheet();
    
    @Test
    void testTimeSheet1() {
        assertTrue(sheet1.getDetails() instanceof ArrayList<?>);
        assertTrue(sheet1.getEmpNum().equals("A3902"));
        assertEquals(sheet1.getEndWeek(), LocalDate.of(2021, 
                11, 19));
        LocalDate illegalDate = LocalDate.of(2021, 3, 11);
        assertThrows(IllegalArgumentException.class, () -> {
            new Timesheet("A0493", illegalDate);
        }, illegalDate.getDayOfWeek() + " is not Friday");
    }
    
    @Test
    void testTimeSheet2() {
        assertTrue(emptySheet.getDetails() instanceof ArrayList<?>);
        assertTrue(emptySheet.getEmpNum().equals(""));
        assertTrue(emptySheet.getEndWeek() == null);
    }
    
    @Test
    void testAddRow() {
        sheet1.addRow(tr1);
        assertTrue(sheet1.getDetails().get(0) instanceof TimesheetRow);
    }
    
    @Test
    void testSetDetails() {
        sheet1.setDetails(null);
        assertEquals(sheet1.getDetails(), null);
        sheet1.setDetails(new ArrayList<TimesheetRow>());
        sheet1.addRow(tr1);
        assertTrue(sheet1.getDetails() instanceof ArrayList<?>);
        assertTrue(sheet1.getDetails().get(0) instanceof TimesheetRow);
    }
    
    @Test 
    void testGetEmpNum() {
        assertEquals(sheet1.getEmpNum(), "A3902");
    }
    
    @Test 
    void testEndWeek() {
        assertEquals(sheet1.getEndWeek(), LocalDate.of(2021, 
                11, 19));
    }
    
    @Test 
    void testToString() {
        sheet1.addRow(tr1);
        assertEquals(sheet1.toString(), "Employee Number:A3902\n"
                + "End Week: 2021-11-19\n"
                + "Project Number: 1\tWork package used: whatever\thours worked"
                + " per day: 3.2hrs 1.1hrs 4.5hrs 5.5hrs 3.2hrs 2.0hrs 5.0hrs "
                + "\n\n" );
    }
    


    }
