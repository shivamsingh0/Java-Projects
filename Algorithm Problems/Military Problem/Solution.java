import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Throwable {
        CustomScanner scanner = new CustomScanner();
        PrintWriter printout = new PrintWriter(System.out);
        
        n = scanner.nextInt();
        tIn = new int[n];
        tOut = new int[n];
        endNode = new int[n];
        int sub = scanner.nextInt();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++)
            adj[scanner.nextInt() - 1].add(i + 1);
        DFS(0);
        while (sub-- > 0) {
            int nextO = scanner.nextInt() - 1; // u
            int nextO2 = scanner.nextInt() - 1; // k
            if (tIn[nextO] + nextO2 > tOut[nextO])
                printout.println(-1 + "");
            else
                printout.println(endNode[tIn[nextO] + nextO2] + 1);
        }

        printout.flush(); // optimize
        printout.close();
    }

    static int n, time;
    static int[] tIn, tOut, endNode;
    static ArrayList<Integer>[] adj;
    //define the depth-first-search
    static void DFS(int u) {
        endNode[time] = u;
        tIn[u] = time++;
        for (int i : adj[u])
            DFS(i);
        tOut[u] = time - 1;
    }

    static class CustomScanner {
        BufferedReader br;
        StringTokenizer st;

        public CustomScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    System.out.println("THERES AN ERROR!!!");
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                System.out.println("THERES AN ERROR2!!!");
            }
            return str;
        }
    }
}