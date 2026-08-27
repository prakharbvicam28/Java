package Lab3;

public class Doctor extends Person {
     void InsertDocDetails() {
          if (i >= 20) {
               System.out.println("Max Capacity Reached");
               return;
          }
          Doctor D = new Doctor();
          System.out.print("Enter Doctor name: ");
          D.doc_name = sc.next();

          System.out.print("Enter Doctor Specialization: ");
          D.specialization = sc.next();

          System.out.print("Enter Doctor's Degree: ");
          D.degree = sc.next();

          System.out.print("Enter Doctor's Fee: ");
          D.fee = sc.nextInt();

          System.out.print("Enter Doctor's Years of Experience: ");
          D.years_of_exp = sc.nextInt();

          det[i] = D;
          i = i + 1;
          System.out.println("Details inserted");

     }
}
