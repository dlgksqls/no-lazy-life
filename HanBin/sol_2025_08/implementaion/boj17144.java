package sol_2025_08.implementaion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj17144 {
    static int r, c, t;
    static int[][] array;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] airTopDx = {0, -1, 0, 1};
    static int[] airBottomDx = {0, 1, 0, -1};
    static int[] airDy = {1, 0, -1, 0};
    static List<int[]> airs = new ArrayList<>();
    static List<int[]> dirties = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        array = new int[r][c];

        // 공기청정기 : -1, 나머지 : 미세먼지 양
        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if (array[i][j] == -1) airs.add(new int[]{i, j});
                else dirties.add(new int[]{i, j});
            }
        }

        for(int T=0; T<t; T++) {
            simulation();
            dirties.clear();
            for(int i=0; i<r; i++){
                for(int j=0; j<c; j++){
                    if (array[i][j] != -1) dirties.add(new int[]{i, j});
                }
            }
        }
        
        int answer = 0;
        for (int[] row : array) {
            for (int i : row) {
                if (i == -1) continue;
                answer += i;
            }
        }

        System.out.println(answer);
        br.close();
    }

    private static void simulation() {
        // 1. 모든 미세먼지 발산 (동시에)
        int[][] tmpArray = new int[r][c];
        for (int[] dirty : dirties) {
            int dirtyX = dirty[0];
            int dirtyY = dirty[1];

            int cnt = 0;
            for(int dir=0; dir<4; dir++){
                int nx = dirtyX + dx[dir];
                int ny = dirtyY + dy[dir];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (array[nx][ny] == -1) continue;

                cnt ++;
                tmpArray[nx][ny] += array[dirtyX][dirtyY] / 5;
            }

            array[dirtyX][dirtyY] -= (array[dirtyX][dirtyY] / 5) * cnt;
        }

        // 확산된 먼지를 원본 배열에 더하기
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                array[i][j] += tmpArray[i][j];
            }
        }

        // 2. 공기청정기 사용 (첫 번째 루프 : 위, 두 번째 루프 : 아래)
        int cnt = 1;
        for (int[] air : airs) {
            int airX = air[0];
            int airY = air[1];

            if (cnt == 1) {
                airX -= 1;
                while(airX > 0 && airY == 0){
                    array[airX][airY] = array[airX-1][airY];
                    airX --;
                }
                while(airX == 0 && airY < c-1){
                    array[airX][airY] = array[airX][airY+1];
                    airY ++;
                }
                while(airX < air[0] && airY == c-1){
                    array[airX][airY] = array[airX+1][airY];
                    airX ++;
                }
                while(airX == air[0] && airY > 0){
                    array[airX][airY] = array[airX][airY-1];
                    airY --;
                }

                array[airX][airY+1] = 0;
                cnt ++;
            }
            else if (cnt == 2){
                airX += 1;
                while(airX < r-1 && airY == 0){
                    array[airX][airY] = array[airX+1][airY];
                    airX ++;
                }
                while(airX == r-1 && airY < c-1){
                    array[airX][airY] = array[airX][airY+1];
                    airY ++;
                }
                while(airX > air[0] && airY == c-1){
                    array[airX][airY] = array[airX-1][airY];
                    airX --;
                }
                while(airX == air[0] && airY > 0){
                    array[airX][airY] = array[airX][airY-1];
                    airY --;
                }
                cnt = 1;
            }
            array[airX][airY+1] = 0;
        }
    }
}
