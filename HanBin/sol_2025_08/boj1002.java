package sol_2025_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            double dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

            if (x1 == x2 && y1 == y2 && r1 == r2) {
                System.out.println(-1); // 무한히 많은 점에서 만남 (동심원 + 반지름 동일)
            } else if (dist > r1 + r2 || dist < Math.abs(r1 - r2)) {
                System.out.println(0); // 만나지 않음
            } else if (dist == r1 + r2 || dist == Math.abs(r1 - r2)) {
                System.out.println(1); // 외접 또는 내접
            } else {
                System.out.println(2); // 두 점에서 만남
            }
        }
    }
}
