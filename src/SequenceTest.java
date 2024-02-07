import java.util.Scanner;

/**
 * This class tests the Sequence class.
 * @author Rylan Meilutis
 * @author Vassily Dudkin
 */
public class SequenceTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoubleArraySeq sequence = new DoubleArraySeq(10);
        int choice = 0;

        while (choice != 12) {
            System.out.println("Sequence Test Menu:");
            System.out.println("1. Print the sequence");
            System.out.println("2. Report the capacity of the sequence");
            System.out.println("3. Set the current element location");
            System.out.println("4. Add a number to the front of the sequence");
            System.out.println("5. Add a number to the end of the sequence");
            System.out.println("6. Add a number before the current element");
            System.out.println("7. Add a number after the current element");
            System.out.println("8. Delete the first number from the sequence");
            System.out.println("9. Delete a number at a location");
            System.out.println("10. Display the value at a certain location");
            System.out.println("11. Display the last element in the sequence");
            System.out.println("12. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.println("The sequence is: " + sequence);
                        break;
                    case 2:
                        System.out.println("The capacity of the sequence is: " + sequence.getCapacity());
                        break;
                    case 3:
                        System.out.print("Enter the location to set the current element to: ");
                        sequence.setCurrent(scanner.nextInt());
                        break;
                    case 4:
                        System.out.print("Enter the number to add to the front of the sequence: ");
                        sequence.addFront(scanner.nextDouble());
                        break;
                    case 5:
                        System.out.print("Enter the number to add to the end of the sequence: ");
                        sequence.addAfter(scanner.nextDouble());
                        break;
                    case 6:
                        System.out.print("Enter the number to add before the current element: ");
                        sequence.addBefore(scanner.nextDouble());
                        break;
                    case 7:
                        System.out.print("Enter the number to add after the current element: ");
                        sequence.addAfter(scanner.nextDouble());
                        break;
                    case 8:
                        sequence.removeFront();
                        break;
                    case 9:
                        System.out.print("Enter the location to delete a number: ");
                        sequence.setCurrent(scanner.nextInt());
                        sequence.removeCurrent();
                        break;
                    case 10:
                        System.out.print("Enter the location to display the value: ");
                        int location = scanner.nextInt();
                        sequence.setCurrent(location);
                        System.out.println("The value at location " + location + " is: " + sequence.getCurrent());
                        break;
                    case 11:
                        sequence.setCurrent(sequence.size());
                        System.out.println("The last element in the sequence is: " + sequence.getCurrent());
                        break;
                    case 12:
                        System.out.println("Goodbye!");

                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
