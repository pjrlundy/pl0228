import java.time.LocalDate;
import java.time.Month;

public class Holiday {

    /**
     * Checks if the given date is a holiday. Currently, it checks for
     * Independence Day and Labor Day.
     *
     * @param date The date to check for a holiday.
     * @return true if the date is a holiday, false otherwise.
     */
    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    /**
     * Adjusts the date for Independence Day if it falls on a weekend. 
     * Independence Day is observed on the nearest weekday if it falls 
     * on a Saturday or Sunday.
     *
     * @param date The date to check for adjustment.
     * @return The adjusted date if original date falls on a weekend, 
     *         otherwise the original date.
     */
    public static LocalDate adjustForWeekendHoliday(LocalDate date) {
        if (isIndependenceDay(date)) {
            if (date.getDayOfWeek().getValue() == 6) { // Saturday
                return date.minusDays(1); // Observed on Friday
            } else if (date.getDayOfWeek().getValue() == 7) { // Sunday
                return date.plusDays(1); // Observed on Monday
            }
        }
        return date;
    }

    /**
     * Checks if the given date is Independence Day or its observed day.
     * Independence Day is observed on July 4th, or on the nearest weekday
     * if it falls on a weekend.
     *
     * @param date The date to check for Independence Day.
     * @return true if the date is Independence Day or its observed day, 
     *         false otherwise.
     */
    private static boolean isIndependenceDay(LocalDate date) {
        if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4) {
            return true;
        }
        // Check for observed days
        LocalDate july4 = LocalDate.of(date.getYear(), Month.JULY, 4);
        if (july4.getDayOfWeek().getValue() == 6 && date.equals(july4.minusDays(1))) {
            return true; // Observed on Friday
        }
        if (july4.getDayOfWeek().getValue() == 7 && date.equals(july4.plusDays(1))) {
            return true; // Observed on Monday
        }
        return false;
    }

    /**
     * Checks if the given date is Labor Day. Labor Day is observed on the
     * first Monday of September.
     *
     * @param date The date to check for Labor Day.
     * @return true if the date is Labor Day, false otherwise.
     */
    private static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER && 
               date.getDayOfWeek().getValue() == 1 && 
               date.getDayOfMonth() <= 7;
    }

    

}

