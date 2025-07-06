import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제에서 s -> t 를 하는 방법을 물었지만,,, s -> t 하는 과정에서 많은 문자열이 추가되고, 문자열의 길이가 길어짐에 따라 실행 시간이 오래걸린다.
 * 그래서 t -> s 를 하는 방법으로 t의 길이를 줄여나가는 방식으로 풀었다.
 *
 * 기존 규칙 (s -> t)
 * - 문자열의 뒤에 A를 추가한다.
 * - 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
 *
 * 기존 풀이 (recursion(s, t))
 * if (flag) return;
 * if (s.length() == t.length()){
 *     if (s.equals(t)){
 *         flag = true;
 *     }
 * }
 *
 * recursion(s + 'A', t);
 * String reverse = new StringBuilder(s).reverse().toString();
 * recursion(reverse + 'B', t);
 *
 * 1. s + 'A'를 할때마다 새로운 문자열이 생성되어 비효율 적이다
 * 2. String reverse = new StringBuilder(s).reverse().toString(); 를 할때마다 새로운 문자열을 생성할 뿐더러, 문자열을 뒤집는 과정에서 비효율적이다.
 *
 * 해결 : s -> t 를 하여 문자열의 길이를 늘이기 보단, t -> s 를 하여 규칙의 역순으로 간다
 * 규칙의 역순 (t 기준으로 시행)
 * - 문자열의 뒤에 A를 추가한다 -> 문자열 맨 뒤가 A라면 A를 제거한다.
 * - 문자열의 뒤에 B를 추가한 후 뒤집는다 -> 문자열의 시작이 B라면 문자열을 뒤집고 맨 뒤의 B를 제거한다.
 *
 * tc #1
 * s = A
 * t = BABA
 *
 * 1. t = BAB
 * 2. t = BA
 * 3. t = A
 * t.equals(s) = true
 */
public class boj12919 {
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        recursion(s, t);

        System.out.println(flag ? 1 : 0);
        br.close();
    }

    private static void recursion(String s, String t) {
        if (flag) return;
        if (t.length() == s.length()){
            if (t.equals(s)){
                flag = true;
            }
            return;
        }

        if(t.charAt(t.length()-1) == 'A') {
            recursion(s, t.substring(0, t.length() - 1));
        }
        if (t.charAt(0) == 'B'){
            String reverse = new StringBuilder(t).reverse().toString();
            recursion(s, reverse.substring(0, reverse.length()-1));
        }
    }
}
