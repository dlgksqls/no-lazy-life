package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 연구소에서 빈 칸의 리스트(조합)를 만든다.
 * 2. 빈 칸 3곳에 벽을 놓아보고, 바이러스를 전파시켜본다.
 * 3. 그런 후 안전 지역의 갯수를 update 한다.
 */
public class boj14502 {
    static int n, m;
    static int[][] array;
    static List<int[]> comBi;
    static boolean[] used;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        comBi = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if (array[i][j] == 0) comBi.add(new int[]{i, j});
            }
        }

        used = new boolean[comBi.size()];

        combination(0, 0);
        System.out.println(answer);

        br.close();
    }

    private static void combination(int idx, int depth) {
        if (depth == 3){
            bfs();
            return;
        }

        for(int i=idx; i<comBi.size(); i++){
            if(!used[i]){
                int[] wall = comBi.get(i);
                used[i] = true;
                array[wall[0]][wall[1]] = 1;
                combination(i + 1, depth + 1);
                array[wall[0]][wall[1]] = 0;
                used[i] = false;
            }
        }
    }

    private static void bfs() {
        int[][] copy = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        List<int[]> viruses = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                copy[i][j] = array[i][j];
                if (copy[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        for (int[] virus : viruses) {
            if (!visited[virus[0]][virus[1]]){
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{virus[0], virus[1]});
                visited[virus[0]][virus[1]] = true;

                while(!queue.isEmpty()){
                    int[] poll = queue.poll();
                    int nowX = poll[0];
                    int nowY = poll[1];

                    for(int i=0; i<4; i++){
                        int nx = nowX + dx[i];
                        int ny = nowY + dy[i];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        if (copy[nx][ny] == 1) continue;
                        if (visited[nx][ny]) continue;

                        visited[nx][ny] = true;
                        copy[nx][ny] = 2;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        answer = Math.max(answer, count(copy));
    }

    private static int count(int[][] copy) {
        int count = 0;
        for (int[] row : copy) {
            for (int i : row) {
                if (i == 0) count ++;
            }
        }
        return count;
    }
}
