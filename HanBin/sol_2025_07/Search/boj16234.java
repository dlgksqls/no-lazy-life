package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj16234 {
    static int n, l, r;
    static int[][] array;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        array = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            boolean flag = false;
            visited = new boolean[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if (visited[i][j]) continue;
                    List<int[]> union = findUnion(i, j);
                    if (union.size() <= 1) continue;
                    join(union);
                    flag = true;
                }
            }

            if (!flag) break;
            answer ++;
        }

        System.out.println(answer);
        br.close();
    }

    private static ArrayList<int[]> findUnion(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> union = new ArrayList<>();
        queue.add(new int[]{x, y});
        union.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int now_x = poll[0];
            int now_y = poll[1];
            for(int i=0; i<4; i++){
                int nx = now_x + dx[i];
                int ny = now_y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (visited[nx][ny]) continue;

                int diff = Math.abs(array[now_x][now_y] - array[nx][ny]);
                if (l <= diff && diff <= r){
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    union.add(new int[]{nx, ny});
                }
            }
        }

        return union;
    }

    private static void join(List<int[]> union) {
        int sum = 0;
        for (int[] nation : union) {
            sum += array[nation[0]][nation[1]];
        }

        int result = sum / union.size();
        for (int[] nation : union) {
            array[nation[0]][nation[1]] = result;
        }
    }
}
