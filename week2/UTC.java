import java.util.Calendar;

public class UTC {

    // constants for raw time conversion
    // these must be static because they will be called from a "static context" => main
    static final int MILLISECONDS = 1;
    static final int MIL_PER_SEC    = MILLISECONDS * 1000;
    static final int MIL_PER_MIN    = MIL_PER_SEC  * 60;
    static final int MIL_PER_HOUR   = MIL_PER_MIN  * 60;
    static final int MIL_PER_DAY    = MIL_PER_HOUR * 24;

    public static void main(String[] args) {
        
		// FORMAT ONE: write Calendar code here. Research API for details
        
        // get the current UNIX timestamp in milliseconds.
        long currentMillis = System.currentTimeMillis();
        System.out.println("UNIX Timestamp (milliseconds): " + currentMillis);
        
        // create a calendar instance and set its time using the current timestamp.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMillis);
        
        // get the current hour, minute, and second.
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        
        // format the time as HH:MM:SS
        String timeUsingCalendar = String.format("%02d:%02d:%02d", hour, minute, second);
        System.out.println("Current local time: " + timeUsingCalendar);
        
        // FORMAT TWO: write raw time conversion code here. Research API for details
        // get the command line argument, convert to an integer, and then
		// perform the math.

        int offsetHours = 0;
        if (args.length > 0) {
            try {
                offsetHours = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("invalid offset argument. Using 0 as default.");
            }
        }
        
        // convert the time zone offset from hours to milliseconds.
        long offsetMillis = offsetHours * (long) MIL_PER_HOUR;
        
        // adjust to get the local time.
        long localMillis = currentMillis + offsetMillis;
        
        // calculate the number of milliseconds that have gpne by today
        long millisInDay = ((localMillis % MIL_PER_DAY) + MIL_PER_DAY) % MIL_PER_DAY;
        
        // calculate hours, minutes, and seconds from the milliseconds into the day.
        long calcHour = millisInDay / MIL_PER_HOUR;
        long remainderAfterHours = millisInDay % MIL_PER_HOUR;
        long calcMinute = remainderAfterHours / MIL_PER_MIN;
        long remainderAfterMinutes = remainderAfterHours % MIL_PER_MIN;
        long calcSecond = remainderAfterMinutes / MIL_PER_SEC;
        
        String timeUsingMath = String.format("%02d:%02d:%02d", calcHour, calcMinute, calcSecond);
        System.out.println("Current GMT time: " + timeUsingMath);
    }
}
