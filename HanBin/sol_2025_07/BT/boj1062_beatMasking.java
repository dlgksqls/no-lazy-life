package sol_2025_07.BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1062_beatMasking {
    static int n, k;
    static int[] words;
    static int answer = Integer.MIN_VALUE;
    // a, c, i, n, t 는 무조건 배워야하므로 비트 마킹
    static int init =
            (1 << ('a' - 'a')) |
            (1 << ('c' - 'a')) |
            (1 << ('i' - 'a')) |
            (1 << ('n' - 'a')) |
            (1 << ('t' - 'a'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (k < 5){
            System.out.println(0);
            return;
        }

        words = new int[n];
        for(int i=0; i<n; i++){
            String str = br.readLine();
            int mask = 0;
            for (char c : str.toCharArray()) {
                mask |= (1 << (c - 'a')); // 받아온 글자에서 새롭게 배우는 단어 마킹 후 단어 배열에 넣어주기
            }
            words[i] = mask;
        }

        recursion(0, 0, init);

        System.out.println(answer);
        br.close();
    }

    private static void recursion(int idx, int depth, int mask) {
        if (depth == k - 5) {
            int count = 0;
            for (int word : words) {
                if ((word & mask) == word){ // 배운 단어가 모두 포함된 문장이라면 count ++
                    count ++;
                }
            }
            answer = Math.max(answer, count);
            return;
        }

        for(int i=idx; i<26; i++){ // 순서가 상관 없으므로 조합을 이용해야함
            if ((mask & (1 << i)) != 0) continue; // 만약 배운 단어라면 재귀를 돌지 않고 continue
            recursion(i + 1, depth + 1, mask | (1 << i)); // 배우지 않은 단어라면 새롭게 배워주기
        }
    }
}