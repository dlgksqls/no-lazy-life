package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj2580 {
    static int[][] array = new int[9][9];
    static List<int[]> blank = new ArrayList<>();
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
                if (array[i][j] == 0) blank.add(new int[]{i, j});
            }
        }

        recursion(0);

        for (int[] row : array) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        br.close();
    }

    private static void recursion(int depth) {
        if (flag) return;

        if (depth == blank.size()){
            flag = true;
            return;
        }

        int x = blank.get(depth)[0];
        int y = blank.get(depth)[1];

        for(int num=1; num<=9; num++){
            if (check(x, y, num)){
                array[x][y] = num;
                recursion(depth + 1);
                if (flag) return;
                array[x][y] = 0;
            }
        }
    }

    private static boolean check(int x, int y, int num) {
        for(int i=0; i<9; i++){
            if(array[x][i] == num) return false;
        }
        for(int i=0; i<9; i++){
            if(array[i][y] == num) return false;
        }

        int row = (x / 3) * 3;
        int col = (y / 3) * 3;

        for(int i = row; i < row + 3; i ++){
            for(int j = col; j < col + 3; j ++){
                if (array[i][j] == num) return false;
            }
        }
        return true;
    }
}
