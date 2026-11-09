import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FacultyClient {

    public static void main(String[] args) {
        try {
            // Locate the RMI registry on localhost:1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtain remote reference to the server object
            FacultyService service = (FacultyService) registry.lookup("FacultyService");

            Scanner scanner = new Scanner(System.in);
            System.out.println("=================================================");
            System.out.println("Connected to Faculty RMI Server!");
            System.out.println("Type faculty name to query the subject taught.");
            System.out.println("Type 'exit' to quit.");
            System.out.println("=================================================");

            while (true) {
                System.out.print("\nEnter Faculty Name: ");
                String facultyName = scanner.nextLine().trim();

                if (facultyName.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting Faculty Query Client. Goodbye!");
                    break;
                }

                if (facultyName.isEmpty()) {
                    System.out.println("Please enter a valid faculty name.");
                    continue;
                }

                // Invoke remote method
                String response = service.subjectTaught(facultyName);
                System.out.println("Result -> " + response);
            }

            scanner.close();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

