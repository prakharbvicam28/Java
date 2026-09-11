package A3;
import java.util.*;
public class Event extends SeatAllocate {
    Scanner sc=new Scanner(System.in);
    String events[] = { "Coding","Debate", "Quiz", "Dance", "Photography"};
    String venues[] = {"Auditorium","Seminar Hall", "Room 101", "Room 102", "Room 103"};
    String pt[][]=new String[30][4];
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
            
            System.out.println();
            System.out.println("    SEATS ARRANGEMENT   ");
            System.out.println("1  2  3  4  5");
            System.out.println("6  7  8  9  10");
            System.out.println("11 12 13 14 15");
            System.out.println("16 17 18 19 20");
            System.out.println("21 22 23 24 25");

            boolean chk=true;
            String st_st=null;
            while (chk)
                {
                    System.out.print("Seelct seat  (1-50):  ");
                    String seat=sc.next();
                
                    int st_no=Integer.parseInt(seat);
                    int r=(st_no-1)/5;
                    int c=(st_no-1)%5;
                    String res=allocate(r,c);
                    
                    // Fixed: Always use .equals() for Strings, NOT ==
                    // Fixed: The string from SeatAllocate was "Seat Taken :(", not "Already Taken :("
                    if (res.equals("No Seats Left :("))
                        {
                            st_st=null;
                            System.out.println(res);
                            break;
                        }
                    else if (res.equals("Seat Taken :("))
                        {
                            System.out.println(res);
                        }
                    else
                        {
                            st_st=seat;
                            chk=false;
                            System.out.println(res); // Print "Booked ! :)"
                        }
                }
            


            String arr[]={n,e,s,st_st};
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
                System.out.println(pt[x][0]+" "+pt[x][1]+" "+pt[x][2]+" "+pt[x][3]);
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
            System.out.println();
            System.out.println("1 --> Register a participant");
            System.out.println("2 --> Show Participants ");
            System.out.println("3 --> Highest Score ");
            System.out.println("4 --> Lowest Score");
            System.out.println("5 --> Average Score");
            System.out.println("6 --> Venue Allocation ");
            System.out.println("7 --> Check Occupied Seats ");
            System.out.println("8 --> Check Available Seats ");
            System.out.println("9 --> Check MAX Occupied Row/Column ");
            System.out.println("10--> Show seat status ");
            System.out.println("11 --> Triangular Seat Arrangement ");
            System.out.println("12--> Exit ");
            System.out.print("Enter choice:  ");
            int ch=ev.sc.nextInt();
            System.out.println();
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
                ev.CountSeats(1);
                break;
            case 8:
                ev.CountSeats(0);
                break;
            case 9:
                ev.MaxOccupied(1);
                ev.MaxOccupied(0);
                break;
            case 10:
                ev.SeatStatus();
                break;
            case 11:
                System.out.print("Enter rows: ");
                int r=ev.sc.nextInt();
                ev.TriSeat(r);
                break;
            case 12:
                flag=false;
                break;
            default:
                System.out.println("Invalid choice");
            }
        }
    }
    
}
