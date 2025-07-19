package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 최단거리를 구하는 문제이지만, 단순히 BFS를 사용하면안된다.
 * 각 땅에 도달할 떄마다 체력, 쉴드량이 다르기 때문이다.
 * 결국 각 땅에 도달할 때마다의 상태를 저장해야한다.
 *
 * visited[][]에 각 땅에 도달할때 당시의 체력을 저장하여서 생존할 수 있는 경우에 인접한 땅에 가도록 하였다.
 * 나머지는 구현이다.
 */
public class boj22944 {
    static int n, h, d;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class State{
        int x, y, hp, shield, time;

        public State(int x, int y, int hp, int shield, int time) {
            this.x = x;
            this.y = y;
            this.hp = hp;
            this.shield = shield;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new char[n][n];
        int sx = -1;
        int sy = -1;
        for(int i=0; i<n; i++){
            String row = br.readLine();
            for(int j=0; j<n; j++){
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }
            }
        }

        int result = bfs(sx, sy);
        System.out.println(result);

        br.close();
    }

    private static int bfs(int sx, int sy) {
        Queue<State> queue = new LinkedList<>();
        visited = new int[n][n];
        queue.add(new State(sx, sy, h, 0, 0));
        visited[sx][sy] = h;

        while(!queue.isEmpty()){
            State cur = queue.poll();

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (map[nx][ny] == 'E') return cur.time + 1;

                int hp = cur.hp;
                int shield = cur.shield;

                if (map[nx][ny] == 'U') shield = d;
                if (shield > 0) shield --;
                else hp --;

                if (hp <= 0) continue;

                if (visited[nx][ny] < hp){
                    visited[nx][ny] = hp;
                    queue.add(new State(nx, ny, hp, shield, cur.time + 1));
                }
            }
        }

        return -1;
    }
}
