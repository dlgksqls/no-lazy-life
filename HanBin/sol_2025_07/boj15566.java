package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 입력이 너무 복잡했다
 * 연꽃이 N개 개구리가 N마리 연꽃을 잇는 통나무가 M개
 * 연꽃에 대한 개구리의 선호도가 주어지고, 통나무는 몇번 연꽃을 이어주는지, 그리고 통나무 위에서 무슨 대화를 할 수 있는
 *
 * 재귀, 백트래킹으로 모든 경우에 개구리가 연꽃에 올라가는 경우의 수를 구하려 했다. (하지만 이것은 시간초과가 떴다.)
 * 그래서 재귀를 돌기 전 check()로 개구리가 옳은 연꽃에 앉아있는지 확인하고, 문제의 조건에 만족하는 통나무로 연결돼있다면 재귀를 돌도록 했다.
 *
 * 기존의 check() 에서는
 * // 두 개구리가 만족하는 연꽃에 앉았는지
 * if (!flogs[firstFlog].like.contains(wood.a) || !flogs[secondFlog].like.contains(wood.b)) return false;
 *
 * // 두 개구리의 관심도가 같은지
 * if (flogs[firstFlog].talk[wood.t] != flogs[secondFlog].talk[wood.t]) return false;
 *
 * 에 대해서 체크를 했다. 하지만 두 개구리가 만족하는 연꽃에 앉았는지 체크하는 과정에서 정확히 두마리만 체크를 하므로 다른 개구리에 대해서는 체크를 할 수 없었다.
 *
 * check()에서는 통나무에 대해서만 체크하고
 * 개구리를 배치하는데 있어서 본인의 연꽃에만 배치하도록 하였다.
 */
public class boj15566 {
    static class Flog{
        int[] talk = new int[5];
        Set<Integer> like = new HashSet<>();

        public Flog(int food, int interest, int family, int philosophy) {
            talk[1] = food;
            talk[2] = interest;
            talk[3] = family;
            talk[4] = philosophy;
        }
    }
    static class wood{
        int a;
        int b;
        int t;

        public wood(int a, int b, int t) {
            this.a = a;
            this.b = b;
            this.t = t;
        }
    }

    static int n, m;
    static boolean[] visited;
    static Flog[] flogs;
    static wood[] woods;
    static List<Integer> answer;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        flogs = new Flog[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            flogs[i] = new Flog(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            flogs[i].like.add(a);
            flogs[i].like.add(b);
        }

        woods = new wood[m];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            woods[i] = new wood(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        visited = new boolean[n];
        answer = new ArrayList<>();

        recursion(0);

        if (!flag) System.out.println("NO");

        br.close();
    }

    private static void recursion(int depth) {
        if (flag) return;
        if (depth == n){
            flag = true;
            System.out.println("YES");
            for (Integer i : answer) {
                System.out.print((i+1) + " ");
            }
            System.out.println();
            return;
        }

        for(int i=0; i<n; i++){
            if (!visited[i]) {

                if (!flogs[i].like.contains(depth + 1)) {
                    continue; // 개구리 'i'가 연꽃 패드 '(depth + 1)'을 선호하지 않으면 다음 개구리 시도
                }

                visited[i] = true;
                answer.add(i);

                if (check(depth)) {
                    recursion(depth + 1);
                }

                visited[i] = false;
                answer.remove(answer.size() - 1);
            }
        }
    }

    private static boolean check(int depth) {
        for(int i=0; i<m; i++){
            wood wood = woods[i];

            if (wood.a-1 > depth || wood.b-1 > depth) {
                continue;
            }

            Integer firstFlog = answer.get(wood.a-1);
            Integer secondFlog = answer.get(wood.b-1);

            // 두 개구리의 관심도가 같은지
            if (flogs[firstFlog].talk[wood.t] != flogs[secondFlog].talk[wood.t]) return false;
        }
        return true;
    }
}
