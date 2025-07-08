package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * array를 정렬 (수열마다 가장 작은 값 + 두번째로 작은 값 > 수열 안에서 가장 큰 값 이면 범위가 어떻든 만족하기 때문에)
 * n이 1이거나 2인경우 그냥 return (문제의 조건이 n이 3인 경우부터 삼각 관계 적용이라고 했기 때문)
 *
 * tc #1
 * 7
 * 2 3 4 1 3 4 5
 *
 * array (정렬) => 1 2 3 3 4 4 5
 * i = 0
 * 1 + 2 > 5 (거짓) break
 * 1 + 2 > 4 (거짓) break
 * 1 + 2 > 3 (거짓) break
 *
 * i = 1
 * 2 + 3 > 5 (거짓) break
 * 2 + 3 > 4 (참) 2 + 3 > 4 (참) 2 + 3 > 3 (참) 수열 : 2 3 3 4 4 (길이 5)
 *
 */
public class boj1548 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        if (n == 1 || n == 2){
            System.out.println(n);
            return;
        }

        int maxLength = Integer.MIN_VALUE;

        for(int i=0; i<n-2; i++){
            int len = 2;
            for(int j=i+2; j<n; j++){
                if (array[i] + array[i+1] > array[j]){
                    len ++;
                }
                else break;
            }
            maxLength = Math.max(maxLength, len);
        }

        System.out.println(maxLength);
        br.close();
    }
}
