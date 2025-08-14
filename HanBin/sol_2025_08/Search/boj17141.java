package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj17141 {
    static int n, m;
    static int[][] array;
    static int[][] time;
    static List<int[]> virusPos = new ArrayList<>();
    static List<int[]> viruses = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int cur = Integer.parseInt(st.nextToken());
                if (cur == 2) virusPos.add(new int[]{i, j});
                array[i][j] = cur;
            }
        }

        recursion(0, 0);

        // sub가 항상 -1이 나와 answer가 업데이트 되지 않았다면 -1 출력
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

        br.close();
    }

    private static void recursion(int idx, int count) {
        if (viruses.size() == m){
            init();
            int sub = bfs();
            if (sub != -1) answer = Math.min(answer, sub);
            return;
        }

        for(int i=idx; i<virusPos.size(); i++){
            viruses.add(virusPos.get(i));
            recursion(i + 1, count + 1);
            viruses.remove(viruses.size() - 1);
        }
    }

    private static void init() {
        time = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if (array[i][j] == 1) time[i][j] = -2;
                else time[i][j] = -1;
            }
        }
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();

        for (int[] virus : viruses) {
            queue.add(new int[]{virus[0], virus[1], 0});
            time[virus[0]][virus[1]] = 0;
        }

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];
            int nowTime = poll[2];

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (array[nx][ny] == 1) continue;
                if (time[nx][ny] != -1) continue;

                time[nx][ny] = nowTime + 1;
                queue.add(new int[]{nx, ny, nowTime + 1});
            }
        }

        // 벽이 아닌 좌표 중 방문하지 않은 곳이 있는지 체크...
        int sucTime = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if (array[i][j] != 1 && time[i][j] == -1) return -1;
                if (array[i][j] != 1) sucTime = Math.max(sucTime, time[i][j]);
            }
        }

        return sucTime;
    }
}
