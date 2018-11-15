import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    
    int N = (int) 1e4;
    int INF = (int) 1e9;
    
    int[] dx = new int[]{-1, 0, 0, 1};
    int[] dy = new int[]{0, -1, 1, 0};
    
    int n, m, k;
    int[] r = new int[N], c = new int[N];
    
    HashMap<Integer, Integer> map = new HashMap<>();
    
    int nc = 0;
    int[] mark = new int[N];
    
    void solve() {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        
        int s = -1;
        for (int i = 0; i < k; i++) {
            r[i] = in.nextInt() - 1;
            c[i] = in.nextInt() - 1;
            if (r[i] == 0 && c[i] == 0) s = i;
            map.put(r[i] * N + c[i], i);
        }
        
        Arrays.fill(mark, -1);
        for (int i = 0; i < k; i++) {
            if (mark[i] < 0) {
                mark[i] = nc++;
                dfs(i);
            }
        }
        int[] cnt = new int[nc];
        for (int i = 0; i < k; i++) cnt[mark[i]]++;
        int[][] g = new int[nc][];
        for (int i = 0; i < nc; i++) g[i] = new int[cnt[i]];
        for (int i = 0; i < k; i++) g[mark[i]][--cnt[mark[i]]] = i;
        
        int[] cnt_row = new int[n], cnt_col = new int[m];
        for (int i = 0; i < k; i++) {
            cnt_row[r[i]]++;
            cnt_col[c[i]]++;
        }
        int[][] rows = new int[n][];
        int[][] cols = new int[m][];
        for (int i = 0; i < n; i++) rows[i] = new int[cnt_row[i]];
        for (int j = 0; j < m; j++) cols[j] = new int[cnt_col[j]];
        for (int i = 0; i < k; i++) {
            rows[r[i]][--cnt_row[r[i]]] = i;
            cols[c[i]][--cnt_col[c[i]]] = i;
        }
        
        int[] dp = new int[k];
        Arrays.fill(dp, INF);
        dp[s] = 0;
        Queue<int[]> qu = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        qu.offer(new int[]{dp[s], s});
        int[] mark_row = new int[n];
        int[] mark_col = new int[m];
        int[] mark_color = new int[nc];
        while (!qu.isEmpty()) {
            int[] t = qu.poll();
            int d = t[0], u = t[1];
            if (d > dp[u]) continue;
            
            if (mark_color[mark[u]] == 0) {
                for (int v : g[mark[u]]) {
                    dp[v] = dp[u];
                    qu.offer(new int[]{dp[v], v});
                }
                mark_color[mark[u]] = 1;
            }
            
            int x = r[u], y = c[u];
            for (int i = -2; i <= 2; i++) {
                if (x + i >= 0 && x + i < n && mark_row[x + i] == 0) {
                    for (int v : rows[x + i]) {
                        if (dp[v] > dp[u] + 1) {
                            dp[v] = dp[u] + 1;
                            qu.offer(new int[]{dp[v], v});
                        }
                    }
                    mark_row[x + i] = 1;
                }
                if (y + i >= 0 && y + i < m && mark_col[y + i] == 0) {
                    for (int v : cols[y + i]) {
                        if (dp[v] > dp[u] + 1) {
                            dp[v] = dp[u] + 1;
                            qu.offer(new int[]{dp[v], v});
                        }
                    }
                    mark_col[y + i] = 1;
                }
            }
        }
        
        int ans = INF;
        for (int i = 0; i < k; i++) {
            int dx = n - 1 - r[i], dy = m - 1 - c[i];
            if (dx <= 1 || dy <= 1) {
                if (dx == 0 && dy == 0) ans = Math.min(ans, dp[i]);
                else ans = Math.min(ans, dp[i] + 1);
            }
        }
        if (ans == INF) ans = -1;
        out.println(ans);
    }
    
    void dfs(int u) {
        int x = r[u], y = c[u];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                int v = map.getOrDefault(nx * N + ny, -1);
                if (v >= 0 && mark[v] < 0) {
                    mark[v] = mark[u];
                    dfs(v);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        in = new OptimizedScanner(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(System.out);
        new Solution().solve();
        out.close();
    }
    
    static OptimizedScanner in;
    static PrintWriter out;
    
    static class OptimizedScanner {
        BufferedReader in;
        StringTokenizer st;
        
        public OptimizedScanner(BufferedReader in) {
            this.in = in;
        }
        
        public String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(nextToken());
        }
        
        public long nextLong() {
            return Long.parseLong(nextToken());
        }
        
        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
}