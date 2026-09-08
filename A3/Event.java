package A3;
import java.util.*;
public class Event {
    Scanner sc=new Scanner(System.in);
    String events[] = { "Coding","Debate", "Quiz", "Dance", "Photography"};

    String pt[][]=new String[10][3];
    int index=0;
    int sum=0;
    
    void Register()
        {
            if (index>9)
                {
                    System.out.println("Max Participants reached");
                    return;
                }
            System.out.print("Enter Participant Name: ");
            String n=sc.next();

            System.out.println("Event name: ");
            String e=sc.next();

            System.out.println("Score: ");
            String s=sc.next();
            sum=sum+Integer.parseInt(s);

            String arr[]={n,e,s};
            pt[index]=arr;
            index++;
            System.out.println("Registered !!!!");

        }
    void HighScore()
    {
        int mx=Integer.parseInt(pt[0][2]);
        for (int i=0;i<pt.length;i++)
            {
                if (Integer.parseInt(pt[i][2])>mx)
                {
                    mx=Integer.parseInt(pt[i][2]);
                }
            }
        System.out.println("Highest Score: "+mx);
    }
    void LowScore()
    {
        int mn=Integer.parseInt(pt[0][2]);
        for (int i=0;i<pt.length;i++)
            {
                mn=Integer.parseInt(pt[i][2]);
            }
        System.out.println("Lowest Score: "+mn);
    }

    void Average()
    {
        int avg=sum/pt.length;
        System.out.println("Average Score is: "+avg);
    }
    public static void main(String[] args) {
        Event ev=new Event();
        boolean flag=true;
        while (flag)
        {
        System.out.println("1 --> Register a participant");
        System.out.println("2 --> Highest Score ");
        System.out.println("3 --> Lowest Score");
        System.out.println("4 --> Average Score");
        System.out.println("5 --> Exit ");
        System.out.print("Enter choice");
        int ch=ev.sc.nextInt();
        switch (ch) {
            case 1:
                ev.Register();
                break;
            case 2:
                ev.HighScore();
                break;
            case 3:
                ev.LowScore();
                break;
            case 4:
                ev.Average();
                break;
            case 5:
                flag=false;
        }
    }
    }
    
}
