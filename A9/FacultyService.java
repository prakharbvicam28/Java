import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface defining the remote method
public interface FacultyService extends Remote {
    String subjectTaught(String facultyName) throws RemoteException;
}

