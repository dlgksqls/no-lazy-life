package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자열이 사전순으로 가장 빠른 것이 앞으로 오도록 출력해야함
 * 재귀로 문자열을 하나씩 탐색하여 출력하도록 함
 *
 * 초기에 minChar 를 가장 큰 값으로 설정
 * input을 순회하며 사전순으로 가장 빠른 값을 조회 후 방문 처리
 * 방문처리된 문자열을 출력
 *
 * 방문처리된 문자를 기준으로 오른쪽을 먼저 탐색 (사전순으로 가장 빠른 문자가 맨 앞에 와야하기 때문)
 * 왼쪽 탐색
 *
 * tc #1
 * 입력 : BAC
 * 출력 : A, AC, BAC
 * 이런식으로 A가 가장 빠르므로 A가 맨 앞에 오도록 오른쪽부터 순차 출력 하고, 왼쪽을 출력함
 */
public class boj16719 {
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        visited = new boolean[input.length()];
        recursion(input, 0, input.length()-1);
    }

    private static void recursion(String input, int start, int end) {
        if (start > end) return;

        char minChar = 'Z' + 1;
        int minIdx = -1;

        for(int i=start; i<=end; i++){
            if (!visited[i] && input.charAt(i) < minChar){
                minChar = input.charAt(i);
                minIdx = i;
            }
        }

        visited[minIdx] = true;

        for(int i=0; i<input.length(); i++){
            if (visited[i]){
                System.out.print(input.charAt(i));
            }
        }
        System.out.println();

        recursion(input, minIdx + 1, end);
        recursion(input, start, minIdx - 1);
    }
}