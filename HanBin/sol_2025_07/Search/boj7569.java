package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj7569 {
    static int M, N, H;
    static int[][][] boxes;
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dy = {1, 0, -1, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        boxes = new int[H][N][M];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    boxes[i][j][k] = Integer.parseInt(st.nextToken());
                    if (boxes[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int h = poll[0];
            int n = poll[1];
            int m = poll[2];

            for (int i = 0; i < 6; i++) {
                int nz = h + dz[i];
                int nx = n + dx[i];
                int ny = m + dy[i];

                if (nz < 0 || nz >= H || nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (boxes[nz][nx][ny] != 0) continue;

                boxes[nz][nx][ny] = boxes[h][n][m] + 1;
                queue.add(new int[]{nz, nx, ny});
            }
        }

        int answer = 0;
        for (int[][] box : boxes) {
            for (int[] row : box) {
                for (int i : row) {
                    if (i == 0) {
                        System.out.println(-1);
                        br.close();
                        return;
                    }
                    answer = Math.max(answer, i);
                }
            }
        }

        System.out.println(answer - 1);

        br.close();
    }
}
