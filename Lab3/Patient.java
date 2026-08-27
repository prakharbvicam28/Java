package Lab3;
import java.util.*;
interface Billable
{
    public float calculateBill();
}


public class Patient implements Billable{
    Scanner sc=new Scanner(System.in);
    String name, disease,doc_assigned;
    int age;




    public float calculateBill()
    {
        float df,mdf,sf;
        System.out.print("Enter Docter's Fee: ");
        df=sc.nextFloat();
        System.out.print("Enter Medicine cost: ");
        mdf=sc.nextFloat();
        System.out.print("Enter Medical Scan's/Test Fee: ");
        sf=sc.nextFloat();
        float bill=df+mdf+sf;
        return bill;
    }
}
