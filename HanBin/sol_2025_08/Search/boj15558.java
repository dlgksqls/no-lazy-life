package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj15558 {
    static int n, k;
    static int[][] array;
    static boolean[][] visited;
    static int[] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        array = new int[2][n];
        for(int i=0; i<2; i++){
            char[] chars = br.readLine().toCharArray();
            for(int j=0; j<chars.length; j++){
                array[i][j] = chars[j] - '0';
            }
        }

        visited = new boolean[2][n];
        move = new int[]{1, -1, k};
        System.out.println(bfs(0, 0));
        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            int time = now[2];

            for(int i=0; i<3; i++){
                int dir = move[i];
                int nx = nowX;
                int ny = nowY;

                if (i == 2) nx = 1 - nowX;
                ny += dir;

                if (ny >= n) return 1;

                if (ny >= 0 && ny > time && array[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, time + 1});
                }
            }
        }

        return 0;
    }
}
