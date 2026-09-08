package A3;
import java.util.*;
public class Bank {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try{
        System.out.print("Enter the User ID: ");
        int id=Integer.parseInt(sc.next());
        System.out.println(id);
        }
        catch(NumberFormatException n)
        {
            System.out.println("Enter a valid ID ");
            main(args);
        }
        sc.close();
    }
    
}
