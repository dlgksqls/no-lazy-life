package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2589 {
    static int col;
    static int row;
    static char[][] array;
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        array = new char[col][row];
        for(int i=0; i<col; i++){
            array[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<col; i++){
            for(int j=0; j<row; j++){
                if (array[i][j] == 'W') continue;
                visited = new boolean[col][row];
                dist = new int[col][row];
                bfs(i, j);
            }
        }

        System.out.println(answer);
        br.close();
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];
            int nowDist = poll[2];
            answer = Math.max(answer, nowDist);

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= col || ny < 0 || ny >= row) continue;
                if (array[nx][ny] == 'W') continue;
                if (visited[nx][ny]) continue;

                queue.add(new int[]{nx, ny, nowDist + 1});
                visited[nx][ny] = true;
                dist[nx][ny] = nowDist + 1;
            }
        }
    }
}
