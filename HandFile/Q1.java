import java.util.Scanner;

class Student
{
    String n;
    int pct;

    Student(String n,int p)
    {
        this.n=n;
        this.pct=p;
    }
}
public class Q1 {
    static int i=0;
    static Student names[]=new Student[10];

    static  void insert(String n, int p)
    {
        if (i<10)
            {
                names[i]=new Student(n,p);
                i++;
            }
        else
            {
                System.out.println("Max Studnet Limit Reached !");
            }

    }

    static void display_stu()
    {
        System.out.println("\n--- List of Student ---");
        for (int j=0;j<i;j++)
            {
                System.out.println("\n name:"+names[j].n+"\tPct: "+names[j].pct);
            }
    }
    static void display_stu75()
    {
        System.out.println("\n--- List of Student with >=75% ---");
        for (int j=0;j<i;j++)
            {
                if (names[j].pct>=75)
                System.out.println("\n name:"+names[j].n+"\tPct: "+names[j].pct);
            }
    }

    static void dispaly_stu_under75()
    {
        System.out.println("\n--- List of Student with <75% ---");
        for (int j=0;j<i;j++)
            {
                if (names[j].pct<75)
                System.out.println("\n name:"+names[j].n+"\tPct: "+names[j].pct);
            }
    }

    static void average_attendance(){
        int total=0;
        for (int j=0;j<i;j++)
            {
                total+=names[j].pct;
            }
        System.out.println("\n Average Percentage: "+total/i);
    }
    

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        for (int c=0; c<10;c++)
            {
                System.out.print("Enter name : ");
                String n=sc.next();
                System.out.print("Enter percentage: ");
                int pct=sc.nextInt();
                insert(n,pct);
            } 
        display_stu();
        display_stu75();
        dispaly_stu_under75();
        average_attendance();
    }
}