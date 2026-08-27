package Lab3;
import java.util.*;
public class Person extends Doctor  {
    static Scanner sc=new Scanner(System.in);

    Person det[]=new Person[20];

    int i=0;;

    void InsertDocDetails()
    {
        if (i>=20)
            {
                System.out.println("Max Capacity Reached");
                return;
            }
        Doctor D=new Doctor();
        System.out.print("Enter Doctor name: ");
        D.name=sc.next();

        System.out.print("Enter Doctor Specialization: ");
        D.specialization=sc.next();

        System.out.print("Enter Doctor's Degree: ");
        D.degree=sc.next();

        System.out.print("Enter Doctor's Fee: ");
        D.fee=sc.nextInt();

        System.out.print("Enter Doctor's Years of Experience: ");
        D.years_of_exp=sc.nextInt();

        det[i]=D;
        i=i+1;
        System.out.println("Details inserted");


    }

    void InsertPatientDetails()
    {
        if(i>=20)
            {
                System.out.println("Max Patient Cpacity reached");
                return;
            }
        Patient p=new Patient();
        System.out.print("Enter Patient Name: ");
        p.name=sc.next();

        System.out.print("Enter Patient's Disease: ");
        p.disease=sc.next();

        System.out.print("Enter Doc assigned to patient:  ");
        p.doc_assigned=sc.next();

        System.out.print("Enter Patient Age: ");
        p.age=sc.nextInt();

        det[i]=p;
        i=i+1;



    void DisplayDetails()
    {
        System.out.println("    Press 1 for Doc Details");
        System.out.println("    Press 2 for Patient  Details");
        System.out.println("    Press 3 to Exit");
        System.out.print("      Enter Choice: ");
        int ch=sc.nextInt();
        if (ch==1)
            {
                for (Person p: det)
                {
                    if(p instanceof Doctor)
                        {
                            System.out.println("Name"+p.name);
                            System.out.println("Specialization"+p.specialization);
                            System.out.println("Degree"+p.degree);
                            System.out.println("Fee"+p.fee);
                            System.out.println("Years Of Experience"+p.years_of_exp);
                        }

                }
            }
            else if (ch==2)
                {
                    for (Person p: det)
                        if (p instanceof Patient)
                        {
                                {
                                    System.out.println("Name"+p.name);
                                    System.out.println("Disease"+p.disease);
                                    System.out.println("Doc_Assigned"+p.doc_assigned);
                                    System.out.println("Age"+p.age);
                                }
                        }
                }
            else if(ch==3)
                {
                    return;
                }
    }
    public static void main(String[] args) {
        System.out.println("Press 1 to Insert Doctor Details");
        System.out.println("Press 2 to Insert Patient Details");
        System.out.println("Press 3 to View Details");
        System.out.println("Press 4 to Calculate Bill");
        System.out.println("Press 5 to Exit");
        System.out.print("Enter Choice: ");
        int ch=sc.nextInt();
        
    }
    
}
