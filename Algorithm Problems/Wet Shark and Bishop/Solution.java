import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] firstComb = new int[2000];
		int[] secComb = new int[2000];
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			firstComb[i + j]++;
			secComb[1000 - i + j]++;
		}
		long sum = 0;
		for (int i = 0; i < 2000; i++) {
			sum += (long) firstComb[i] * (firstComb[i] - 1) / 2;
			sum += (long) secComb[i] * (secComb[i] - 1) / 2;
		}
		System.out.println(sum);
	}
}