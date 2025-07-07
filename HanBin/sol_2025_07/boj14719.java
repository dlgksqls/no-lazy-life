package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 땅 하나를 기준으로 왼쪽 벽의 최댓값, 오른쪽 벽의 최댓값을 비교
 * 선택한 땅이 array[i]라 치고, minHeight = Math.min(leftMax, rightMax)를 함
 * array[i] < minHeight 면 해당 땅에 물이 고인다는 의미이므로
 * result += minHeight - array[i]를 해줌
 */
public class boj14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] array = new int[w];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<w; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for(int i=1; i<w-1; i++){
            int leftMax = Integer.MIN_VALUE;
            int rightMax = Integer.MIN_VALUE;

            for(int j=0; j<i; j++){
                leftMax = Math.max(leftMax, array[j]);
            }

            for(int j=i; j<w; j++){
                rightMax = Math.max(rightMax, array[j]);
            }

            int minHeight = Math.min(leftMax, rightMax);
            if (array[i] < minHeight) {
                result += minHeight - array[i];
            }
        }

        System.out.println(result);
        br.close();
    }
}
