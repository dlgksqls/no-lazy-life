package sol_2025_08.implementaion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj20056 {
    static int n, m, k;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<FireBall> fireBalls;
    static List<FireBall>[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        fireBalls = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBalls.add(new FireBall(r, c, m, s, d));
        }

        for(int T=0; T<k; T++) {
            simulation();
        }

        int answer = 0;
        for (FireBall fireBall : fireBalls) {
            answer += fireBall.m;
        }

        System.out.println(answer);
        br.close();
    }

    private static void simulation() {
        map = new ArrayList[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                map[i][j] = new ArrayList<>();
            }
        }

        // 1. 불 합치기
        for (FireBall fireBall : fireBalls) {
            int nx = (fireBall.r + dx[fireBall.d] * fireBall.s) % n;
            int ny = (fireBall.c + dy[fireBall.d] * fireBall.s) % n;

            if (nx <= 0) nx += n;
            if (ny <= 0) ny += n;

            map[nx][ny].add(new FireBall(nx, ny, fireBall.m, fireBall.s, fireBall.d));
        }

        // 2. 불 나누기
        List<FireBall> newFireBalls = new ArrayList<>();
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if (map[i][j].isEmpty()) continue;

                if (map[i][j].size() == 1) newFireBalls.add(map[i][j].get(0));
                else{
                    int sumM = 0;
                    int sumS = 0;
                    int cnt = map[i][j].size();
                    boolean allEven = true;
                    boolean allOdd = true;

                    for (FireBall fireBall : map[i][j]) {
                        sumM += fireBall.m;
                        sumS += fireBall.s;

                        if (fireBall.d % 2 == 0) allOdd = false;
                        else allEven = false;
                    }

                    sumM = sumM / 5;
                    sumS = sumS / cnt;

                    if (sumM == 0) continue;

                    int[] newDir = allEven || allOdd ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                    for (int d : newDir) {
                        newFireBalls.add(new FireBall(i, j, sumM, sumS, d));
                    }
                }
            }
        }
        fireBalls = newFireBalls;
    }

    static class FireBall{
        int r;
        int c;
        int m;
        int s;
        int d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
