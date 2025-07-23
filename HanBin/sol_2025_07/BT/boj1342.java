package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1342 {
    static char[] input;
    static StringBuilder sb = new StringBuilder();
    static int answer = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().toCharArray();

        visited = new boolean[input.length];
        Arrays.sort(input);

        recursion(sb);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(StringBuilder sb) {
        if (sb.length() == input.length){
            answer ++;
            return;
        }

        for(int i=0; i<input.length; i++){
            if (visited[i]) continue;
            if (i > 0 && input[i] == input[i-1] && !visited[i-1]) continue;
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == input[i]) continue;

            visited[i] = true;
            sb.append(input[i]);
            recursion(sb);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
}
