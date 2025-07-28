package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17836 {
    static int N, M, T;
    static int[][] array;
    static int[][] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean isGram = false;
    static int gramDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        array = new int[N + 1][M + 1];
        dist = new int[N + 1][M + 1];
        int gram_x = 0;
        int gram_y = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
                if (array[i][j] == 2) {
                    gram_x = i;
                    gram_y = j;
                }
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[1][1] = 0;

        int no_gram = bfs(1, 1);

        dist[1][1] = 0;
        int gram = Integer.MAX_VALUE;

        if (isGram) {
            gram = gramDist + (N - gram_x) + (M - gram_y); // 거리 구하기 공식
        }

        int answer = Math.min(gram, no_gram);

        System.out.println((answer <= T ? answer : "Fail"));
        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_x = now[0];
            int now_y = now[1];

            if (now_x == N && now_y == M) return dist[N][M];
            if (array[now_x][now_y] == 2) {
                isGram = true;
                gramDist = dist[now_x][now_y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = now_x + dx[i];
                int ny = now_y + dy[i];

                if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
                if (array[nx][ny] == 1) continue;
                if (dist[nx][ny] != Integer.MAX_VALUE) continue;

                dist[nx][ny] = dist[now_x][now_y] + 1;
                queue.add(new int[]{nx, ny});
            }
        }

        return dist[N][M];
    }
}
