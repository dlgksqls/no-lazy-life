package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1553 {
    static int[][] map;
    static boolean[][] used;
    static boolean[][] visited;
    static int total;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[8][7];
        used = new boolean[7][7];
        visited = new boolean[8][7];

        for(int i=0; i<8; i++){
            String str = br.readLine();
            for(int j=0; j<7; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        recursion(0, 0);
        System.out.println(total);
        br.close();
    }

    private static void recursion(int r, int c) {
        if (r == 8){
            total ++;
            return;
        }
        if (c == 7){
            recursion(r+1, 0);
            return;
        }
        if (visited[r][c]){
            recursion(r, c+1);
            return;
        }

        visited[r][c] = true;
        int num = map[r][c];

        if (c+1<7 && !visited[r][c+1]) place(num, map[r][c+1], r, c, r, c+1);
        if (r+1<8 && !visited[r+1][c]) place(num, map[r+1][c], r, c, r+1, c);

        visited[r][c] = false;
    }

    private static void place(int a, int b, int r, int c, int r1, int c1) {
        if (used[a][b]) return;
        used[a][b] = true;
        used[b][a] = true;

        visited[r1][c1] = true;
        recursion(r, c+1);
        visited[r1][c1] = false;

        used[a][b] = false;
        used[b][a] = false;
    }
}
