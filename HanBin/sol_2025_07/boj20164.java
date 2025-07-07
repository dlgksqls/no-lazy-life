package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * * 수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.
 * 1. 수가 한 자리이면 더 이상 아무것도 하지 못하고 종료한다.
 * 2. 수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.
 * 3. 수가 세 자리 이상이면 임의의 위치에서 끊어서 3개의 수로 분할하고, 3개를 더한 값을 새로운 수로 생각한다.
 */
public class boj20164 {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        // 숫자를 새로 만들어서 새로 연산해야하므로 재귀를 이용
        // recursion(숫자, 홀수의 갯수)
        recursion(input, 0);
        System.out.println(min + " " + max);
        br.close();
    }

    private static void recursion(String input, int cnt) {
        if (input.length() == 1){ // 1. 숫자의 길이가 1이면 최댓값, 최솟값 업데이트하여 return
            min = Math.min(min, cnt + count(input));
            max = Math.max(max, cnt + count(input));
            return;
        }

        if (input.length() == 2) { // 2. 숫자의 길이가 2이면 각 자리의 숫자 값을 더한 상태로 새로운 연산 진행한다.
            recursion(Integer.toString(input.charAt(0) - '0' + input.charAt(1) - '0'), cnt + count(input));
        }
        else {
            // 3. 숫자의 길이가 3이상이면 숫자를 세개로 나누어서 진행
            for(int i=1; i<input.length()-1; i++){
                for(int j=i+1; j<input.length(); j++){
                    int first = Integer.parseInt(input.substring(0, i)); // 첫번째 구간
                    int second = Integer.parseInt(input.substring(i, j)); // 두번째 구간
                    int third = Integer.parseInt(input.substring(j, input.length())); // 세번째 구간

                    recursion(Integer.toString(first + second + third), cnt + count(input));
                }
            }
        }
    }

    // 홀수의 갯수 연산
    private static int count(String input) {
        int count = 0;
        for(int i=0; i<input.length(); i++){
            if ((int)(input.charAt(i)) % 2 == 1){
                count ++;
            }
        }

        return count;
    }
}
