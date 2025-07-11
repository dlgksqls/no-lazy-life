package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 0부터 모든 수를 탐색하여 321, 950 같이 줄어드는 수를, n번째 줄어드는 수를 찾는 문제
 * 0 ~ 9 맨 앞자리에 나올 수 있는 수를 두고, recursion 함수를 통해 나올 수 있는 수를 list.add 한다
 * 그리고 list를 오름차순으로 정렬 한 후 해당하는 수를 출력한다.
 */

public class boj1174 {
    static int n;
    static ArrayList<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<=9; i++) {
            recursion(i, i);
        }

        Collections.sort(list);

        if (list.size() < n){
            System.out.println(-1);
        }
        else{
            System.out.println(list.get(n - 1));
        }

        br.close();
    }

    private static void recursion(long current, int last) {
        list.add(current);

        for(int i=0; i<last; i++){
            recursion(current * 10 + i, i);
        }
    }
}
