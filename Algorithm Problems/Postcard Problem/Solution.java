import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String t = in.next();
        int n = s.length();
        int c1 = 0;
        int c2 = 0;

        char[] chars = s.toCharArray();
        int[] pr = new int[123];
        for (int i = 0; i < t.length(); i++) {
            pr[t.charAt(i)]++;
        }
        for (int i = 0; i < n; i++) {
            if (pr[chars[i]] >= 1) {
                pr[chars[i]]--;
                chars[i] = ' ';
                c1++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (chars[i] == ' ') {
                continue;
            }

            char ch = ' ';
            if (Character.isLowerCase(chars[i])) {
                ch = Character.toUpperCase(chars[i]);
            } else {
                ch = Character.toLowerCase(chars[i]);
            }

            if (pr[ch] >= 1) {
                pr[ch]--;
                c2++;
            }
        }
        in.close();
        System.out.println(c1 + " " + c2);
    }
}