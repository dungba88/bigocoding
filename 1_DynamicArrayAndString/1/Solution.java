import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[][] segs = new int[n][2];
            for (int i = 0; i < n; i++) {
                segs[i][0] = scanner.nextInt();
                segs[i][1] = scanner.nextInt();
            }
            int result = findSegment(segs);
            System.out.println(result);
        }
    }
 
    private static int findSegment(int[][] segs) {
        if (segs.length == 0)
            return -1;
        if (segs.length == 1)
            return 1;
        int min = segs[0][0];
        int max = segs[0][1];
        int result = 1;
        for (int i = 1; i < segs.length; i++) {
            int[] seg = segs[i];
            if (seg[0] < min || seg[1] > max) {
                result = -1;
            }
            min = Math.min(min, seg[0]);
            max = Math.max(max, seg[1]);
            if (seg[0] == min && seg[1] == max) {
                result = i + 1;
            }
        }
        return result;
    }
}
