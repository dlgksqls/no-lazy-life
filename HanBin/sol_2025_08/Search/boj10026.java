package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj10026 {
    static int n;
    static char[][] array_1;
    static char[][] array_2;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        array_1 = new char[n][n];
        array_2 = new char[n][n];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                array_1[i][j] = str.charAt(j);
                if (str.charAt(j) == 'G') array_2[i][j] = 'R';
                else array_2[i][j] = str.charAt(j);
            }
        }

        doBfs(array_1);
        System.out.print(answer + " ");

        answer = 0;
        doBfs(array_2);
        System.out.println(answer);

        br.close();
    }

    private static void bfs(int x, int y, char pre, char[][] array) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (array[nx][ny] != pre) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
    }

    private static void doBfs(char[][] array) {
        visited = new boolean[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if (!visited[i][j]) {
                    char pre = array[i][j];
                    answer ++;
                    bfs(i, j, pre, array);
                }
            }
        }
    }
}
