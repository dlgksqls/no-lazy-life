package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1600 {
    static int k;
    static int w, h;
    static int[][] array;
    static int[][][] dist;
    static int[] kdx = {-2, -1, 1, 2, 2, 1, -2, -1};
    static int[] kdy = {1, 2, 2, 1, -1, -2, -1, -2};
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        array = new int[h][w];
        dist = new int[h][w][k+1];
        for(int i=0; i<h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<w; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                for (int l=0; l<=k; l++) dist[i][j][l] = Integer.MAX_VALUE;
            }
        }

        bfs();

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<=k; i++){
            answer = Math.min(answer, dist[h-1][w-1][i]);
        }

        System.out.println((answer != Integer.MAX_VALUE) ? answer : -1);
        br.close();
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        dist[0][0][0] = 0;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];
            int horseCount = poll[2];

            if (horseCount < k) {
                for (int dir = 0; dir < 8; dir++) {
                    int nx = nowX + kdx[dir];
                    int ny = nowY + kdy[dir];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                    if (array[nx][ny] == 1) continue;
                    if (dist[nx][ny][horseCount + 1] != Integer.MAX_VALUE) continue;
                    if (dist[nx][ny][horseCount + 1] < dist[nowX][nowY][horseCount] + 1) continue;

                    dist[nx][ny][horseCount + 1] = dist[nowX][nowY][horseCount] + 1;
                    queue.add(new int[]{nx, ny, horseCount + 1});
                }
            }

            for(int dir=0; dir<4; dir ++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if (array[nx][ny] == 1) continue;
                if (dist[nx][ny][horseCount] != Integer.MAX_VALUE) continue;
                if (dist[nx][ny][horseCount] < dist[nowX][nowY][horseCount] + 1) continue;

                dist[nx][ny][horseCount] = dist[nowX][nowY][horseCount] + 1;
                queue.add(new int[]{nx, ny, horseCount});
            }
        }
    }
}
