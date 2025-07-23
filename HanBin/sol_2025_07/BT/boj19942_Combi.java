package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj19942_Combi {
    static class InGre{
        int dan;
        int ji;
        int tan;
        int bi;
        int price;

        public InGre(int dan, int ji, int tan, int bi, int price) {
            this.dan = dan;
            this.ji = ji;
            this.tan = tan;
            this.bi = bi;
            this.price = price;
        }
    }
    static int n;
    static InGre[] ingres;
    static List<Integer> best;
    static List<Integer> list;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static int mp;
    static int mf;
    static int ms;
    static int mv;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        ingres = new InGre[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            ingres[i] = new InGre(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        best = new ArrayList<>();
        list = new ArrayList<>();
        recursion(0, 0, 0, 0, 0, 0);

        if (!best.isEmpty()){
            System.out.println(answer);
            for (Integer i : best) {
                System.out.print(i + " ");
            }
        }
        else System.out.println(-1);
        br.close();
    }

    private static void recursion(int idx, int sum, int p, int f, int s, int v) {
        if (p >= mp && f >= mf && s >= ms && v >= mv){
            if (answer > sum){
                answer = sum;
                best = new ArrayList<>(list);
            }
            return;
        }

        if (idx == n){
            return;
        }

        for(int i=idx; i<n; i++){
            if (!visited[i]){
                visited[i] = true;
                list.add(i+1);
                recursion(i+1,
                        sum+ingres[i].price,
                        p+ingres[i].dan,
                        f+ingres[i].ji,
                        s+ingres[i].tan,
                        v + ingres[i].bi);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
