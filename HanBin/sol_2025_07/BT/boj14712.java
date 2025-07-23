package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * boolean[][] array를 채우는데, 채운 것이 연속으로 2 * 2가 되면 안된다
 * ex) 2 * 2 array
 *
 * O O => 불가능  O X => 가능
 * O O          O O
 *
 * 재귀를 사용한다.
 * 첫번째 방법
 * recursion() 메서드 : array의 처음부터 시작하여 (0, 0) 1. 다음 칸에 네모를 채우지 않는 방법, 2. 다음칸을 검사하여 2 * 2가 만족하지 않는다면 다음 칸을 채우는 방법
 * x == n (마지막 칸까지 조사한 후 다음 칸 x => n) 이 되면 이 재귀는 가능한 재귀라는 뜻이므로 ++
 *
 * 두번째 방법 (오래 걸림)
 * recursion() 메서드 : idx를 사용하여 x = idx / m, y = idx % m 이라는 것을 이용하여
 * 1. 다음 칸에 네모를 채우지 않는 방법, 2. 채우는 방법
 * 이렇게 하여 네모를 채우는 모든 경우의 수를 구한다.
 * 그 후 idx = n * m 이면 모든 칸을 다 조사한 것이기에 검사를 진행한다.
 */
public class boj14712 {
    static int n, m;
    static boolean[][] array;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new boolean[n][m];
        recursion(0, 0);
//        recursion(0);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(int x, int y) {
        if (x == n){
            answer ++;
            return;
        }

        int nextX = x;
        int nextY = y+1;

        if (nextY == m){
            nextX += 1;
            nextY = 0;
        }

        recursion(nextX, nextY);

        array[x][y] = true;
        if (!check(x, y)){
            recursion(nextX, nextY);
        }
        array[x][y] = false;
    }

    private static boolean check(int x, int y) {
        if (x >= 1 && y >= 1){
            return array[x-1][y-1] && array[x-1][y] && array[x][y-1];
        }
        return false;
    }

//    private static void recursion(int idx){
//        if (idx == n * m){
//            if (!check()) answer ++;
//            return;
//        }
//
//        int x = idx / m;
//        int y = idx % m;
//
//        recursion(idx + 1);
//
//        array[x][y] = true;
//        recursion(idx + 1);
//        array[x][y] = false;
//    }
//
//    private static boolean check(){
//        for(int i=1; i<n; i++){
//            for(int j=1; j<m; j++){
//                if (array[i][j]) {
//                    if (array[i - 1][j - 1] && array[i - 1][j] && array[i][j - 1] && array[i][j]) return true;
//                }
//            }
//        }
//        return false;
//    }
//
}
