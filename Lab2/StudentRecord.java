package Lab2;
import java.util.*;
class Student
{
    String name,course;
    int id,marks;
}
public class StudentRecord extends Student{
    static Student arr[]=new Student[10];
    static Scanner sc=new Scanner(System.in);
    static int index=0;
    public static void main(String[] args)
    {
        int ch;
        do
        {
            System.out.println("1 to insert student details: ");
            System.out.println("2 to Fetch Studnet details");
            System.out.println("3 to exit");
            System.out.print("Enter Choice ");
            // System.out.println();
            ch=sc.nextInt();
            if (ch==1)
                {
                    InsertDetails();
                }
            else if (ch==2)
                {
                    FetchDetails();
                }
            else
                {
                    break;
                }
        }while(ch!=3);

    }

    public static void InsertDetails()
    {
        if (index==9)
            {
                System.out.println("Max Students Capacity reached");
                return;
            }
        Student st=new Student();
        System.out.print("Enter Name: ");
        st.name=sc.next();
        System.out.println();

        System.out.print("Enter ID: ");
        st.id=sc.nextInt();
        System.out.println();

        System.out.print("Enter Course: ");
        st.course=sc.next();
        System.out.println();

        System.out.print("Enter marks: ");
        st.marks=sc.nextInt();
        System.out.println();

        arr[index]=st;
        index=index+1;
    }

    public static void FetchDetails()
    {
        if (arr[0]==null)
            {
                System.out.println("No Students");
                return;
            }
        for (int i=0;i<index;i++)
            {
                System.out.println("ID: "+arr[i].id+"| Name: "+arr[i].name+"| Course: "+arr[i].course+"| Marks: "+arr[i].marks);

            }
    }

}