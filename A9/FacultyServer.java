import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class FacultyServer extends UnicastRemoteObject implements FacultyService {

    // Server maintains a HashMap of Faculty Name -> Course Name
    private Map<String, String> facultyCourseMap;

    public FacultyServer() throws RemoteException {
        super();
        facultyCourseMap = new HashMap<>();

        // Pre-populating Faculty and Course records
        facultyCourseMap.put("Dr. Ritika Wason", "Object Oriented Programming using Java");
        facultyCourseMap.put("Dr. M. N. Hoda", "Software Engineering");
        facultyCourseMap.put("Prof. Parul Arora", "Discrete Structures");
        facultyCourseMap.put("Prof. Arpita Nagpal", "Computer Networks");
        facultyCourseMap.put("Prof. Sunil Pratap Singh", "Operating Systems");
    }

    // Remote method implementation
    @Override
    public String subjectTaught(String facultyName) throws RemoteException {
        System.out.println("[Server] Received query for faculty: " + facultyName);

        // Case-insensitive lookup in HashMap
        for (Map.Entry<String, String> entry : facultyCourseMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(facultyName.trim())) {
                return "Course/Subject: " + entry.getValue() + " (Faculty: " + entry.getKey() + ")";
            }
        }

        return "No subject found for faculty: " + facultyName;
    }

    public static void main(String[] args) {
        try {
            // Create RMI registry on default port 1099
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("RMI registry created on port 1099.");
            } catch (Exception e) {
                // If registry is already running, get existing registry
                registry = LocateRegistry.getRegistry(1099);
                System.out.println("Using existing RMI registry on port 1099.");
            }

            // Create remote object instance
            FacultyServer serverObj = new FacultyServer();

            // Bind remote object to registry
            registry.rebind("FacultyService", serverObj);

            System.out.println("=================================================");
            System.out.println("Faculty RMI Server is running and waiting for clients...");
            System.out.println("=================================================");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

