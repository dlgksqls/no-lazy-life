package sol_2025_09.implementaion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj15685 {
    static int n;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        visited = new boolean[101][101];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragonCurve(x, y, d, g);
        }

        System.out.println(count());

        br.close();
    }

    private static int count() {
        int answer = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if (visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]){
                    answer ++;
                }
            }
        }

        return answer;
    }

    private static void dragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for(int i=0; i<g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        visited[x][y] = true;
        for (Integer direction : directions) {
            x += dx[direction];
            y += dy[direction];
            visited[x][y] = true;
        }
    }
}
