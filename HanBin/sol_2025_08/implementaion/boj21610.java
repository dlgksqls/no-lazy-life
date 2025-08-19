package sol_2025_08.implementaion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj21610 {
    static int n, m;
    static int[][] array;
    static boolean[][] resCloud;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] crossX = {-1, 1, 1, -1};
    static int[] crossY = {1, 1, -1, -1};
    static List<int[]> clouds = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        resCloud = new boolean[n+1][n+1];
        array = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                int water = Integer.parseInt(st.nextToken());
                array[i][j] = water;
            }
        }

        // 0. 초기 구름 위치 저장
        clouds.add(new int[]{n, 1});
        clouds.add(new int[]{n, 2});
        clouds.add(new int[]{n-1, 1});
        clouds.add(new int[]{n-1, 2});

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // 1. 모든 구름이 d방향으로 s만큼 이동
            for (int[] cloud : clouds) {
                int x = cloud[0];
                int y = cloud[1];

                int nx = (x + dx[d] * s) % n;
                int ny = (y + dy[d] * s) % n;

                if (nx <= 0) nx += n;
                if (ny <= 0) ny += n;

                array[nx][ny] += 1;
                resCloud[nx][ny] = true;

                // 2. 구름별 이동 좌표 update
                cloud[0] = nx;
                cloud[1] = ny;
            }

            // 3. 구름 복사 버그
            for (int[] cloud : clouds) {
                for(int dir=0; dir<4; dir++){
                    int nx = cloud[0] + crossX[dir];
                    int ny = cloud[1] + crossY[dir];

                    if (nx < 1 || nx > n || ny < 1 || ny > n) continue;
                    if (array[nx][ny] > 0) array[cloud[0]][cloud[1]] += 1;
                }
            }

            // 4. 이전 구름 초기화 및 새 구름 넣기
            clouds.clear();
            for(int r=1; r<=n; r++) {
                for (int c = 1; c <= n; c++) {
                    if (!resCloud[r][c] && array[r][c] >= 2) {
                        array[r][c] -= 2;
                        clouds.add(new int[]{r, c});
                    }
                    resCloud[r][c] = false;
                }
            }
        }

        int answer = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                answer += array[i][j];
            }
        }

        System.out.println(answer);
        br.close();
    }
}
