//By Rylan Meilutis

import java.util.Scanner;

public class SequenceTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:
                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    case 11:

                        break;
                    case 12:

                        break;
                    default:

                        break;
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
