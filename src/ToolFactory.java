/**
 * The ToolFactory class is used to create Tool objects.
 * This class demonstrates the Factory Design Pattern, providing a way to encapsulate
 * the instantiation logic of Tool objects.
 *
 * Using a factory for object creation has several advantages:
 * - It promotes loose coupling between classes.
 * - It enhances the scalability of the application by centralizing the creation logic,
 *   making it easier to manage changes in the object creation process.
 */
public class ToolFactory {

    /**
     * Creates a new Tool object.
     * 
     * @param code The code of the tool.
     * @param type The type of the tool.
     * @param brand The brand of the tool.
     * @return A new Tool object.
     */
    public Tool createTool(String code, String type, String brand) {
        return new Tool(code, type, brand);
    }
}
