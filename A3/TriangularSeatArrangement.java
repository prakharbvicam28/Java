package A3;

public class TriangularSeatArrangement {
    void TriSeat(int x)
    {
        for (int i=1;i<=x;i++)
            {
                for (int j=i;j<x;j++)
                    {
                        System.out.print(" ");
                    }
                for (int j=1;j<=i;j++)
                    {
                        System.out.print("[ ]");
                    }
                System.out.println();
            }
    }
}
