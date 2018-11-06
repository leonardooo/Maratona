import java.io.*;
import java.util.*;
public class Kepler {

    static Circle[] circles;
    static int res = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        circles = new Circle[n];
        for(int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            double r = Double.parseDouble(line[2]);

            Circle c = new Circle();
            c.n = i;
            c.x = x;
            c.y = y;
            c.r = r;

            circles[i] = c;
        }

        Arrays.sort(circles);

        Set<Integer> forest = new TreeSet<Integer>();
        forest.add(0);

        for(int i = 1; i < n; i++) {
            Iterator<Integer> it = forest.iterator();
            while(it.hasNext()) {
                int j = it.next();

                dfs(circles[i], circles[j]);

                if(res > circles.length) {
                    break;
                }

                if(circles[i].contains(circles[j])) {
                    circles[i].inside.add(circles[j]);
                    it.remove();
                }
            }

            forest.add(i);

            if(res > circles.length) {
                break;
            }
        }

        if(res > circles.length) {
            System.out.println("greater");
        } else {
            System.out.println(2*res);
        }
    }

    static void dfs(Circle bigger, Circle smaller) {
        if( bigger.contains(smaller) ) {
            return;
        }

        res++;

        if(res > circles.length) {
            return;
        }

        Iterator<Circle> it = smaller.inside.iterator();
        while(it.hasNext()) {
            dfs(bigger, it.next());

            if(res > circles.length) {
                return;
            }
        }
    }
}

class Circle implements Comparable {
    int n;
    double x,y,r;

    List<Circle> inside = new LinkedList<Circle>();

    boolean contains(Circle o) {
        double d = Math.sqrt( Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2) );
        return r > (d + o.r);
    }

    public int compareTo(Object obj) {
        Circle o = (Circle) obj;
        return Double.valueOf(r).compareTo(Double.valueOf(o.r));
    }
}
