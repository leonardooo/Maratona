import java.io.*;
import java.util.*;

public class Solution {

    static int count = 0;

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {

        if(c_lib <= c_road) {
            return ((long)c_lib) * ((long)n);
        }

        Node[] nodes = new Node[n+1];
        for(int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for(int i = 0; i < cities.length; i++) {
            nodes[ cities[i][0] ].addNeig( nodes[ cities[i][1] ] );
            nodes[ cities[i][1] ].addNeig( nodes[ cities[i][0] ] );
        }

        long res = 0L;
        for(int i = 1; i <= n; i++) {
            if(! nodes[i].visited ) {
                count = 0;
                numberOfNodes(nodes[i]);

                res = res + ((count-1) * c_road) + c_lib;
            }
        }

        return res;
    }

    static void numberOfNodes(Node node) {
        node.visited = true;
        count++;

        for(Node adj : node.neig) {

            if( !adj.visited ) {
                numberOfNodes(adj);
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            System.out.println(String.valueOf(result));
        }

        scanner.close();
    }
}


class Node {
    int city;
    boolean visited;
    List<Node> neig;

    Node(int city) {
        this.city = city;
        this.visited = false;
        neig = new LinkedList<>();
    }

    void addNeig(Node n) {
        neig.add(n);
    }
}