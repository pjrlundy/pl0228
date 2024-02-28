public interface ITool {
    // Define method signatures that all tools must implement
    String getToolCode();
    String getToolType();
    String getBrand();
    double getDailyCharge();
    boolean isWeekdayCharge();
    boolean isWeekendCharge();
    boolean isHolidayCharge();
}