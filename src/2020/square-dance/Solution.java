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

            out.println(competition_interest);
        }
    }

    int eliminate_dancers(int[][] dfloor) {
        return 0;
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