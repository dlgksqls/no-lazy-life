package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16928 {
    static int n, m;
    static int[] array = new int[101];
    static boolean[] visited = new boolean[101];
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            array[x] = y;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            array[u] = v;
        }

        bfs();
        System.out.println(answer);
        br.close();
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int now = poll[0];
            int dist = poll[1];

            if (now == 100) {
                answer = Math.min(answer, dist);
                return;
            }

            if (array[now] != 0){
                now = array[now];
            }

            for(int dice = 1; dice <= 6; dice ++){
                int next = now + dice;
                if (next > 100) continue;

                if (array[next] != 0) next = array[next];

                if (!visited[next]){
                    visited[next] = true;
                    queue.add(new int[]{next, dist + 1});
                }
            }
        }
    }
}
