package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * bfs를 두번 써야하는 문제
 * 첫 번쨰 bfs : 불이 퍼지는 시간을 fireTime에 저장 (이 떄 불은 하나에서 출발한다는 고정관념은 버리자...)
 * 두 번째 bfs : 지훈이가 각 포인트에 도달하는 시간, 그리고 탈출지에 도착했을 때 불보다 일찍 도착했는지 검사 후 return
 */
public class boj4179 {
    static int r, c;
    static char[][] array;
    static Queue<int[]> fires = new LinkedList<>();
    static int[][] fireTime;
    static int[][] jiTime;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        array = new char[r][c];
        jiTime = new int[r][c];
        fireTime = new int[r][c];

        int[] initJi = {};
        for(int i=0; i<r; i++){
            array[i] = br.readLine().toCharArray();
            for(int j=0; j<c; j++){
                if (array[i][j] == '#') continue;
                if (array[i][j] == 'J') {
                    if (i == 0 || i == r - 1 || j == 0 || j == c - 1) {
                        System.out.println(1);
                        br.close();
                        return;
                    }
                    jiTime[i][j] = 0;
                    initJi = new int[]{i, j};
                }
                else if (array[i][j] == 'F') {
                    fireTime[i][j] = 0;
                    fires.add(new int[]{i, j});
                }
                else {
                    fireTime[i][j] = Integer.MAX_VALUE;
                    jiTime[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        bfs_fire();
        int answer = bfs_ji(initJi[0], initJi[1]);
        System.out.println((answer != -1) ? answer : "IMPOSSIBLE");

        br.close();
    }

    private static void bfs_fire() {
        while (!fires.isEmpty()){
            int[] poll = fires.poll();
            int nowX = poll[0];
            int nowY = poll[1];

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (array[nx][ny] == '#') continue;
                if (fireTime[nx][ny] != Integer.MAX_VALUE) continue;

                fireTime[nx][ny] = fireTime[nowX][nowY] + 1;
                fires.add(new int[]{nx, ny});
            }
        }
    }

    private static int bfs_ji(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];

            if (nowX == 0 || nowX == r-1 || nowY == 0 || nowY == c-1){
                if (jiTime[nowX][nowY] < fireTime[nowX][nowY]) return jiTime[nowX][nowY] + 1;
            }

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (array[nx][ny] == '#') continue;
                if (jiTime[nx][ny] != Integer.MAX_VALUE) continue;

                jiTime[nx][ny] = jiTime[nowX][nowY] + 1;
                queue.add(new int[]{nx, ny});
            }
        }

        return -1;
    }
}
