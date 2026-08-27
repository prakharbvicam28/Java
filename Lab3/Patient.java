package Lab3;
interface Billable {
    public float calculateBill();
}

public class Patient extends Person implements Billable {
    void InsertPatientDetails() {
        if (i >= 20) {
            System.out.println("Max Patient Cpacity reached");
            return;
        }
        Patient p = new Patient();
        System.out.print("Enter Patient Name: ");
        p.pat_name = sc.next();

        System.out.print("Enter Patient's Disease: ");
        p.disease = sc.next();

        System.out.print("Enter Doc assigned to patient:  ");
        p.doc_assigned = sc.next();

        System.out.print("Enter Patient Age: ");
        p.age = sc.nextInt();

        det[i] = p;
        i = i + 1;

    }

    public float calculateBill() {
        float df, mdf, sf;
        System.out.print("Enter Docter's Fee: ");
        df = sc.nextFloat();
        System.out.print("Enter Medicine cost: ");
        mdf = sc.nextFloat();
        System.out.print("Enter Medical Scan's/Test Fee: ");
        sf = sc.nextFloat();
        float bill = df + mdf + sf;
        return bill;
    }
}
