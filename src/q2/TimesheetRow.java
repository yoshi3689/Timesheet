package q2;


/**
 * represents a row in a Timesheet object.
 * @author Yoshiyuki Nagai, set C
 * @version 1.0
 */
public class TimesheetRow {
    
    
    /**
     * the maximum hours an employee can work per day.
     */
    public static final float HOURS_LIMIT = 24.0f;
    
    /**
     * one of the two constants for base conversions between 
     * a decimal and hexadecimal.
     */
    public static final int HEX = 16;
    
    /**
     * one of the two constants for base conversions
     *  between a decimal and hexadecimal.
     */    
    public static final int TEN = 10;
    
    /**
     * the maximum value for a day in the week.
     */
    public static final int DAY_LIMIT = 6;
    
    /**
     * the last index of an encoded working hours.
     */
    private static final int HEX_HOURS_L_I = 13;

    /**
     * a project number.
     */
    public final int project;
    
    /**
     * a work package the employee is using.
     */
    private String workPackage;
    
    /**
     * an encoded version of the total hours worked this week.
     */
    private long hours;
    
    /**
     * non-argument constructor.
     */
    public TimesheetRow() {
        this.project = 0;
        this.workPackage = null;
        this.hours = 0;
    }
    
    /**
     * constructs a TimeSheetRow object.
     * @param project a project number
     * @param workPackage the name of the work package
     * @param everyDayHours a list of working hours for this week.
     */
    public TimesheetRow(int project, String workPackage, 
        float[] everyDayHours) {
        this.project = project;
        this.workPackage = workPackage;
        setHours(everyDayHours);
    }
    
    /**
     * converts a decimal integer to a string.
     * @param workingHours hours worked
     * @return a hexadecimal representation of working hours
     */
    public static String decToHex(long workingHours) {
        return Long.toHexString(workingHours);
    }
    
    /**
     * creates an array of floats which represent working hours for each day. 
     * @param sa working hours for Saturday
     * @param su working hours for Sunday
     * @param mo working hours for Monday
     * @param tu working hours for Tuesday
     * @param we working hours for Wednesday
     * @param th working hours for Thursday
     * @param fr working hours for Friday
     * @return an array of working hours in a week.
     */
    public static float[] getHoursAray(float sa, float su, float mo, float tu, 
        float we, float th, float fr) {
        float[] hoursArr = {sa, su, mo, tu, we, th, fr};
        return hoursArr;
    }
    
    /**
     * gets a position of the daily working hours for a specific day.
     * @param loopLimit the sentinel of the loop
     * @param dayOfTheWeek the day to get working hours
     * @return position of the working hours for the given day
     */
    public static int getPosition(int loopLimit, int dayOfTheWeek) {
        int counter = loopLimit;
        if (loopLimit >= 0) {
            for (int i = 0; i < loopLimit; i = i + 2) {
                if (i / 2 == dayOfTheWeek) {
                    break;
                }
                counter = counter - 2;
            }
        } else {
            counter = 0;
        }
        return counter;
    }
    
    /**
     * removes the zero if working hours string starts with 0.
     * @param dailyHoursString hours worked
     * @return adjusted string
     */
    public static String adjustDigits(String dailyHoursString) {
        if (dailyHoursString.charAt(0) == '0') {
            dailyHoursString = "" + dailyHoursString.charAt(1);
        }
        return dailyHoursString;
    }
    
    /**
     * checks if the value is within the bounds.
     * @param valueToCheck the value to check
     * @param upperLimit the upper limit of a range
     * @return null or an exception
     */
    public static int checkRange(int valueToCheck, int upperLimit) {
        if (valueToCheck >= 0 && valueToCheck <= upperLimit) {
            return 1;
        } else {
            return -1;
        }
    }
    
    /**
     * gets a project number the employee is currently assigned to.
     * @return the project
     */
    public int getProject() {
        return project;
    }


    /**
     * gets a work package the employee is currently using.
     * @return the workPackage
     */
    public String getWorkPackage() {
        return workPackage;
    }

    /**
     * sets a new work package.
     * @param workPackage the workPackage to set
     */
    public void setWorkPackage(String workPackage) {
        this.workPackage = workPackage;
    }
    

    /**
     * gets an encoded version of the hours the employee worked.
     * @return the hours
     */
    public long getHours() {
        return hours;
    }

    /**
     * sets an encoded version of the hours the employee worked.
     * @param hoursWeekly the hours to set
     */
    public void setHours(float[] hoursWeekly) {
        String strToStore = "";
        for (int i = 0; i < hoursWeekly.length; i++) {
            hoursWeekly[i] *= TEN;
            if (checkRange((int) hoursWeekly[i], (int) (HOURS_LIMIT * TEN)) 
                == 1)  {
                String hexDigit = Integer.toHexString((int) hoursWeekly[i]);
                if (hexDigit.length() == 1) {
                    hexDigit = "0" + hexDigit;
                }
                strToStore = hexDigit + strToStore;
            } else {
                throw new IllegalArgumentException("the input value has to "
                        + "be 0 - " + (int) (TimesheetRow.HOURS_LIMIT 
                        * TimesheetRow.TEN));
            }
        }
        this.hours = Long.parseLong(strToStore, HEX);
    }
    
    /**
     * returns the hours the employee worked on that day.
     * @param dayOfTheWeek an integer representation of a day in a week 
     * @return the hours worked on that day
     */
    public float getHour(int dayOfTheWeek) {
        float floatToReturn = 0.0f;
        String dailyHoursString = "";
        if (checkRange(dayOfTheWeek, DAY_LIMIT) == 1) {
            String hexString = decToHex(hours);
            int dayToGetHours = getPosition(HEX_HOURS_L_I,
                dayOfTheWeek);
            
            dailyHoursString = hexString.charAt(dayToGetHours - 1) + "" 
                    + hexString.charAt(dayToGetHours);
            dailyHoursString = adjustDigits(dailyHoursString);
            floatToReturn =  
                    ((float) Integer.parseInt(dailyHoursString, HEX) / TEN);
            return floatToReturn;
        } else {
            throw new IllegalArgumentException("the input value has to be 0 - "
                + DAY_LIMIT);
        }
    }

    /**
     * sets a daily working hours with the given day and hours.
     * @param dayOfTheWeek an integer version of Monday - Friday
     * @param hoursWorked hours worked on that day
     */
    public void setHour(int dayOfTheWeek, float hoursWorked) {
        String dailyHoursString = decToHex((long) (hoursWorked * TEN));
        if (checkRange(dayOfTheWeek, DAY_LIMIT) == 1 && checkRange((int) 
            (hoursWorked * TEN), (int) (HOURS_LIMIT * TEN)) == 1) {
            String hexString = decToHex(hours);
            // Considering how substring method, the day value has to be - 1.
            int dayToGetHours = getPosition(HEX_HOURS_L_I, dayOfTheWeek) - 1;
            String digitsToAdd = dailyHoursString.length() > 1 
                    ? "" + dailyHoursString 
                    : "0" + dailyHoursString;
            hexString = hexString.substring(0, dayToGetHours) 
                    + digitsToAdd
                    + hexString.substring(dayToGetHours 
                    + 2, hexString.length());
            
            hours = Long.parseLong(hexString, HEX);
        } else {
            throw new IllegalArgumentException("the input value has to be 0 - "
                    + DAY_LIMIT);
        }
    }    
}
