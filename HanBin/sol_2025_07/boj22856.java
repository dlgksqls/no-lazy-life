package sol_2025_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 유사 중위 순회 => root 부터 시작하여 마지막 노드에서 순회를 마침
 * 그 동안에 이동 횟수를 count하여 return 하기
 *
 * inorder 함수에서 앞으로 가는 경우, 돌아오는 경우를 총 두번 ++ 했음 (move : 총 이동횟수)
 *
 * private static void inorder(int current) {
 *       if (current == -1) return;
 *
 *       if (tree[current].left != -1){ // 왼쪽을 순회할 수 있다면 순회하기
 *           move ++; // 순회하러 들어갈 때 ++
 *           inorder(tree[current].left);
 *           move ++; // 순회하고 root로 돌아갈 때 ++
 *       }
 *       last = current;
 *       if (tree[current].right != -1){
 *           move ++;
 *           inorder(tree[current].right);
 *           move ++;
 *       }
 *    }
 *
 * 중위순회를 하면 결국 root로 돌아감
 * 하지만 유사중위순회는 마지막 노드에 도착하면 root로 돌아가지 않고 끝내야함
 * 그래서 총 이동거리 - root ~ lastNode (root부터 lastNode까지 깊이)를 해줘야함
 *
 * private static void findDepth(int current, int idx) {
 *      if (current == -1) return;
 *      if (current == last) depth = idx;
 *
 *      if (tree[current].left != -1) findDepth(tree[current].left, idx + 1);
 *      if (tree[current].right != -1) findDepth(tree[current] .right, idx + 1);
 *     }
 */
public class boj22856 {
    static class Node {
        int value;
        int left;
        int right;

        public Node(int value, int left, int right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    static Node[] tree;
    static int last = -1;
    static int move = 0;
    static int depth = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        tree = new Node[n+1];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            tree[v] = new Node(v, l, r);
        }

        inorder(1);
        findDepth(1, 0);

        System.out.println(move - depth);

        br.close();
    }

    private static void inorder(int current) {
        if (current == -1) return;

        if (tree[current].left != -1){
            move ++;
            inorder(tree[current].left);
            move ++;
        }
        last = current;
        if (tree[current].right != -1){
            move ++;
            inorder(tree[current].right);
            move ++;
        }
    }

    private static void findDepth(int current, int idx) {
        if (current == -1) return;
        if (current == last) depth = idx;

        if (tree[current].left != -1) findDepth(tree[current].left, idx + 1);
        if (tree[current].right != -1) findDepth(tree[current] .right, idx + 1);
    }
}
