package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj13549 {
    static int n, k;
    static int[] map = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Arrays.fill(map, Integer.MAX_VALUE);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        map[n] = 0;
        while(!queue.isEmpty()){
            int now = queue.poll();

            if (now == k) break;

            if (now * 2 >= 0 && now * 2 < 100001){
                if (map[now*2] > map[now]){
                    map[now*2]= map[now];
                    queue.add(now * 2);
                }
            }
            if (now + 1 >= 0 && now + 1 < 100001) {
                if (map[now+1] > map[now] + 1){
                    map[now+1] = map[now] + 1;
                    queue.add(now + 1);
                }
            }
            if (now - 1 >= 0 && now - 1 < 100001){
                if (map[now-1] > map[now] + 1){
                    map[now-1] = map[now] + 1;
                    queue.add(now - 1);
                }
            }
        }

        System.out.println(map[k]);
        br.close();
    }
}
