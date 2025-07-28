package sol_2025_07.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class boj2668 {
    static int n;
    static int[] array;
    static List<Integer> final_list = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        array = new int[n+1];
        visited = new boolean[n+1];

        for(int i=1; i<=n; i++){
            array[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=n; i++) {
            visited = new boolean[n+1];
            recursion(i, i);
        }

        Collections.sort(final_list);
        System.out.println(final_list.size());
        for (Integer i : final_list) {
            System.out.println(i);
        }

        br.close();
    }

    private static void recursion(int start, int now) {
        if(!visited[now]){
            visited[now] = true;
            recursion(start, array[now]);
        }
        else if (start == now){
            final_list.add(start);
        }
    }
}
