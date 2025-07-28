package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 바깥쪽에 있는 치즈가 먼저 녹는다.
 * 2. 그래서 바깥쪽부터 탐색을 한다.
 * 3. 턴마다 녹일 수 있는 치즈를 녹인다.
 * 4. 빈 칸이면 queue에 넣어 탐색을 계속 한다
 */
public class boj2636 {
    static int n, m;
    static int[][] array;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int time = 0;
    static int cheese = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if (array[i][j] == 1) cheese ++;
            }
        }

        int last = 0;
        while(cheese > 0){
            last = cheese;
            visited = new boolean[n][m];
            bfs();
            time ++;
        }

        System.out.println(time);
        System.out.println(last);

        br.close();
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for(int i=0; i<4; i++){
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny]) continue;

                if (array[nx][ny] == 0) queue.add(new int[]{nx, ny});
                else if (array[nx][ny] == 1) {
                    array[nx][ny] = 0;
                    cheese --;
                }

                visited[nx][ny] = true;
            }
        }
    }
}
