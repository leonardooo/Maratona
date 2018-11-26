import java.util.StringTokenizer;
import java.util.Vector;

class Main {

        static char[][] map;
        static int[][] length;
//      static Queue queue;
        static Vector points;

        public static void main(String[] args) {

                int n = Integer.parseInt(Reader.readLn());
                Reader.readLn(); //black line
                for(int i = 0; i < n; i++) {

                        map = new char[100][100];
                        length = new int[100][100];
                        initialize();
                        String line = readMap();

                        int maxx = getRows();
                        int maxy = getCols();
                        floodfill(maxx,maxy);

                        if(i > 0) System.out.println();
                        while(line != null && !line.equals("")) {
                                StringTokenizer st = new StringTokenizer(line);
                                int x = Integer.parseInt(st.nextToken());
                                int y = Integer.parseInt(st.nextToken());
                                System.out.println( length[x-1][y-1] );
                                line = Reader.readLn();
                        }

                }

        }

        static void floodfill(int x, int y) {
                for(int i = 0; i < x; i++) {
                        for(int j = 0; j < y; j++) {
                                if(map[i][j] == 'W' && length[i][j] == 0) {
                                        points = new Vector();
                                        dfs(i,j,x,y);
                                        for(int k = 0; k < points.size(); k++) {
                                                Point p = (Point)points.elementAt(k);
                                                length[p.x][p.y] = points.size();
                                        }
                                }
                        }
                }
        }

        static void dfs(int x, int y, int maxx, int maxy) {

                Point p = new Point(x,y);
                points.addElement(p);
                length[p.x][p.y]++;

                if( p.x > 0 && map[p.x-1][p.y] == 'W' && length[p.x-1][p.y] == 0  ) {
                        Point temp = new Point(p.x-1,p.y);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

                if( p.y > 0 && map[p.x][p.y-1] == 'W' && length[p.x][p.y-1] == 0  ) {
                        Point temp = new Point(p.x,p.y-1);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

                if( p.x < maxx-1 && map[p.x+1][p.y] == 'W' && length[p.x+1][p.y] == 0  ) {
                        Point temp = new Point(p.x+1,p.y);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

                if( p.y < maxy-1 && map[p.x][p.y+1] == 'W' && length[p.x][p.y+1] == 0  ) {
                        Point temp = new Point(p.x,p.y+1);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

                if( p.y > 0 && p.x > 0 && map[p.x-1][p.y-1] == 'W' && length[p.x-1][p.y-1] == 0  ) {
                        Point temp = new Point(p.x-1,p.y-1);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

                if( p.y < maxy-1 && p.x < maxx-1 && map[p.x+1][p.y+1] == 'W' && length[p.x+1][p.y+1] == 0  ) {
                        Point temp = new Point(p.x+1,p.y+1);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

                if( p.y < maxy-1 && p.x > 0 && map[p.x-1][p.y+1] == 'W' && length[p.x-1][p.y+1] == 0  ) {
                        Point temp = new Point(p.x-1,p.y+1);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

                if( p.y > 0 && p.x < maxx-1 && map[p.x+1][p.y-1] == 'W' && length[p.x+1][p.y-1] == 0 ) {
                        Point temp = new Point(p.x+1,p.y-1);
                        dfs(temp.x,temp.y,maxx,maxy);
                }

        }

//      static void bfs(int x, int y, int maxx, int maxy) {
//     
//              Vector points = new Vector();
//             
//              Point start = new Point(x,y);
//      queue.enqueue(start);
//     
//      while(!queue.isEmpty()) {
//              Point p = queue.dequeue();
//             
//              if( p.x > 0 && map[p.x-1][p.y] == 'W' && length[p.x-1][p.y] == 0  ) {
//                      length[p.x-1][p.y]++;
//                      Point temp = new Point(p.x-1,p.y);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              if( p.y > 0 && map[p.x][p.y-1] == 'W' && length[p.x][p.y-1] == 0  ) {
//                      length[p.x][p.y-1]++;
//                      Point temp = new Point(p.x,p.y-1);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              if( p.x < maxx-1 && map[p.x+1][p.y] == 'W' && length[p.x+1][p.y] == 0  ) {
//                      length[p.x+1][p.y]++;
//                      Point temp = new Point(p.x+1,p.y);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              if( p.y < maxy-1 && map[p.x][p.y+1] == 'W' && length[p.x][p.y+1] == 0  ) {
//                      length[p.x][p.y+1]++;
//                      Point temp = new Point(p.x,p.y+1);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              if( p.y > 0 && p.x > 0 && map[p.x-1][p.y-1] == 'W' && length[p.x-1][p.y-1] == 0  ) {
//                      length[p.x-1][p.y-1]++;
//                      Point temp = new Point(p.x-1,p.y-1);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              if( p.y < maxy-1 && p.x < maxx-1 && map[p.x+1][p.y+1] == 'W' && length[p.x+1][p.y+1] == 0  ) {
//                      length[p.x+1][p.y+1]++;
//                      Point temp = new Point(p.x+1,p.y+1);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              if( p.y < maxy-1 && p.x > 0 && map[p.x-1][p.y+1] == 'W' && length[p.x-1][p.y+1] == 0  ) {
//                      length[p.x-1][p.y+1]++;
//                      Point temp = new Point(p.x-1,p.y+1);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              if( p.y > 0 && p.x < maxx-1 && map[p.x+1][p.y-1] == 'W' && length[p.x+1][p.y-1] == 0 ) {
//                      length[p.x+1][p.y-1]++;
//                      Point temp = new Point(p.x+1,p.y-1);
//                      points.addElement(temp);
//                      queue.enqueue(temp);
//              }
//             
//              for(int i = 0; i < points.size(); i++) {
//                      Point temp = (Point)points.elementAt(i);
//                      length[temp.x][temp.y] = points.size();
//              }
//             
//      }
//      }

        static String readMap() {
                int row = 0;
                String line = Reader.readLn();
                while( line.charAt(0) == 'W' || line.charAt(0) == 'L' ) {
                        for(int i = 0; i < line.length(); i++)
                                map[row][i] = line.charAt(i);
                        row++;
                        line = Reader.readLn();
                }
                return line;
        }

        static void initialize() {
                for(int i = 0; i < 100; i++)
                        for(int j = 0; j < 100; j++)
                                map[i][j] = '*';
        }

        static int getRows() {
                int row = 0;
                while( map[row++][0] != '*' ) {}
                return row;
        }

        static int getCols() {
                int col = 0;
                while( map[0][col++] != '*' ) {}
                return col;
        }

}

//class Queue {
//     
//      Vector array = new Vector();
//      int index = 0;
//     
//      void enqueue(Point s) {
//              array.addElement(s);
//      }
//     
//      Point dequeue() {
//              return (Point)array.elementAt(index++);
//      }
//     
//      boolean isEmpty() {
//              return array.size() <= index;
//      }
//     
//}

class Point {
        int x,y;
        Point(int x, int y) {
                this.x = x;
                this.y = y;
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