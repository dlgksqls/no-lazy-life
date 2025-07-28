package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj13023 {
    static int n, m;
    static List<Integer>[] lists;
    static boolean[] visited;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        lists = new ArrayList[n];
        for(int i=0; i<n; i++){
            lists[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lists[a].add(b);
            lists[b].add(a);
        }

        for(int i=0; i<n; i++) {
            visited = new boolean[n];
            recursion(i, 0);
            if (flag) break;
        }

        System.out.println(flag ? 1 : 0);
        br.close();
    }

    private static void recursion(int start, int depth) {
        if (depth == 4){
            flag = true;
            return;
        }

        visited[start] = true;
        for (Integer i : lists[start]) {
            if(!visited[i]){
                recursion(i, depth + 1);
            }
        }
        visited[start] = false;
    }
}
