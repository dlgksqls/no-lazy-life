package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 샘터가 있는 곳을 set과 queue에 넣어주고 bfs를 돌림
 * set에 넣어주는 이유는 방문 처리를 하기 위함
 * 문제의 범위가 너무 커서 list에 넣으니 탐색하는데 시간이 오래 걸려 시간 초과가 뜸 set.contain O(1), list.contain O(n)
 *
 * 샘터를 기준으로 양 옆으로 집이 퍼져나가면 됨
 * 만약 방문하지 않은 곳이라면 if(!set.contain(water or house)) queue에 추가하여 양옆으로 퍼지도록 하면 됨
 */
public class boj18513 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int water = Integer.parseInt(st.nextToken());
            visited.add(water);
            queue.add(new int[]{water, 0});
        }

        long answer = 0;
        int[] dir = {-1, 1};
        while(!queue.isEmpty() && k > 0){
            int[] poll = queue.poll();
            int now = poll[0];
            int dist = poll[1];

            for(int i=0; i<2; i++){
                int nDir = now + dir[i];

                if (!visited.contains(nDir)){
                    visited.add(nDir);
                    answer += dist;
                    k --;
                    queue.add(new int[]{nDir, dist + 1});
                    if (k == 0) break;
                }
            }
        }

        System.out.println(answer);

        br.close();
    }
}
