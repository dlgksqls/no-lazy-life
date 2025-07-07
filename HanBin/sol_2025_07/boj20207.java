package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 각 날짜별로 일정이 겹친다면 해당 날짜 idx에 ++를 함
 * 일정들이 포함된 날짜의 직사각형의 넓이를 구하면 되므로,, width와 height를 구하여 width * height를 구하면 됨
 * 글로 설명하려니 어려워서,,, 여기까지
 */
public class boj20207 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] day = new int[366];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for(int j=s; j<=e; j++){
                day[j] ++;
            }
        }

        int result = 0;
        int width = 0;
        int height = 0;
        for(int i=1; i<=365; i++){
            if (day[i] != 0){
                width ++;
                height = Math.max(height, day[i]);
            }
            else {
                result += width * height;
                width = 0;
                height = 0;
            }
        }

        result += width * height;
        System.out.println(result);
        br.close();
    }
}
