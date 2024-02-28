public class Tool implements ITool{
    private String toolCode;
    private String toolType;
    private String brand;
    private ToolAttributes.ToolTypeAttributes attributes;

    // Constructor
    public Tool(String toolCode, String toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        switch (toolType) {
            case "Ladder":
                this.attributes = ToolAttributes.LADDER;
                break;
            case "Chainsaw":
                this.attributes = ToolAttributes.CHAINSAW;
                break;
            case "Jackhammer":
                this.attributes = ToolAttributes.JACKHAMMER;
                break;
            default:
                throw new IllegalArgumentException("Invalid tool type");
        }
    }

    // Getters
    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public double getDailyCharge() {
        return attributes.getDailyCharge();
    }

    public boolean isWeekdayCharge() {
        return attributes.isWeekdayCharge();
    }

    public boolean isWeekendCharge() {
        return attributes.isWeekendCharge();
    }

    public boolean isHolidayCharge() {
        return attributes.isHolidayCharge();
    }

    @Override
    public String toString() {
        return "Tool{" +
               "toolCode='" + toolCode + '\'' +
               ", toolType='" + toolType + '\'' +
               ", brand='" + brand + '\'' +
               ", dailyCharge=" + getDailyCharge() +
               ", weekdayCharge=" + isWeekdayCharge() +
               ", weekendCharge=" + isWeekendCharge() +
               ", holidayCharge=" + isHolidayCharge() +
               '}';
    }
}
