package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 브루트포스 문제이다
 * 선수들이 포메이션에 서는 경우의 수(순열)를 모두 계산한다.
 * 단 여기서 해당 포지션에서의 능력이 0인 경우는 pass 한다. (쓸대없는 경우의 수 까지 모두 계산이 되어서 시간 초과가 뜬다)
 * 재귀의 깊이가 11까지 올라가게되면 answer를 갱신한다.
 */

public class boj3980 {
    static int[][] players;
    static List<Integer> list;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc ++){
            players = new int[11][11];
            for(int i=0; i<11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++){
                    players[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            list = new ArrayList<>();
            visited = new boolean[11];
            answer = Integer.MIN_VALUE;

            recursion(0);
            System.out.println(answer);

        }
        br.close();
    }

    private static void recursion(int depth) {
        if (depth == 11){
            int sum = 0;
            for(int i=0; i< list.size(); i++){
                sum += players[list.get(i)][i];
            }
            answer = Math.max(answer, sum);
            return;
        }

        for(int i=0; i<11; i++){
            if (!visited[i] && players[i][depth] > 0){
                list.add(i);
                visited[i] = true;
                recursion(depth + 1);
                visited[i] = false;
                list.remove(list.get(list.size() - 1));
            }
        }
    }
}
