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

        Map<Integer,Node> nodes = new HashMap<>();
        nodes.put(n1.hashCode(), n1);
        nodes.put(n2.hashCode(), n2);
        nodes.put(n3.hashCode(), n3);
        nodes.put(n4.hashCode(), n4);
        
        Node nTemp1 = null;
        Node nTemp2 = null;
        Node nTemp3 = null;
        Node nTemp4 = null;

        double xtemp = (-(y1*b)-c)/a;
        //if(xtemp % 1 == 0) {
            boolean inside = false;
            if(x1 < x2) {
                inside = x1 <= xtemp && x2 >= xtemp;
            } else {
                inside = x2 <= xtemp && x1 >= xtemp;
            }

            if(inside) {
                nTemp1 = new Node(xtemp,y1);
                if(nodes.get(nTemp1.hashCode()) != null) {
                    nTemp1 = nodes.get(nTemp1.hashCode());
                } else {
                    nodes.put(nTemp1.hashCode(), nTemp1 );
                }
                n1.add(nTemp1);
                
            }
        //}

        //System.out.println(xtemp);

        xtemp = (-(y2*b)-c)/a;
        //if(xtemp % 1 == 0) {
            inside = false;
            if(x1 < x2) {
                inside = x1 <= xtemp && x2 >= xtemp;
            } else {
                inside = x2 <= xtemp && x1 >= xtemp;
            }

            if(inside) {
                nTemp3 = new Node(xtemp,y2);
                if(nodes.get(nTemp3.hashCode()) != null) {
                    nTemp3 = nodes.get(nTemp3.hashCode());
                } else {
                    nodes.put(nTemp3.hashCode(), nTemp3 );
                }
                nTemp3.add(n2);
            }
        //}

        //System.out.println(xtemp);

        double ytemp = (-(x1*a)-c)/b;
        //if(ytemp % 1 == 0) {
            inside = false;
            if(y1 < y2) {
                inside = y1 <= ytemp && y2 >= ytemp;
            } else {
                inside = y2 <= ytemp && y1 >= ytemp;
            }

            if(inside) {
                nTemp2 = new Node(x1,ytemp);
                if(nodes.get(nTemp2.hashCode()) != null) {
                    nTemp2 = nodes.get(nTemp2.hashCode());
                } else {
                    nodes.put(nTemp2.hashCode(), nTemp2 );
                }
                n1.add(nTemp2);
            }
        //}

        //System.out.println(ytemp);

        ytemp = (-(x2*a)-c)/b;
        //if(ytemp % 1 == 0) {
            inside = false;
            if(y1 < y2) {
                inside = y1 <= ytemp && y2 >= ytemp;
            } else {
                inside = y2 <= ytemp && y1 >= ytemp;
            }

            if(inside) {
                nTemp4 = new Node(x2,ytemp);
                if(nodes.get(nTemp4.hashCode()) != null) {
                    nTemp4 = nodes.get(nTemp4.hashCode());
                } else {
                    nodes.put(nTemp4.hashCode(), nTemp4 );
                }
                nTemp4.add(n2);
            }
        //}

        // System.out.println(ytemp);

        // System.out.println(nTemp1);
        // System.out.println(nTemp2);
        // System.out.println(nTemp3);
        // System.out.println(nTemp4);

        if(nTemp1 != null && nTemp3 != null) {
            //System.out.println("1 3");
            nTemp1.add(nTemp3);
        } else if(nTemp2 != null && nTemp4 != null) {
            //System.out.println("2 4");
            nTemp2.add(nTemp4);
        } else if(nTemp1 != null && nTemp4 != null) {
            //System.out.println("1 4");
            nTemp1.add(nTemp4);
        } else if(nTemp2 != null && nTemp3 != null) {
            //System.out.println("2 3");
            nTemp2.add(nTemp3);
        }

        dfs(n1,null);

        // System.out.println("size=" + nodes.size());
        // System.out.println(nodes);

        // System.out.println();
        System.out.println(n2.dist);
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