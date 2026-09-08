package A3;
import java.util.*;
public class Event {
    Scanner sc=new Scanner(System.in);
    String events[] = { "Coding","Debate", "Quiz", "Dance", "Photography"};
    String venues[] = {"Auditorium","Seminar Hall", "Room 101", "Room 102", "Room 103"};
    String pt[][]=new String[10][3];
    int index=0;
    int sum=0;
    int pt_c=0;
    Random rd=new Random();
    
    void Register()
        {
            if (index>9)
                {
                    System.out.println("Max Participants reached");
                    return;
                }
            System.out.print("Enter Participant Name: ");
            String n=sc.next();

            System.out.print("Event name ('Coding','Debate', 'Quiz, 'Dance', 'Photography'): ");
            String e=sc.next();


            System.out.print("Score: ");
            String s=sc.next();
            sum=sum+Integer.parseInt(s);

            String arr[]={n,e,s};
            pt[index]=arr;
            index++;
            pt_c++;
            System.out.println("Registered !!!!");

        }
    void HighScore()
    {
        int mx=Integer.parseInt(pt[0][2]);
        int i=0;
        while (pt[i][2]!=null && i<10)
            {
                if (Integer.parseInt(pt[i][2])>mx)
                {
                    mx=Integer.parseInt(pt[i][2]);
                }
                i++;
            }
        System.out.println("Highest Score: "+mx);
    }
    void LowScore()
    {
        int mn=Integer.parseInt(pt[0][2]);
        int i=0;
        while (pt[i][2]!=null && i<10)
            {
                if (Integer.parseInt(pt[i][2])<mn)
                    {
                        mn=Integer.parseInt(pt[i][2]);
                    }
                i++;
            }
        System.out.println("Lowest Score: "+mn);
    }

    void ShowParticipants()
    {
        int x=0;
        while (x<pt_c)
            {
                System.out.println(pt[x][0]+" "+pt[x][1]+" "+pt[x][2]);
                x++;
            }
    }
    void Average()
    {
        int avg=sum/pt_c;
        System.out.println("Average Score is: "+avg);
    }

    void VenueAllocate()
    {
        
        System.out.print("Enter the Event to allocate venue: ");
        String event=sc.next();
        int prt=0;
        for (String[] a: pt)
            {
                if (a[1]==event)
                    {
                        prt++ ;
                    }

            }
        if (prt>=3)
            {
                int v_id=rd.nextInt(venues.length);
                System.out.println("Venue Allocated: "+venues[v_id]);
            }
        else{
            System.out.println("Insufficicnet Participants :( ");
        }
    }
    public static void main(String[] args) {
        Event ev=new Event();
        boolean flag=true;
        while (flag)
        {
            System.out.println("1 --> Register a participant");
            System.out.println("2 --> Register a participant");
            System.out.println("3 --> Highest Score ");
            System.out.println("4 --> Lowest Score");
            System.out.println("5 --> Average Score");
            System.out.println("6 --> Venue Allocation ");
            System.out.println("7 --> Exit ");
            System.out.print("Enter choice");
            int ch=ev.sc.nextInt();
            switch (ch) {
            case 1:
                ev.Register();
                break;
            case 2:
                ev.ShowParticipants();
                break;
            case 3:
                ev.HighScore();
                break;
            case 4:
                ev.LowScore();
                break;
            case 5:
                ev.Average();
                break;
            case 6:
                ev.VenueAllocate();
                break;
            case 7:
                flag=false;
                break;
            default:
                System.out.println("Invalid choice");
            }
        }
    }
    
}
