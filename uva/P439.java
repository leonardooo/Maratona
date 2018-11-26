import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

class Main {

        static char[] rows = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

        static String sta, end;
        static Hashtable visiteds;
        static Queue queue;

    public static void main(String[] args) {

        String line = readLn();
        while(line != null) {

                StringTokenizer st = new StringTokenizer(line);
                sta = st.nextToken();
                end = st.nextToken();

                visiteds = new Hashtable();
                queue = new Queue();

                int res = bfs(sta,end);
                System.out.println("To get from " + sta + " to " + end + " takes " + res + " knight moves.");

                line = readLn();
        }

    }

    static int bfs(String start, String end) {

        if(start.equals(end))
                return 0;

        Integer dist = new Integer(0);
        visiteds.put(start,dist);
        queue.enqueue(start);

        while(!queue.isEmpty()) {
                String s = queue.dequeue();
                Integer dist_s = (Integer)visiteds.get(s);
                Vector paths = get_moves(s);
                for(int i = 0; i < paths.size(); i++) {
                        String path = (String)paths.elementAt(i); //System.out.println(">>> " + path);

                        if(path.equals(end))
                                return dist_s.intValue() + 1;

                        if( visiteds.get(path) == null ) {
                                visiteds.put( path, new Integer( dist_s.intValue() + 1 ) );
                                queue.enqueue(path);
                        }
                }
        }

        return -1;

    }

    static Vector get_moves(String s) {
        Vector vector = new Vector();
        char row = s.charAt(0);
        int row_index = get_index(row);
        int row_minus_one = row_index - 1;
        int row_minus_two = row_index - 2;
        int row_plus_one = row_index + 1;
        int row_plus_two = row_index + 2;
        int col = Integer.parseInt( String.valueOf(s.charAt(1)) );

        if( row_minus_two >= 0 && col - 1 > 0 ) {
                String row_temp = String.valueOf(rows[row_minus_two]);
                String col_temp = String.valueOf(col-1);
                vector.addElement( row_temp.concat(col_temp) );
        }

        if( row_minus_one >= 0 && col - 2 > 0 ) {
                String row_temp = String.valueOf(rows[row_minus_one]);
                String col_temp = String.valueOf(col-2);
                vector.addElement( row_temp.concat(col_temp) );
        }

        if( row_minus_one >= 0 && col + 2 <= 8 ) {
                String row_temp = String.valueOf(rows[row_minus_one]);
                String col_temp = String.valueOf(col+2);
                vector.addElement( row_temp.concat(col_temp) );
        }

        if( row_minus_two >= 0 && col + 1 <= 8 ) {
                String row_temp = String.valueOf(rows[row_minus_two]);
                String col_temp = String.valueOf(col+1);
                vector.addElement( row_temp.concat(col_temp) );
        }

        if( row_plus_two < 8 && col - 1 > 0 ) {
                String row_temp = String.valueOf(rows[row_plus_two]);
                String col_temp = String.valueOf(col-1);
                vector.addElement( row_temp.concat(col_temp) );
        }

        if( row_plus_one < 8 && col - 2 > 0 ) {
                String row_temp = String.valueOf(rows[row_plus_one]);
                String col_temp = String.valueOf(col-2);
                vector.addElement( row_temp.concat(col_temp) );
        }

        if( row_plus_one < 8 && col + 2 <= 8 ) {
                String row_temp = String.valueOf(rows[row_plus_one]);
                String col_temp = String.valueOf(col+2);
                vector.addElement( row_temp.concat(col_temp) );
        }

        if( row_plus_two < 8 && col + 1 <= 8 ) {
                String row_temp = String.valueOf(rows[row_plus_two]);
                String col_temp = String.valueOf(col+1);
                vector.addElement( row_temp.concat(col_temp) );
        }

        return vector;
    }

    static int get_index(char c) {
        for(int i = 0; i < rows.length; i++)
                if(rows[i] == c)
                        return i;
        return -1;
    }

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

class Queue {

        Vector array = new Vector();

        void enqueue(String s) {
                array.addElement(s);
        }

        String dequeue() {
                String res = (String)array.elementAt(0);
                array.removeElementAt(0);
                return res;
        }

        boolean isEmpty() {
                return array.size() == 0;
        }

}