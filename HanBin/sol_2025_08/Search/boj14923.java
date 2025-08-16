package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj14923 {
    static int n, m;
    static int hx, hy;
    static int ex, ey;
    static int[][] array;
//    static List<int[]> walls;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        hx = Integer.parseInt(st.nextToken());
        hy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        array = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++){
                int num = Integer.parseInt(st.nextToken());
//                if (num == 1) walls.add(new int[]{i, j}); // 이 방법은 시간초과가 뜸
                array[i][j] = num;
            }
        }

        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        br.close();
    }

    private static void bfs() {
        int[][][] time = new int[n+1][m+1][2]; // 벽을 부순다 1, 벽을 부수지 않는다 0
        for(int i=1; i<=n ;i++){
            for(int j=1; j<=m; j++){
                Arrays.fill(time[i][j], -1);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{hx, hy, 0});
        time[hx][hy][0] = 0;

        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];
            int isWall = poll[2]; // 벽을 부쉈는지

            if (nowX == ex && nowY == ey){
                answer = time[nowX][nowY][isWall];
                return;
            }

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

                // 벽이 아닌 경우
                if (array[nx][ny] == 0 && time[nx][ny][isWall] == -1){
                    time[nx][ny][isWall] = time[nowX][nowY][isWall] + 1;
                    queue.add(new int[]{nx, ny, isWall});
                }

                // 벽인데 한번도 벽을 부수지 않은 경우
                if (array[nx][ny] == 1 && isWall == 0 && time[nx][ny][1] == -1){
                    time[nx][ny][1] = time[nowX][nowY][isWall] + 1;
                    queue.add(new int[]{nx, ny, 1});
                }
            }
        }
    }
}
