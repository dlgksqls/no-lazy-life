package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 주어진 영단어를 기반으로 만들 수 있는 중복되지 않은 글자를 출력한다.
 * 재귀를 사용했다.
 *
 * 처음에 Set을 사용하여 중복처리를 해줬는데, 메모리 초과가 떴다.
 * 그래서 StringBuilder를 사용하여 그때그때 바로 출력하도록 해줬다.
 *
 * 주어진 영단어를 char[] 로 바꿨고, 오름차순으로 정렬해줬다. 그래야지 사전순으로 출력이 가능하기 때문이다.
 * 그리고 나올 수 있는 경우의 수를 순열로 처리해줬다.
 * 여기서 중복 처리를 해줘야하는데, prev = array[i]; 이걸 통해서 한 분기에 같은 단어가 나오지 않도록 하였다.
 */

public class boj6443 {
    static boolean[] visited;
    static char[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc ++){
            array = br.readLine().toCharArray();
            visited = new boolean[array.length];

            Arrays.sort(array);

            recursion(0, new StringBuilder());

        }
        br.close();
    }

    private static void recursion(int depth, StringBuilder sb) {
        if (depth == array.length){
            System.out.println(sb);
            return;
        }

        char prev = 0;
        for(int i=0; i<array.length; i++){
            if (!visited[i] && prev != array[i]){
                visited[i] = true;
                sb.append(array[i]);
                recursion(depth + 1, sb);
                visited[i] = false;
                sb.deleteCharAt(sb.length()-1);
                prev = array[i];
            }
        }
    }
}
