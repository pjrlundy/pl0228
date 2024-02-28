import java.time.DayOfWeek;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The RentalAgreement class represents a rental agreement for a tool. It includes details 
 * such as the tool rented, rental period, discount, and charges.
 * 
 * Note: Interface Dependency Injection, by using ITool interface instead of a concrete Tool class,
 * the code adheres to the Dependency Inversion Principle, a key aspect of SOLID principles. 
 * This approach makes the code more flexible and easier to maintain, as 
 * it can handle different types of tools that implement the ITool interface.
 */
public class RentalAgreement {

    private ITool tool;  // Using ITool interface
    private int rentalDays;
    private int discountPercent;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public ITool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public int getChargeDays() {
        return chargeDays;
    }

    /**
     * Initializes a new RentalAgreement with the given tool, rental period, discount percentage, 
     * and checkout date. It calculates the due date and charges as part of the initialization process.
     * Parameters:
     *   tool: The tool being rented.
     *   rentalDays: The number of days for which the tool is rented.
     *   discountPercent: The discount percentage applied to the rental.
     *   checkoutDate: The date on which the tool is checked out.
     */
    public RentalAgreement(ITool tool, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental days must be 1 or more.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
        this.dueDate = calculateDueDate();
        calculateCharges();
    }

    /**
     * Calculates and returns the due date for the tool rental.
     */
    private LocalDate calculateDueDate() {
        LocalDate date = checkoutDate;
        for (int i = 0; i < rentalDays; i++) {
            date = date.plusDays(1);
            while (!isChargeableDay(date)) {
                date = date.plusDays(1);
            }
        }
        return date;
    }

    /**
     * Calculates the charge days, pre-discount charge, discount amount, and final charge for the rental.
     */
    private void calculateCharges() {
        LocalDate date = checkoutDate;
        for (int i = 0; i < rentalDays; i++) {
            if (isChargeableDay(date)) {
                chargeDays++;
            }
            date = date.plusDays(1);
        }

        preDiscountCharge = chargeDays * tool.getDailyCharge();
        discountAmount = (preDiscountCharge * discountPercent) / 100.0;
        discountAmount = BigDecimal.valueOf(discountAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        finalCharge = preDiscountCharge - discountAmount;
        finalCharge = BigDecimal.valueOf(finalCharge).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Determines if a given date is chargeable based on the tool's charging policy and 
     * the date's status as a weekend or holiday.
     * 
     * Parameters:
     *  date: local date
     */
    private boolean isChargeableDay(LocalDate date) {
        boolean isWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
        boolean isHoliday = Holiday.isHoliday(date);

        // Check if the tool charges on weekends and holidays
        boolean isWeekendChargeable = tool.isWeekendCharge();
        boolean isHolidayChargeable = tool.isHolidayCharge();

        // Determine if the day is chargeable based on the tool's charging policy
        boolean isChargeable = true;
        if (isWeekend && !isWeekendChargeable) {
            isChargeable = false; // Not chargeable on weekends
        }
        if (isHoliday && !isHolidayChargeable) {
            isChargeable = false; // Not chargeable on holidays
        }

        return isChargeable;
    }

    // Generate agreement details
    public String generateAgreement() {

         // Date formatter for mm/dd/yy format
         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        
         // Currency formatter for $9,999.99 format
         NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
 
         return String.format(
            "Tool code: %s\n" +
            "Tool type: %s\n" +
            "Tool brand: %s\n" +
            "Rental days: %d\n" +
            "Check out date: %s\n" +
            "Due date: %s\n" +
            "Daily rental charge: %s\n" +
            "Charge days: %d\n" +
            "Pre-discount charge: %s\n" +
            "Discount percent: %d%%\n" +
            "Discount amount: %s\n" +
            "Final charge: %s\n\n",
            tool.getToolCode(), tool.getToolType(), tool.getBrand(), rentalDays,
            checkoutDate.format(dateFormatter), dueDate.format(dateFormatter), 
            currencyFormatter.format(tool.getDailyCharge()),
            chargeDays, currencyFormatter.format(preDiscountCharge), 
            discountPercent, currencyFormatter.format(discountAmount), 
            currencyFormatter.format(finalCharge)
        );
    }

    // Print agreement to console
    public void printAgreement() {
        System.out.println(generateAgreement());
    }

    
    
    

   

}






