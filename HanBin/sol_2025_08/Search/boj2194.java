package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2194 {
    static int n, m, a, b, k;
    static int[][] array;
    static int[] head;
    static int[] fin;
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        array = new int[n+1][m+1];

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            array[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
        }

        boolean isFirst = true;
        for(int unit=0; unit<2; unit++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (isFirst) {
                head = new int[]{x, y};
                isFirst = false;
            }
            else{
                fin = new int[]{x, y};
            }
        }

        visited = new boolean[n+1][m+1];
        dist = new int[n+1][m+1];
        System.out.println(bfs(head[0], head[1]));

        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];
            int nowDist = poll[2];

            if (nowX == fin[0] && nowY == fin[1]) return nowDist;

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if (visited[nx][ny]) continue;
                if (array[nx][ny] == 2) continue;
                if (!isValid(nx, ny)) continue;

                queue.add(new int[]{nx, ny, nowDist + 1});
                visited[nx][ny] = true;
                dist[nx][ny] = nowDist + 1;
            }
        }

        return -1;
    }

    private static boolean isValid(int x, int y) {
        for(int i=0; i<a; i++){
            for(int j=0; j<b; j++){
                int nx = x + i;
                int ny = y + j;

                if (nx < 1 || nx > n || ny < 1 || ny > m) return false;
                if (array[nx][ny] == 2) return false;
            }
        }
        return true;
    }
}
