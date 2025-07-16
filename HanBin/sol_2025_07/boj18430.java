package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2차원 배열에서 3개를 선택하여 ㄱ자 모앙의 부메랑을 만들어야한다.
 * 이 또한 3개를 선택하는 모든 경우의 수를 놓고 재귀를 진행하니 시간초과가 떴다.
 *
 * 그래서 dx, dy를 두어 필요한 움직임만 취한 채로 재귀를 진행했다.
 * 여기서도 모든 인덱스를 돌며 원소를 선택하면 시간초과가 뜨므로
 * idx / m : 행
 * idx % m : 열
 *
 * 이렇게 인덱스를 결정한 후 검사를 했다.
 */
public class boj18430 {
    static int n, m;
    static int[][] array;
    static boolean[][] visited;
    static int[][] dx = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int[][] dy = {{-1, 0}, {-1, 0}, {0, 1}, {0, 1}};
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(0, 0);

        System.out.println(answer);
         br.close();
    }

    private static void recursion(int idx, int sum) {
        if (idx == n * m) {
            answer = Math.max(answer, sum);
            return;
        }

        int x = idx / m; // 행
        int y = idx % m; // 열

        if (!visited[x][y]) {
            for (int dir = 0; dir < 4; dir++) {
                int x1 = x + dx[dir][0];
                int y1 = y + dy[dir][0];
                int x2 = x + dx[dir][1];
                int y2 = y + dy[dir][1];

                if (isValid(x1, y1) && isValid(x2, y2) && !visited[x1][y1] && !visited[x2][y2]) {
                    if (!visited[x1][y1] && !visited[x2][y2]) {
                        visited[x][y] = true;
                        visited[x1][y1] = true;
                        visited[x2][y2] = true;

                        recursion(idx + 1, sum + array[x][y] * 2 + array[x1][y1] + array[x2][y2]);

                        visited[x][y] = false;
                        visited[x1][y1] = false;
                        visited[x2][y2] = false;
                    }
                }
            }
        }
        recursion(idx + 1, sum);
    }

    private static boolean isValid(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) return true;
        return false;
    }
}
