import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int nodes = Integer.parseInt(line[0]);
        int edges = Integer.parseInt(line[1]);

        Node[] allNodes = new Node[nodes+1];
        for(int i = 1 ; i <= nodes; i++) {
            allNodes[i] = new Node(i);
        }

        for(int i = 0 ; i < edges; i++) {
            line = br.readLine().split(" ");
            int n1 = Integer.parseInt(line[0]);
            int n2 = Integer.parseInt(line[1]);
            allNodes[ n1 ].addNeig( allNodes[ n2 ] );
            allNodes[ n2 ].addNeig( allNodes[ n1 ] );
        }

        line = br.readLine().split(" ");
        int target = Integer.parseInt(br.readLine());

        Queue q = new Queue();
        List<Node> targetNodes = new LinkedList<>();
        for(int i = 1 ; i <= nodes; i++) {
            allNodes[i].color = Integer.parseInt(line[i-1]);

            if(allNodes[i].color == target) {
                q.enqueue(allNodes[i]);
                targetNodes.add(allNodes[i]);
            }
        }

        if(q.list.size() <= 1) {
            System.out.println(-1);
            return;
        }

        bfs(q, target, 0);

        int min = Integer.MAX_VALUE;
        for(Node targetNode : targetNodes) {
            if(targetNode.dist < min) {
                min = targetNode.dist;

                //System.out.println(allNodes[i].string());
            }
        }

        if(min == Integer.MAX_VALUE)
            min = -1;

        System.out.println(min);


    }

    static void bfs(Queue q, int target, int dist) {

        if(q.list.size() == 0) return;
        Node node = q.dequeue();

        for(Node neig : node.neig) {

            if(neig.dist == -1 || (neig.dist > dist+1 && neig.from != node.index)) {
                neig.dist = dist+1;
                neig.from = node.index;
                q.enqueue(neig);
            }
        }

        bfs(q, target, dist+1);

    }
}

class Queue {
    List<Node> list = new LinkedList<>();

    void enqueue(Node n) {
        list.add(n);
    }

    Node dequeue() {
        return list.remove(0);
    }

    Node top() {
        return list.get(0);
    }
}

class Node {
    int index, color, dist, from;
    List<Node> neig;

    Node(int index) {
        this.index = index;
        neig = new LinkedList<>();
        dist = Integer.MAX_VALUE;
        from = -1;
        color = -1;
    }

    void addNeig(Node n) {
        neig.add(n);
    }

    String string() {
        return index+" "+color+" "+dist+" "+from;
    }
}
