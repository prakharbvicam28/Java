// package A3;

// import java.util.Scanner;

// class Account {
//     int bal=1000;

//     void withdraw(int amt)
//     {
//         if (amt<=bal)
//             {
//                 bal=bal-amt;
//                 System.out.println("Current balance: "+bal);
//             }
//         else{
//             System.out.println("Insufficient balance !");
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc=new Scanner(System.in);
//         Account acc=new Account();
//         System.out.print("Enter amount to withdraw: ");
//         int a=sc.nextInt();
//         acc.withdraw(a);
//         sc.close();
        
//     }
// }

package A3;
import java.util.*;

class InsufficientBalanceException extends Exception
{
    @Override
    public String getMessage() {
        return "Insufficient Balance !";
    }
}
public class Account
{
    int bal=1000;

    void withdraw(int amt) throws InsufficientBalanceException
    {
        if (amt<=bal)
            {
                bal=bal-amt;
                System.out.println("Current balance: "+bal);
            }
        else{
            throw new InsufficientBalanceException();
            //
        }
    }
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        Account acc=new Account();
        try{
            
            System.out.print("Enter amount to withdraw: ");
            int a=sc.nextInt();
            acc.withdraw(a);
        }
        catch(InsufficientBalanceException io)
        {
            System.out.println(io.getMessage());
        }
        
        sc.close();
    }
}