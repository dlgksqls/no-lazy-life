package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재귀, 백트레킹 문제로
 * 재귀를 돌기 전에 원래 계란의 내구도를 저장해놓고
 * 연산 후 재귀를 끝마치고 왔을 때 계란의 내구도를 원상복구 시켜 경우의 수를 구하는 문제이다.
 *
 * idx => 부딪칠 계란의 idx
 * 순열을 사용했다. 어떤 순서로 계란을 깨냐에 따라서 최댓값이 달라지기 때문
 *
 * idx로 집은 계란의 내구도가 0 이하라면 아무 행위도 하지 안고 다음 계란을 집는다. (recursion(idx + 1))
 * 그렇지 않다면 자기자신을 제외한 깰 수 있는 계란을 탐색한다.
 * 깰 수 있다면 깨보고 재귀를 한다.
 *
 * 맨 오른쪽 계란을 집을 경우 answer을 update하고 해당 재귀를 마친다.
 */
public class boj16987 {
    static class Egg{
        int s;
        int w;

        public Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }
    static int n;
    static Egg[] eggs;
    static int answer = Integer.MIN_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        eggs = new Egg[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        visited = new boolean[n];
        recursion(0);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(int idx) {
        if (idx == n) {
            int count = 0;
            for (Egg egg : eggs) {
                if (egg.s <= 0){
                    count ++;
                }
            }
            answer = Math.max(answer, count);
            return;
        }

        boolean flag = false;
        if (eggs[idx].s > 0){
            for(int i=0; i<n; i++){
                if (i == idx || eggs[i].s <= 0) continue;

                flag = true;
                int tmp_idx = eggs[idx].s;
                int tmp_i = eggs[i].s;

                eggs[idx].s -= eggs[i].w;
                eggs[i].s -= eggs[idx].w;

                recursion(idx + 1);

                eggs[idx].s = tmp_idx;
                eggs[i].s = tmp_i;
            }
        }

        if (!flag) recursion(idx + 1);
    }
}
