/**
 * The ToolRentalApplication class is responsible for running the tool rental application.
 * It demonstrates the use of Dependency Injection and Factory Design Pattern.
 *
 * Dependency Injection (DI) is utilized here by injecting the ToolFactory dependency
 * into the ToolRentalApplication. This enhances the modularity and testability of the application,
 * as it allows the ToolFactory to be easily swapped or mocked during testing.
 *
 * The ToolFactory is used to create Tool instances, thus separating the responsibility
 * of object creation from the business logic, adhering to the Single Responsibility Principle.
 */
import java.time.LocalDate;

public class ToolRentalApplication {

    private ToolFactory toolFactory;

     /**
     * Constructor for ToolRentalApplication.
     * 
     * @param toolFactory The factory used for creating Tool objects.
     */
    public ToolRentalApplication(ToolFactory toolFactory) {
        this.toolFactory = toolFactory;
    }

    /**
     * The main method that runs the application.
     * It demonstrates creating tools using the factory and simulates tool rentals.
     */
    public void run() {
        // Use the factory to create tools
        ITool ladder = toolFactory.createTool("LADW", "Ladder", "Werner");
        ITool chainsaw = toolFactory.createTool("CHNS", "Chainsaw", "Stihl");
        ITool jackhammerDewalt = toolFactory.createTool("JAKD", "Jackhammer", "DeWalt");
        ITool jackhammerRidgid = toolFactory.createTool("JAKR", "Jackhammer", "Ridgid");

        // Simulate checkouts
        try {
            RentalAgreement rental1 = new RentalAgreement(ladder, 5, 10, LocalDate.of(2024, 3, 15));
            rental1.printAgreement();

            RentalAgreement rental2 = new RentalAgreement(chainsaw, 3, 25, LocalDate.of(2024, 7, 2));
            rental2.printAgreement();

            RentalAgreement rental3 = new RentalAgreement(jackhammerDewalt, 6, 0, LocalDate.of(2024, 9, 3));
            rental3.printAgreement();

            RentalAgreement rental4 = new RentalAgreement(jackhammerRidgid, 9, 50, LocalDate.of(2024, 12, 24));
            rental4.printAgreement();
        } catch (IllegalArgumentException e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ToolFactory toolFactory = new ToolFactory();
        ToolRentalApplication app = new ToolRentalApplication(toolFactory);
        app.run();
    }
}
