package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj9466 {
    static int n;
    static int[] students;
    static boolean[] visited;
    static boolean[] finished;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc ++){
            n = Integer.parseInt(br.readLine());
            students = new int[n+1];
            finished = new boolean[n+1];
            visited = new boolean[n+1];
            count = 0;
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++){
                students[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=n; i++){
                if (!visited[i]){
                    dfs(i);
                }
            }
            System.out.println(n - count);
        }
        br.close();
    }

    private static void dfs(int v) {
        if (visited[v]){
            finished[v] = true;
            count ++;
        }
        else {
            visited[v] = true;
        }

        if (!finished[students[v]]) dfs(students[v]);

        visited[v] = false;
        finished[v] = true;
    }
}
