/**
 * The ToolAttributes class defines the attributes for different types of tools.
 * It contains predefined constants for various tool types, each with specific charging rules and rates.
 */
public class ToolAttributes {
    public static final ToolTypeAttributes LADDER = new ToolTypeAttributes(1.99, true, true, false);
    public static final ToolTypeAttributes CHAINSAW = new ToolTypeAttributes(1.49, true, false, true);
    public static final ToolTypeAttributes JACKHAMMER = new ToolTypeAttributes(2.99, true, false, false);

    /**
     * The ToolTypeAttributes nested class defines the specific attributes for a tool type.
     * It includes the daily charge rate and booleans to indicate if charges apply on weekdays, weekends, and holidays.
     */
    public static class ToolTypeAttributes {
        private final double dailyCharge;
        private final boolean weekdayCharge;
        private final boolean weekendCharge;
        private final boolean holidayCharge;

        /**
         * Constructor for ToolTypeAttributes.
         * 
         * @param dailyCharge The daily charge rate for the tool.
         * @param weekdayCharge Whether the tool is chargeable on weekdays.
         * @param weekendCharge Whether the tool is chargeable on weekends.
         * @param holidayCharge Whether the tool is chargeable on holidays.
         */
        public ToolTypeAttributes(double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
            this.dailyCharge = dailyCharge;
            this.weekdayCharge = weekdayCharge;
            this.weekendCharge = weekendCharge;
            this.holidayCharge = holidayCharge;
        }

        public double getDailyCharge() {
            return dailyCharge;
        }

        public boolean isWeekdayCharge() {
            return weekdayCharge;
        }

        public boolean isWeekendCharge() {
            return weekendCharge;
        }

        public boolean isHolidayCharge() {
            return holidayCharge;
        }
    }
}
