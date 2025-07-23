package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 외판원 순회 => 시작점부터 출발해서 여러 도시를 거쳐서 다시 출발점으로 돌아온다.
 * ex) n == 5
 * 1 -> 2 -> 3 -> 4 -> 5 -> 1
 *
 * 출발은 어디에서 하든 상관없다. 하지만 외판원 순회가 끝났을 때 가장 최소의 값을 지불할 때의 값
 *
 * recursion() 재귀를 통해 history (방문한 도시)를 채운다.
 * 재귀 한 후에는 모든 도시를 순회 (순열) 하기위해 history.remove(history.size() - 1)을 해준다.
 * 재귀의 깊이 (depth) == city.length 가 됐다면 모든 도시를 순회한 것이므로 값을 계산한다
 * 여기서 도시간의 거리가 0이라면 갈 수 없다는 뜻이므로 return 을 해준다.
 *
 * 마지막에 마지막 도시에서 시작 도시로 가는 값을 더해준다.
 * 여기서도 역시 거리가 0이라면 그냥 return 한다.
 */

public class boj10971 {
    static ArrayList<Integer>[] city;
    static ArrayList<Integer> history;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        city = new ArrayList[n];
        for(int i=0; i<n; i++){
            city[i] = new ArrayList<>();
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int value = Integer.parseInt(st.nextToken());
                city[i].add(value);
            }
        }

        visited = new boolean[n];
        history = new ArrayList<>();
        recursion(0);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(int depth) {
        if (depth == city.length){
            int val = 0;
            int now = history.get(0);
            for(int i=1; i<history.size(); i++){
                int cost = city[now].get(history.get(i));
                if (cost == 0) return; // 길이 없다면 return
                val += cost;
                now = history.get(i);
            }

            int returnCost = city[now].get(history.get(0));
            if (returnCost == 0) return; // 돌아오는 길이 없다면 return
            val += returnCost;
            answer = Math.min(answer, val);
            return;
        }

        for(int i=0; i<n; i++){
            if (!visited[i]){
                visited[i] = true;
                history.add(i);
                recursion(depth + 1);
                visited[i] = false;
                history.remove(history.size()-1);
            }
        }
    }
}
