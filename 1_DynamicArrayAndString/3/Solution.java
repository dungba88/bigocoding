/**
 * a candidate has chance to be qualified if and only if
 * - he is qualified by rank amongst his semi-final group
 * - OR he is qualified by time amongst all participants
 * thus we only need to check 2 cases, which each of the above conditions
 * has the maximum probability
 * in short, we only need to check if k = 0 OR k = N/2
 * 
 * if k = 0, then he must be in the top N-th of the overall tournaments
 * else he must be in the top N/2-th of each semifinal
 * 
 * P/S: in case k=0, we can use a sorted merge to find the N-th participant. i'm sure
 * there's another algorithm to do it in O(log(n)), but it will be very complicated
 * and didn't help the overall complexity since we need to iterate both arrays anyway.
 */
import java.util.Scanner;
 
public class Solution {
 
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int n = scanner.nextInt();
			int[] firstSemis = new int[n];
			int[] secondSemis = new int[n];
			for (int i = 0; i < n; i++) {
				firstSemis[i] = scanner.nextInt();
				secondSemis[i] = scanner.nextInt();
			}
			check(firstSemis, secondSemis);
		}
	}
 
	private static void check(int[] firstSemis, int[] secondSemis) {
		// do a sorted merge and find the N-th partition
		int cutOff = 0;
		int firstIndex = 0, secondIndex = 0;
		for (int i = 0; i < firstSemis.length; i++) {
			if (firstSemis[firstIndex] < secondSemis[secondIndex]) {
				cutOff = firstSemis[firstIndex++];
			} else {
				cutOff = secondSemis[secondIndex++];
			}
		}
 
		outputQualified(firstSemis, cutOff);
		outputQualified(secondSemis, cutOff);
	}
 
	private static void outputQualified(int[] group, int cutOff) {
		char[] chars = new char[group.length];
		for (int i = 0; i < group.length; i++) {
			if (i < group.length / 2 || group[i] <= cutOff) {
				chars[i] = '1';
			} else {
				chars[i] = '0';
			}
		}
		System.out.println(chars);
	}
}