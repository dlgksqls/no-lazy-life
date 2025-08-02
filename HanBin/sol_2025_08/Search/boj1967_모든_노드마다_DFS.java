package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj1967_모든_노드마다_DFS {
    static int n;
    static List<int[]>[] array;
    static boolean[] visited;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        array = new List[n+1];

        for(int i=1; i<=n; i++){
            array[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            array[a].add(new int[]{b, w});
            array[b].add(new int[]{a, w});
        }

        for(int i=1; i<=n ;i++){
            visited = new boolean[n+1];
            dfs(i, 0);
        }

        System.out.println(answer);

        br.close();
    }

    private static void dfs(int i, int sum) {
        answer = Math.max(answer, sum);

        if (!visited[i]){
            visited[i] = true;
            for (int[] node : array[i]) {
                int nodeDir = node[0];
                int wight = node[1];

                if (!visited[nodeDir]){
                    dfs(nodeDir, sum + wight);
                }
            }
        }
    }
}
