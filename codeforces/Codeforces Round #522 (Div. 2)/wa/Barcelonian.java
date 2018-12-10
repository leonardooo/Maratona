import java.util.*;
public class Barcelonian {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();

        Node n1 = new Node(x1,y1);
        Node n2 = new Node(x2,y2);

        if(x1 == x2 || y1 == y2) {
            n1.add(n2);
        }

        if(intersect(a, b, c, x1, y1) && intersect(a, b, c, x2, y2)) {
            n1.add(n2);
        }

        double x3 = x1;
        double y3 = y2;

        Node n3 = new Node(x3,y3);
        n1.add(n3);
        n3.add(n2);

        double x4 = x2;
        double y4 = y1;

        Node n4 = new Node(x4,y4);
        n1.add(n4);
        n4.add(n2);

        Set<Node> nodes = new HashSet<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);

        Node nTemp1 = null;
        Node nTemp2 = null;
        Node nTemp3 = null;
        Node nTemp4 = null;

        double xtemp = (-(y1*b)-c)/a;
        if(xtemp % 1 == 0) {
            boolean inside = false;
            if(x1 < x2) {
                inside = x1 <= xtemp && x2 >= xtemp;
            } else {
                inside = x2 <= xtemp && x1 >= xtemp;
            }

            if(inside) {
                nTemp1 = new Node(xtemp,y1);
                n1.add(nTemp1);
                nodes.add( nTemp1 );
            }
        }

        //System.out.println(xtemp);

        xtemp = (-(y2*b)-c)/a;
        if(xtemp % 1 == 0) {
            boolean inside = false;
            if(x1 < x2) {
                inside = x1 <= xtemp && x2 >= xtemp;
            } else {
                inside = x2 <= xtemp && x1 >= xtemp;
            }

            if(inside) {
                nTemp3 = new Node(xtemp,y2);
                nTemp3.add(n2);
                nodes.add( nTemp3 );
            }
        }

        //System.out.println(xtemp);

        double ytemp = (-(x1*a)-c)/b;
        if(ytemp % 1 == 0) {
            boolean inside = false;
            if(y1 < y2) {
                inside = y1 <= ytemp && y2 >= ytemp;
            } else {
                inside = y2 <= ytemp && y1 >= ytemp;
            }

            if(inside) {
                nTemp2 = new Node(x1,ytemp);
                n1.add(nTemp2);
                nodes.add( nTemp2 );
            }
        }

        //System.out.println(ytemp);

        ytemp = (-(x2*a)-c)/b;
        if(ytemp % 1 == 0) {
            boolean inside = false;
            if(y1 < y2) {
                inside = y1 <= ytemp && y2 >= ytemp;
            } else {
                inside = y2 <= ytemp && y1 >= ytemp;
            }

            if(inside) {
                nTemp4 = new Node(x2,ytemp);
                nTemp4.add(n2);
                nodes.add( nTemp4 );
            }
        }

        //System.out.println(ytemp);

        if(nTemp1 != null && nTemp3 != null) {
            nTemp1.add(nTemp3);
        } else if(nTemp2 != null && nTemp4 != null) {
            nTemp2.add(nTemp4);
        } else if(nTemp1 != null && nTemp4 != null) {
            nTemp1.add(nTemp4);
        } else if(nTemp2 != null && nTemp3 != null) {
            nTemp2.add(nTemp3);
        }

        dfs(n1,null);

        System.out.println("size=" + nodes.size());
        System.out.println(nodes);

        System.out.println();
        System.out.println(round(n2.dist));
    }

    static void dfs(Node cur, Node anc) {
        if(anc == null) {
            cur.dist = 0.0;
        } else {
            cur.dist = Math.min(cur.dist, distance(cur.x, cur.y, anc.x, anc.y)+anc.dist);
        }

        for (Node nei : cur.next) {
            dfs(nei, cur);
        }
    }

    static boolean intersect(double a, double b, double c, double x, double y) {
        return a*x+b*y+c == 0.0;
    }

    static double distance(double x1, double y1, double x2, double y2) {
        double res = Math.pow(Math.abs(x1-x2),2) + Math.pow(Math.abs(y1-y2),2);
        res = Math.sqrt(res);
        return res;
    }

    static double round(double n) {
        n *= Math.pow(10,10);
        n = Math.round(n);
        n /= Math.pow(10,10);
        return n;
    }
}

class Node {
    double x,y;
    double dist = Double.MAX_VALUE;
    Set<Node> next = new HashSet<>();
    
    Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void add(Node n) {
        if( !(x == n.x && y == n.y) ) {
            next.add(n);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("x="+x+" y="+y+"\n next=");
        for (Node n : next) {
            sb.append(" x="+n.x+",y="+n.y+" - ");
        }
        return "\n" + sb.toString();
    }

    public boolean equals(Object o) {
        Node n = (Node)o;
        return x == n.x && y == n.y;
    }

    public int hashCode() {
        return Objects.hash(x,y);
    }
    
}