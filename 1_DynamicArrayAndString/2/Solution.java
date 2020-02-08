import java.util.Scanner;
import java.util.function.Predicate;
 
public class Solution {
 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String first = scanner.nextLine();
            String second = scanner.nextLine();
            String result = findString(first, second);
            System.out.println(result);
        }
    }
 
    private static String findString(String first, String second) {
        char[] chars = new char[first.length()];
        int i = 0;
        char c1 = 0, c2 = 0;
        for (i = 0; i < chars.length; i++) {
            c1 = first.charAt(i);
            c2 = second.charAt(i);
            if (c1 != c2)
                break;
            chars[i] = c1;
        }
        // if there is a character between c1 and c2, use it
        if (c1 < c2 - 1) {
            return insertAll(chars, i, (char) (c1 + 1));
        }
        // otherwise, try to use c1 followed by all 'z'
        if (findChar(first, i + 1, c -> c < 'z')) {
            chars[i] = c1;
            return insertAll(chars, i + 1, 'z');
        }
        // else, try to use c2 followed by all 'a'
        if (findChar(second, i + 1, c -> c > 'a')) {
            chars[i] = c2;
            return insertAll(chars, i + 1, 'a');
        }
        return "No such string";
    }
 
    private static String insertAll(char[] chars, int i, char c) {
        for (int j = i; j < chars.length; j++) {
            chars[j] = c;
        }
        return new String(chars);
    }
 
    private static boolean findChar(String str, int idx, Predicate<Character> predicate) {
        for (int j = idx; j < str.length(); j++) {
            if (predicate.test(str.charAt(j)))
                return true;
        }
        return false;
    }
}
