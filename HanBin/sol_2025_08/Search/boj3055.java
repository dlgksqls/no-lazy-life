package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj3055 {
    static int r, c;
    static char[][] array;
    static Queue<int[]> waters;
    static int[][] dist;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        boolean startFlag = false;
        int startX = -1;
        int startY = -1;

        waters = new LinkedList<>();
        visited = new boolean[r][c];
        dist = new int[r][c];
        array = new char[r][c];
        for(int i = 0; i< r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                array[i][j] = str.charAt(j);
                if (str.charAt(j) == '*') waters.add(new int[]{i, j});
                if (str.charAt(j) == 'S') {
                    if (!startFlag) {
                        startX = i;
                        startY = j;
                        startFlag = true;
                    }
                }
            }
        }

        System.out.println(bfs(startX, startY));
        br.close();
    }

    private static String bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        visited[x][y] = true;

        int nowTurn = 0;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];
            int nowDist = poll[2];

            if (array[nowX][nowY] == 'D') return Integer.toString(nowDist);

            // 물이 먼저 퍼진다.
            // turn과 nowDist를 사용하여 nowDist가 바뀐다면 초가 지난 것이기에 물을 확산시키고
            // nowTurn을 바꿔주기
            if (nowDist > nowTurn) {
                for (int i = 0; i < waters.size(); i++) {
                    int[] water = waters.poll();
                    int waterX = water[0];
                    int waterY = water[1];
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = waterX + dx[dir];
                        int ny = waterY + dy[dir];

                        if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                        if (array[nx][ny] == 'X' || array[nx][ny] == 'D' || array[nx][ny] == '*') continue;

                        array[nx][ny] = '*';
                        waters.add(new int[]{nx, ny});
                    }
                    nowTurn = nowDist;
                }
            }

            // 고슴도치가 움직인다.
            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (array[nx][ny] == 'X') continue;
                if (visited[nx][ny]) continue;
                if (array[nx][ny] == '*') continue;

                queue.add(new int[]{nx, ny, nowDist + 1});
                visited[nx][ny] = true;
                dist[nx][ny] = nowDist + 1;
            }
        }
        return "KAKTUS";
    }
}
