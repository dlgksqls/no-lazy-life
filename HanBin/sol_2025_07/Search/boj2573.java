package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 대륙의 갯수를 먼저 세아리고, 물에 닿아있는 대륙의 수명을 깎아내리는 방식
 * 대륙의 갯수 = bfs()
 * 대륙의 수명 = 완전 탐색으로,,,
 */
public class boj2573 {
    static int n, m;
    static int[][] array;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static List<int[]> remove;
    static int answer = 0;
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
            }
        }

        while(true){
            int earth = 0;
            visited = new boolean[n][m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if (array[i][j] != 0 && !visited[i][j]) {
                        earth ++;
                        bfs(i, j);
                    }
                }
            }

            if (earth >= 2) {
                System.out.println(answer);
                br.close();
                return;
            }
            else if (earth == 0) {
                System.out.println(0);
                br.close();
                return;
            }

            remove = new ArrayList<>();
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if (array[i][j] == 0) continue;
                    int count = 0;
                    for(int dir=0; dir<4; dir++){
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        if (array[nx][ny] == 0) count ++;
                    }
                    remove.add(new int[]{i, j, count});
                }
            }

            for (int[] row : remove) {
                int x = row[0];
                int y = row[1];
                int count = row[2];

                if (array[x][y] <= count) array[x][y] = 0;
                else array[x][y] -= count;
            }

            answer ++;
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];

            for(int i=0; i<4; i++){
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (array[nx][ny] == 0) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
    }
}
