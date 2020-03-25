import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());
        while(cases-- > 0) {

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

            int start = Integer.parseInt(br.readLine());

            Queue q = new Queue();
            q.enqueue(allNodes[start]);
            allNodes[start].dist = 0;
        
            bfs(q);

            StringBuilder sb = new StringBuilder();
            for(int i = 1; i <= nodes; i++) {
                if(i != start) {
                    if(allNodes[i].dist == -1) sb.append(allNodes[i].dist + " ");
                    else sb.append((((long)allNodes[i].dist)*6) + " ");
                }
            }
            System.out.println(sb.toString());
        }

    }

    static void bfs(Queue q) {

        if(q.list.size() == 0) return;
        Node node = q.dequeue();

//        System.out.println(node.string());

        for(Node neig : node.neig) {

            if(neig.dist == -1) {
                neig.dist = node.dist+1;
                q.enqueue(neig);
            }
        }

        bfs(q);

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
    int index, dist;
    List<Node> neig;

    Node(int index) {
        this.index = index;
        neig = new LinkedList<>();
        dist = -1;
    }

    void addNeig(Node n) {
        neig.add(n);
    }

    String string() {
        return index+" "+" "+dist;
    }
}
