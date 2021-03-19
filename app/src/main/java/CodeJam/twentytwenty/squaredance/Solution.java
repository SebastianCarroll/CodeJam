package CodeJam.twentytwenty.squaredance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;

public class Solution {
    static final String FILENAME = "T-input.txt";
    static final String IN = FILENAME;

    Scanner sc = new Scanner(getClass().getResourceAsStream(IN));
    // Scanner sc = new Scanner(System.in);
    PrintStream out = System.out;

    int row_count;
    int col_count;

    void run() {

        int t_total = sc.nextInt();

        for (int t = 0; t < t_total; t++) {

            row_count = sc.nextInt();
            col_count = sc.nextInt();

            int[][] dfloor = new int[row_count][col_count];

            for (int r = 0; r < row_count; r++) {
                for (int c = 0; c < col_count; c++) {
                    dfloor[r][c] = sc.nextInt();
                }
            }

            // TODO: could just sum initial pass to get first
            // round interest
            // Here we have all dancers on the floor
            boolean competition_ended = false;
            int competition_interest = 0;
            while (!competition_ended) {
                int round_interest = sum_skill(dfloor);
                int eliminated_count = eliminate_dancers(dfloor);
                competition_interest = competition_interest + round_interest;
                if (eliminated_count == 0) {
                    competition_ended = true;
                }
            }

            out.println(String.format("Case #%d: %s", t+1, competition_interest));
        }
    }

    void print_board(int[][] board) {
        for(int r=0; r<row_count; r++) {
            int[] row = board[r];
            for(int c=0; c<col_count; c++) {
                out.print(" " + row[c]);
            }
            out.println("");
        }
    }

    int eliminate_dancers(int[][] dfloor) {
        List<Point> eliminated = new ArrayList<Point>();
        for (int r = 0; r < row_count; r++) {
            for (int c = 0; c<col_count; c++) {
                int curSkill = dfloor[r][c];
                if(curSkill > 0) {
                    // we have a dancer
                    double compassAve = calculateCompassAveSkill(dfloor, r, c);
                    if(compassAve > curSkill) {
                        eliminated.add(new Point(r,c));
                    }
                }
            }
        }

        for (Point point : eliminated) {
            dfloor[point.x][point.y] = -1;   
        }

        return eliminated.size();
    }

    // TODO: this algorithm will slow down significanlty with large boards when
    // lots of dancers have been eliminated because there will be a vast number of 
    // empty cells between neighbours but we are checking each empty cell possibly 4 times 
    // each round
    double calculateCompassAveSkill(int[][] dfloor, int r, int c) {
        // start at r,c
        double skill_sum = 0;
        double n_count = 0;
        
        // Move up until hit neighbour or off board
        for(int r_u = r-1; r_u>=0; r_u--) {
            int n_skill = dfloor[r_u][c];
            if(n_skill > -1) {
                skill_sum += n_skill;
                n_count++;
                break; // Don't look for more neighbours
            }
        }
        // move down until hit nethbour or ..
        for(int r_d = r+1; r_d<row_count; r_d++) {
            int n_skill = dfloor[r_d][c];
            if(n_skill > -1) {
                skill_sum += n_skill;
                n_count++;
                break; // Don't look for more neighbours
            }
        }

        // move left ..
        for(int c_l = c-1; c_l >= 0; c_l--) {
            int n_skill = dfloor[r][c_l];
            if(n_skill > -1) {
                skill_sum += n_skill;
                n_count++;
                break; // Don't look for more neighbours
            }
        }

        // move right ...
        for(int c_r = c+1; c_r<col_count; c_r++) {
            int n_skill = dfloor[r][c_r];
            if(n_skill > -1) {
                skill_sum += n_skill;
                n_count++;
                break; // Don't look for more neighbours
            }
        }

        // TODO: needs to be a float? Or can i compare to 4x the skill 
        return n_count == 0 ? skill_sum : skill_sum/n_count;
    }

    int sum_skill(int[][] dfloor) {
        // TODO: Overflow?
        // How do we know if this might be a problem? Som bote calcs in order
        // 1 <= T <= 100 // not too concerned here
        // 1 <= Sij <= 10^6
        // 1 <= RxC <= 100 
        // Coudl we design a test case where only one dancer is eliminated each round
        // eg. 
        /*
         1 2 3
         4 5 6 
         7 8 9
         skill ave: 
         3 3 4

         Can already see this wont work. but as a worst case, lets assume we can
         We could have:
         Sum_10^6 .. 10^6-100 +
         Sum_10^6 .. 10^6-99 + 
         Sum_10^6 .. 10^6-98 +
         ..
         Sum_10^6 .. 10^6

         Lets ignore the 100 difference and say its
         100 dancers for 100 rounds at max skill
         10^6 * 10^2 *10^2 = 10^10

         Max int in java is: 2147483647
         = 2^31
         2^10 about 10^3

         2^31 = 2^(10*3) = (2^10)^3 = (10^3)^3 = 10^9
         So not for test 1 but def for test two
        */
        int skill = 0;
        for (int r=0; r<row_count; r++) {
            for (int c=0; c<col_count; c++) {
                int curSkill = dfloor[r][c];
                if (curSkill > 0) {
                    skill += curSkill;
                }
            }
        }
        return skill;
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    class Point {

        int x; 
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}