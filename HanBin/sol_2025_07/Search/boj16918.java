package sol_2025_07.Search;

import org.w3c.dom.Attr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj16918 {
    static int r, c, n;
    static int[][] array;
    static boolean[][] exploded;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        array = new int[r][c];

        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<str.length(); j++){
                if (str.charAt(j) == 'O') array[i][j] = 0;
                else array[i][j] = -1;
            }
        }

        for(int i=1; i<=n; i++){
            if (i % 2 == 0) setBomb();
            else {
                exploded = new boolean[r][c];
                simulate();
            }
        }

        for (int[] row : array) {
            for (int col : row) {
                if (col == -1) System.out.print('.');
                else System.out.print('O');
            }
            System.out.println();
        }

        br.close();
    }

    private static void setBomb() {
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                array[i][j] += 1;
            }
        }
    }

    private static void simulate() {
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if (array[i][j] == 2) {
                    exploded[i][j] = true;
                    if (i + 1 < r) exploded[i+1][j] = true;
                    if (i - 1 >= 0 ) exploded[i-1][j] = true;
                    if (j + 1 < c) exploded[i][j+1] = true;
                    if (j - 1 >= 0 ) exploded[i][j-1] = true;
                }
            }
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if (exploded[i][j]) array[i][j] = -1;
                else if (array[i][j] != -1) array[i][j] += 1;
            }
        }
    }
}
