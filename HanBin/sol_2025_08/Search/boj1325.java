package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1325 {
    static int n, m;
    static ArrayList<Integer>[] array;
    static int[] virus;
    static boolean[] visited;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        virus = new int[n+1];
        int max = Integer.MIN_VALUE;
        array = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            array[i] = new ArrayList();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            array[b].add(a);
        }

        for(int i=1; i<array.length; i++){
            visited = new boolean[n+1];
            count = 0;
            visited[i] = true;
            bfs(i);
            virus[i] = count;
            max = Math.max(max, virus[i]);
        }

        for(int i=1; i<=n; i++){
            if (virus[i] == max) System.out.print(i + " ");
        }

        br.close();
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        while(!queue.isEmpty()){
            int now = queue.poll();

            for (int i : array[now]) {
                if (!visited[i]){
                    count ++;
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
