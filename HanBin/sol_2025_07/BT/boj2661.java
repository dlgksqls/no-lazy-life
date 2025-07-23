package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1, 2, 3 으로만 이루어진 수열 중에 가장 작은 좋은 수열을 출력해야한다.
 *
 * 나쁜 수열 예시 (동일한 부분 수열이 연속 반복할 시)
 * 1. 33 // 3 반복
 * 2. 32121323 // 21 반복
 * 3. 123123213 // 123 반복
 *
 * 좋은 수열 예시
 * 1. 2
 * 2. 32
 * 3. 32123
 * 4. 1232123
 *
 * 백트래킹으로 순열을 만들었다.
 * 숫자가 뒤에서부터 추가가 되므로, 뒤에서부터 1자리, 2자리, 3자리,, 이런식으로 비교를 하여서
 * 연속된 수열이 나오면 바로 return 하도록 했다
 */
public class boj2661 {
    static int n;
    static String answer;
    static boolean flag = false;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        recursion(sb, 0);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(StringBuilder sb, int depth) {
        if (flag) return;
        if (depth == n){
            answer = sb.toString();
            flag = true;
            return;
        }

        for(int i=1; i<=3; i++){
            sb.append(i);

            if (check(sb)) {
                recursion(sb, depth + 1);
            }

            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean check(StringBuilder sb) {
        int len = sb.length();
        for(int i=1; i<=len/2; i++){
            String prev = sb.substring(len-2*i, len-i);
            String curr = sb.substring(len-i);
            if (prev.equals(curr)) return false;
        }
        return true;
    }
}
