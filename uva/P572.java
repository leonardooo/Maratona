import java.util.StringTokenizer;
import java.util.Vector;

class Main {

        static char[][] map;
        static boolean[][] visited;
        static Queue queue;

        public static void main(String[] args) {

                Scanner scanner = new Scanner();

                int x = scanner.getInt();
                int y = scanner.getInt();
                while(x > 0) {

                        map = new char[x][y];
                        visited = new boolean[x][y];
                        readMap(x,y,scanner);

                        int res = getNumberOfDeposits(x,y);
                        System.out.println(res);

                        x = scanner.getInt();
                        y = scanner.getInt();
                }

        }

        static int getNumberOfDeposits(int x, int y) {
                int count = 0;
                for(int i = 0; i < x; i++) {
                        for(int j = 0; j < y; j++) {
                                if(map[i][j] == '@' && !visited[i][j]) {
                                        count++;
                                        queue = new Queue();
                                        bfs(i,j,x,y);
                                }
                        }
                }
                return count;
        }

        static void bfs(int x, int y, int maxx, int maxy) {
        Point start = new Point(x,y);
        queue.enqueue(start);

        while(!queue.isEmpty()) {
                Point p = queue.dequeue();

                if( p.x > 0 && map[p.x-1][p.y] == '@' && !visited[p.x-1][p.y] ) {
                        visited[p.x-1][p.y] = true;
                        queue.enqueue( new Point(p.x-1,p.y) );
                }

                if( p.y > 0 && map[p.x][p.y-1] == '@' && !visited[p.x][p.y-1] ) {
                        visited[p.x][p.y-1] = true;
                        queue.enqueue( new Point(p.x,p.y-1) );
                }

                if( p.x < maxx-1 && map[p.x+1][p.y] == '@' && !visited[p.x+1][p.y] ) {
                        visited[p.x+1][p.y] = true;
                        queue.enqueue( new Point(p.x+1,p.y) );
                }

                if( p.y < maxy-1 && map[p.x][p.y+1] == '@' && !visited[p.x][p.y+1] ) {
                        visited[p.x][p.y+1] = true;
                        queue.enqueue( new Point(p.x,p.y+1) );
                }

                if( p.y > 0 && p.x > 0 && map[p.x-1][p.y-1] == '@' && !visited[p.x-1][p.y-1] ) {
                        visited[p.x-1][p.y-1] = true;
                        queue.enqueue( new Point(p.x-1,p.y-1) );
                }

                if( p.y < maxy-1 && p.x < maxx-1 && map[p.x+1][p.y+1] == '@' && !visited[p.x+1][p.y+1] ) {
                        visited[p.x+1][p.y+1] = true;
                        queue.enqueue( new Point(p.x+1,p.y+1) );
                }

                if( p.y < maxy-1 && p.x > 0 && map[p.x-1][p.y+1] == '@' && !visited[p.x-1][p.y+1] ) {
                        visited[p.x-1][p.y+1] = true;
                        queue.enqueue( new Point(p.x-1,p.y+1) );
                }

                if( p.y > 0 && p.x < maxx-1 && map[p.x+1][p.y-1] == '@' && !visited[p.x+1][p.y-1] ) {
                        visited[p.x+1][p.y-1] = true;
                        queue.enqueue( new Point(p.x+1,p.y-1) );
                }

        }
        }

        static void readMap(int x, int y, Scanner scanner) {
                for(int i = 0; i < x; i++) {
                        String s = scanner.getString(); //System.out.println(" >>> " + s + " <<< ");
                        for(int j = 0; j < y; j++) {
                                map[i][j] = s.charAt(j);
                        }
                }
        }

}

class Queue {

        Vector array = new Vector();

        void enqueue(Point s) {
                array.addElement(s);
        }

        Point dequeue() {
                Point res = (Point)array.elementAt(0);
                array.removeElementAt(0);
                return res;
        }

        boolean isEmpty() {
                return array.size() == 0;
        }

}

class Point {
        int x,y;
        Point(int x, int y) {
                this.x = x;
                this.y = y;
        }
}

class Scanner {

    StringTokenizer st = null;
    String next = null;

    Scanner() {
        next = Reader.readLn();
    }

    int getInt() {
        read();
        return Integer.parseInt(st.nextToken());
    }

    double getDouble() {
        read();
        return new Double(st.nextToken()).doubleValue();
    }

    String getString() {
        read();
        return st.nextToken();
    }

    void read() {
        if(st == null) {
                st = new StringTokenizer(next);
                next = Reader.readLn();
        }

        while(! st.hasMoreTokens()) {
                st = new StringTokenizer(next);
                next = Reader.readLn();
        }
    }

    boolean isEOF() {
        return (st == null && next == null) || (st != null && !st.hasMoreTokens() && next == null);
    }

}

class Reader {
        static String readLn() {
            String newLine = System.getProperty("line.separator");
            StringBuffer buffer = new StringBuffer();
            int car = -1;
            try {
                car = System.in.read();
                while ((car > 0) && (car != newLine.charAt(0))) {
                    buffer.append((char)car);
                    car = System.in.read();
                }
                if (car == newLine.charAt(0))
                System.in.skip(newLine.length() - 1);
            } catch (java.io.IOException e) { return (null);}
            if ((car < 0) && (buffer.length() == 0)) return (null);
            return (buffer.toString()).trim();
        }
}