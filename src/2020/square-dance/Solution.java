import java.util.Scanner;
import java.io.PrintStream;

public class Solution {
    static final String FILENAME = "T-input.txt";
    static final String IN = FILENAME;

    Scanner sc = new Scanner(getClass().getResourceAsStream(IN));
    // Scanner sc = new Scanner(System.in);
    PrintStream out = System.out;

    void run() {

        int t_total = sc.nextInt();

        // TODO: Messy here, needs to be initialised byt guarantee never will be empty
        // due to input
        // A better way?
        int[][] dfloor = new int[0][0];

        for (int t = 0; t < t_total; t++) {
            // Each test here
            int row_count = sc.nextInt();
            int col_count = sc.nextInt();
            // out.println("row: " + row_count
            // + ", col: " + col_count);

            dfloor = new int[row_count][col_count];
            for (int r = 0; r < row_count; r++) {
                for (int c = 0; c < col_count; c++) {
                    dfloor[r][c] = sc.nextInt();
                    // out.print(dfloor[r][c] + ", ");
                }
                // out.println("");
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

    int eliminate_dancers(int[][] dfloor) {
        int elimiatedCound = 0;
        for (int r = 0; r < dfloor.length; r++) {
            int[] row = dfloor[r];
            for (int c = 0; c < row.length; c++) {
                int curSkill = dfloor[r][c];
                if(curSkill > 0) {
                    // we have a dancer
                    int compassAve = calculateCompassAveSkill(dfloor, r, c);
                    if(compassAve > curSkill) {
                        // Eliminated - how to do without skewing other dancer scores? 
                        dfloor[r][c] = -1;
                        elimiatedCound++;
                    }
                }
            }
        }
        return elimiatedCound;
    }

    int calculateCompassAveSkill(int[][] dfloor, int r, int c) {
        // start at r,c
        int skill_sum = 0;
        
        // Move up until hit neighbour or off board
        int r_u =r;
        while(r_u > 0) {
            r_u--;
            int n_skill = dfloor[r_u][c];
            if(n_skill > -1) {
                skill_sum += n_skill;
                break; // Don't look for more neighbours
            }
        }
        // move down until hit nethbour or ..
        for(int r_d = r+1; r_d < dfloor.length; r_d++) {
            int n_skill = dfloor[r_d][c];
            if(n_skill > -1) {
                skill_sum += n_skill;
                break; // Don't look for more neighbours
            }
        }

        int col_count = dfloor[r].length;
        // move left ..
        for(int c_l = c-1; c_l > 0; c_l--) {
            int n_skill = dfloor[r][c_l];
            if(n_skill > -1) {
                skill_sum += n_skill;
                break; // Don't look for more neighbours
            }
        }

        // move right ...
        for(int c_r = c+1; c_r < col_count; c_r++) {
            int n_skill = dfloor[r][c_r];
            if(n_skill > -1) {
                skill_sum += n_skill;
                break; // Don't look for more neighbours
            }
        }

        return skill_sum;
    }

    int sum_skill(int[][] dfloor) {
        // TODO: Overflow?
        int skill = 0;
        for (int r = 0; r < dfloor.length; r++) {
            int[] row = dfloor[r];
            for (int c = 0; c < row.length; c++) {
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
}