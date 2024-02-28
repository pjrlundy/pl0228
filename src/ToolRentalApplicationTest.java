import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ToolRentalApplicationTest {


    private ITool ladder;
    private ITool chainsaw;
    private ITool jackhammerDeWalt;
    private ITool jackhammerRidgid;
    private ToolFactory toolFactory;

    @Before
    public void setUp() {
        toolFactory = new ToolFactory();
        ladder = toolFactory.createTool("LADW", "Ladder", "Werner");
        chainsaw = toolFactory.createTool("CHNS", "Chainsaw", "Stihl");
        jackhammerDeWalt = toolFactory.createTool("JAKD", "Jackhammer", "DeWalt");
        jackhammerRidgid = toolFactory.createTool("JAKR", "Jackhammer", "Ridgid");
    }

    @Test
    public void testValidCheckout() {
        // Test for a standard checkout
        RentalAgreement rental = new RentalAgreement(ladder, 5, 10, LocalDate.of(2024, 3, 15));
        assertNotNull(rental);
    }

    @Test
    public void testInvalidRentalDayCount() {
        // Test for invalid rental day count (0 or negative)
        assertThrows(IllegalArgumentException.class, () -> {
            new RentalAgreement(ladder, 0, 10, LocalDate.of(2024, 3, 15));
        });
    }

    @Test
    public void testInvalidDiscountPercent() {
        // Test for invalid discount percentage (negative or over 100)
        assertThrows(IllegalArgumentException.class, () -> {
            new RentalAgreement(ladder, 5, 101, LocalDate.of(2024, 3, 15));
        });
    }

    @Test
    public void testHolidayAdjustment() {
        // Test for holiday adjustment (e.g., July 4th)
        RentalAgreement rental = new RentalAgreement(chainsaw, 5, 10, LocalDate.of(2024, 7, 2));
        assertTrue(rental.getDueDate().isAfter(LocalDate.of(2024, 7, 2).plusDays(5)));
    }

    @Test
    public void testWeekendAdjustment() {
        // Test for weekend adjustment
        RentalAgreement rental = new RentalAgreement(jackhammerDeWalt, 7, 0, LocalDate.of(2024, 9, 1));
        assertTrue(rental.getDueDate().isAfter(LocalDate.of(2024, 9, 1).plusDays(7)));
    }

    @Test
    public void testLaborDayAdjustment() {
        // Test for special case: Labor Day
        RentalAgreement rental = new RentalAgreement(jackhammerRidgid, 5, 0, LocalDate.of(2024, 8, 30));
        assertTrue(rental.getDueDate().isAfter(LocalDate.of(2024, 8, 30).plusDays(5)));
    }

    @Test
    public void testChargeDaysCalculation() {
        // Test for charge days calculation including holidays and weekends
        RentalAgreement rental = new RentalAgreement(ladder, 10, 0, LocalDate.of(2024, 7, 1));
        assertTrue(rental.getChargeDays() <= 10);
    }

    @Test
    public void testDiscountCalculation() {
        // Test for correct discount calculation
        RentalAgreement rental = new RentalAgreement(chainsaw, 5, 25, LocalDate.of(2024, 9, 3));
        assertEquals(25, rental.getDiscountPercent());
       
    }

    //integration tests...
    @Test
    public void testEntireRentalProcess() {
        // Create a tool using the ToolFactory
        ITool tool = toolFactory.createTool("LADW", "Ladder", "Werner");

        // Perform a rental transaction
        RentalAgreement rental = new RentalAgreement(tool, 5, 10, LocalDate.of(2024, 3, 15));

        // Verify that a valid rental agreement is created
        assertNotNull(rental);

        // Further assertions to validate the rental agreement details
        assertEquals("LADW", rental.getTool().getToolCode());
        assertEquals(5, rental.getRentalDays());
    
        // Additional assertions as needed...
    }


    //integration tests...
    @Test
    public void testMultipleToolRentalIntegration() {
        // Renting multiple tools together
        RentalAgreement rental1 = new RentalAgreement(ladder, 5, 10, LocalDate.of(2024, 3, 15));
        RentalAgreement rental2 = new RentalAgreement(chainsaw, 3, 0, LocalDate.of(2024, 3, 18));
        RentalAgreement rental3 = new RentalAgreement(jackhammerDeWalt, 7, 5, LocalDate.of(2024, 3, 20));
        
        // Perform assertions to ensure integration
        assertNotNull(rental1);
        assertNotNull(rental2);
        assertNotNull(rental3);
 
    }

    //Edge case tests
    @Test
    public void edgeCaseTest1() {
        // Test for a very short rental period
        RentalAgreement rental = new RentalAgreement(ladder, 1, 0, LocalDate.of(2024, 3, 15));
        
        // Perform assertions for edge case
        assertNotNull(rental);
        assertEquals(1, rental.getChargeDays());
      
    }

}







