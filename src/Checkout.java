import java.time.LocalDate;

public class Checkout {

    public RentalAgreement checkoutTool(Tool tool, int rentalDayCount, int discountPercent, LocalDate checkoutDate) throws IllegalArgumentException {
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        return new RentalAgreement(tool, rentalDayCount, discountPercent, checkoutDate);
    }
}
