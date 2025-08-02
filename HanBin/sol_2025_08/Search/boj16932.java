package sol_2025_08.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj16932 {
    static int n, m;
    static int[][] array;
    static int[][] group;
    static Map<Integer, Integer> groupSize;
    static int groupNum = 1;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        group = new int[n][m];
        groupSize = new HashMap<>();
        // 그룹화
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if (array[i][j] == 1 && group[i][j] == 0){
                    int size = bfs(i, j, groupNum);
                    groupSize.put(groupNum, size);
                    groupNum += 1;
                }
            }
        }

        // 0 -> 1 해보며 최댓값 갱신
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if (array[i][j] == 0){
                    int sum = 1; // 자기 자신 바꾼 것 (0 -> 1)
                    Set<Integer> set = new HashSet<>();

                    for(int dir=0; dir<4; dir++){
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        if (array[nx][ny] == 0) continue;
                        if (group[nx][ny] != 0) set.add(group[nx][ny]);
                    }

                    for (Integer num : set) {
                        sum += groupSize.get(num);
                    }

                    answer = Math.max(answer, sum);
                }
            }
        }

        System.out.println(answer);
        br.close();
    }

    private static int bfs(int x, int y, int groupNum) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        group[x][y] = groupNum;
        int size = 1;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];

            for(int dir=0; dir<4; dir++){
                int nx = nowX + dx[dir];
                int ny = nowY + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (array[nx][ny] != 1) continue;
                if (group[nx][ny] != 0) continue;

                size += 1;
                group[nx][ny] = groupNum;
                queue.add(new int[]{nx, ny});
            }
        }

        return size;
    }
}
