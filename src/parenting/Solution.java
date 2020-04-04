package parenting;

import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int interval_count = in.nextInt();
            List<Interval> intervals = new ArrayList<>();
            for (int j=0; j<interval_count; j++) {
                Interval cur = new Interval(in);
                intervals.add(cur);
            }

            intervals.sort(new SortByStart());

  /*          for ( Interval interval : intervals) {
                System.out.println(interval);
            }
*/
            int[] combs = new int[interval_count];
            Arrays.fill(combs, 0);
            int[] length = new int[interval_count];
            Arrays.fill(length, 2);
            nestedLoopOperation(combs, length, 0);

            System.out.println("Case #" + i + ": " );
        }
    }

    private static void nestedLoopOperation(int[] counters, int[] length, int level) {
        if(level == counters.length) performOperation(counters);
        else {
            for (counters[level] = 0; counters[level] < length[level]; counters[level]++) {
                nestedLoopOperation(counters, length, level + 1);
            }
        }
    }

    private static void performOperation(int[] counters) {
        System.out.println(Arrays.toString(counters));
    }

    private static class Interval {

        public int start;
        public int end;

        // Constructor reads two ints from standard in
        public Interval(Scanner in) {
            start = in.nextInt();
            end = in.nextInt();
        }

        public String toString() {
            return "[ " + String.valueOf(start) + "," + String.valueOf(end) + " ]";
        }
    }

    private static class SortByStart implements Comparator<Interval> {

        @Override
        public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }
    }
}