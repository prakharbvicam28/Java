package A3;

public class SeatAllocate extends TriangularSeatArrangement {
    int seat[][]=new int[5][5];
    int avl_seat=25;

    String allocate(int r,int c)
    {
        if (avl_seat<=0)
            return "No Seats Left :(";
        
        if (seat[r][c]!=1)
            {
                seat[r][c]=1;
                avl_seat--;
                return "Booked ! :)";
                
            }
        else
            {
                return "Seat Taken :(";
            }
    }
    void SeatStatus()
    {
        System.out.println("1 = Booked");
        System.out.println("0 = Available");
        for (int i=0;i<5;i++)
            {
                for (int j=0;j<5;j++)
                    {
                        if (seat[i][j]==1){
                            System.out.print(1+" ");
                        }
                        else{
                        System.out.print(0+" ");
                        }
                    }
                System.out.println();
            }
    }

    void CountSeats(int x)
    {
        if (x==1)
            {
                System.out.println("Total Occupied seats: "+(25-avl_seat));
            }
        else{
            System.out.println("Total Available seats: "+avl_seat);
        }
    }

    void MaxOccupied(int x)
    {
        if (x==1)
            {
                int max_r=0;
                int r_cnt=0;
                for (int i=0;i<5;i++)
                    {
                        for (int j=0;j<4;j++)
                            {
                                if (seat[i][j]==1)
                                    {
                                        r_cnt++;
                                    }
                            }
                        if (r_cnt>max_r)
                            {
                                max_r=r_cnt;
                                max_r=i;
                            }
                    }
                System.out.println("Row with max Occupied seats: "+(max_r+1));


            }
            else
            {
                int max_c=0;
                int c_cnt=0;
                for (int i=0;i<5;i++)
                    {
                        for (int j=0;j<4;j++)
                            {
                                if (seat[j][i]==1)
                                    {
                                        c_cnt++;
                                    }
                            }
                        if (c_cnt>max_c)
                            {
                                max_c=c_cnt;
                                max_c=i;
                            }
                    }
                System.out.println("Column with max Occupied seats: "+(max_c+1));

                }
    }
    
}
