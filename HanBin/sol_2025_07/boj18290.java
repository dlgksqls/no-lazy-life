package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

public class boj18290 {
    static int n, m, k;
    static int[][] array;
    static int answer = Integer.MIN_VALUE;
    static boolean[][] visited;
    static List<int[]> visits;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        array = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visits = new ArrayList<>();
        visited = new boolean[n][m];
        recursion(0);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(int depth) {
        if(depth == k){
            int sum = 0;
            for (int[] visit : visits) {
                int x = visit[0];
                int y = visit[1];

                sum += array[x][y];
            }

            answer = Math.max(answer, sum);
            return;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if (!visited[i][j]){
                    if (check(i, j)) {
                        visits.add(new int[]{i, j});
                        visited[i][j] = true;
                        recursion(depth + 1);
                        visits.remove(visits.size() - 1);
                        visited[i][j] = false;
                    }
                }
            }
        }
    }

    private static boolean check(int x, int y) {
        for (int[] visit : visits) {
            int vx = visit[0];
            int vy = visit[1];

            if ((x - 1 == vx && y == vy) ||
                    (x + 1 == vx && y == vy) ||
                    (x == vx && y - 1 == vy) ||
                    (x == vx && y + 1 == vy)) {
                return false;
            }
        }
        return true;
    }
}
