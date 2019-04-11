import java.util.*;
public class Graph {

    static boolean yes = true;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();

        Node[] nodes = new Node[v+1];
        for(int i = 1; i <= v; i++) {
            nodes[i] = new Node(i);
        }

        Edge[] edges = new Edge[e];
        for(int i = 0; i < e; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            
            nodes[v1].neig.add(nodes[v2]);
            nodes[v2].neig.add(nodes[v1]);
            
            edges[i] = new Edge(v1,v2);
        }

        sc.close();

        dfscolor(nodes[1], 1);
        
        if( !yes ) {
            System.out.println("NO");
        } else {
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < e; i++) {
                sb.append(nodes[edges[i].v1].color);
            }

            System.out.println("YES");
            System.out.println(sb.toString());
        }
    }

    static void dfscolor(Node cur, int color) {
        cur.color = color;
        color = color == 1 ? 0 : 1;

        for (Node nei : cur.neig) {
            if(nei.color == -1) {
                dfscolor(nei, color);
            } else if(nei.color != color) {
                yes = false;
            }
        }
    }
}

class Edge {
    int v1, v2;
    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}

class Node {
    int v;
    int color;
    List<Node> neig;
    public Node(int v) {
        this.v = v;
        this.color = -1;
        this.neig = new LinkedList<>();
    }
}