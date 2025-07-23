package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1062 {
    static int n, k;
    static int[] spell = new int[26];
    static int answer = Integer.MIN_VALUE;
    static String[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (k < 5){
            System.out.println(0);
            return;
        }

        char[] init = {'a', 'c', 'i', 'n', 't'};
        k -= 5;
        for (char c : init) {
            spell[c - 'a'] = 1;
        }

        input = new String[n];
        for(int i=0; i<n; i++){
            String str = br.readLine();
            input[i] = str.substring(4, str.length() - 4);
        }

        recursion(0);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(int idx) {
        if (k == 0) {
            int count = 0;
            for (String s : input) {
                boolean flag = true;
                for(int i=0; i<s.length(); i++){
                    if (spell[s.charAt(i) - 'a'] == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) count ++;
            }
            answer = Math.max(answer, count);
            return;
        }

        for(int i=idx; i<26; i++){
            if (spell[i] != 0) continue;
            spell[i] ++;
            k --;
            recursion(idx + 1);
            k ++;
            spell[i] --;
        }
    }
}