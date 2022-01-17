package q2;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;
import java.util.ArrayList;

/**
 * represents a time sheet.
 *
 * @author Yoshiyuki Nagai, set C
 * @version 1.0
 */
public class Timesheet {

    /**
     * an employee's number on this time sheet. 
     */
    private final String empNum;
    
    /**
     * the last day of the week.
     */
    private final LocalDate endWeek;
    
    /**
     * rows that contain the details of this time sheet.
     */
    private List<TimesheetRow> details;
    
    /**
     * constructs a time sheet.
     * @param empNum an employee number on this time sheet
     * @param endWeek an end week of this time sheet
     */
    public Timesheet(String empNum, LocalDate endWeek) {
        this.empNum = empNum;
        if (endWeek.getDayOfWeek() == DayOfWeek.FRIDAY) {
            this.endWeek = endWeek;
        } else {
            throw new IllegalArgumentException(endWeek.getDayOfWeek() 
                + " is not Friday");
        }
        this.details = new ArrayList<TimesheetRow>();
    }
    
    /**
     * constructs a time sheet.
     */
    public Timesheet() {
        this.empNum = "";
        this.endWeek = null;
        this.details = new ArrayList<TimesheetRow>();
    }

    /**
     * adds a new row to this time sheet.
     * @param row a new row to add
     */
    public void addRow(TimesheetRow row) {
        details.add(row);
    }
    
    


    /**
     * gets the details of this time sheet.
     * @return the details of this time sheet in a form of a list
     */
    public List<TimesheetRow> getDetails() {
        return details;
    }

    /**
     * sets the details of this time sheet.
     * @param details the details to set
     */
    public void setDetails(List<TimesheetRow> details) {
        this.details = details;
    }

    /**
     * gets the employee number on this time sheet.
     * @return the empNum
     */
    public String getEmpNum() {
        return empNum;
    }

    /**
     * gets the end week of this time sheet.
     * @return the endWeek
     */
    public LocalDate getEndWeek() {
        return endWeek;
    }
    
    /**
     * returns a string version of each row of this time sheet.
     * @return the result of concatenating three information in a sheet row.
     */
    public String toString() {
        final int loopLimit = TimesheetRow.DAY_LIMIT + 1;
        String res = "Employee Number:";
        res += empNum + "\n";
        res += "End Week: " + endWeek + "\n";
        for (TimesheetRow timesheetRow : details) {
            res += "Project Number: " + timesheetRow.getProject() + "\t" 
                + "Work package used: " + timesheetRow.getWorkPackage() + "\t"
                + "hours worked per day: ";
            for (int i = 0; i < loopLimit; i++) {
                res += timesheetRow.getHour(i) + "hrs ";
            }
            res += "\n";
        }
        return res + "\n";
    }

    /**
     * creates three TImesheetRow objects and add them to a TImesheet object.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        final int year = 2021;
        final int month = 11;
        final int day = 26;
        
        Timesheet sheet1 = new Timesheet("A3902", LocalDate.of(year, 
            month, day));
        final float[] workingHours1 = {3.2f, 1.1f, 4.5f, 
            5.5f, 3.2f, 2.0f, 5.0f};
        final float[] workingHours2 = {0f, 1.1f, 4.5f, 
            5.5f, 3.2f, 2.0f, 5.0f};
        final float[] workingHours3 = {0f, 1.1f, 4.5f, 
            5.5f, 3.2f, 2.0f, 5.0f};
       
        
        TimesheetRow tr1 = new TimesheetRow(1, "whatever", workingHours1);
        TimesheetRow tr2 = new TimesheetRow(0, "whatever", workingHours2);
        TimesheetRow tr3 = new TimesheetRow(2, "whatever", workingHours3);
        
        sheet1.details.add(tr1);
        sheet1.details.add(tr2);
        sheet1.details.add(tr3);

        System.out.println(sheet1.toString());
    }

}
