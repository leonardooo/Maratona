import java.util.Hashtable;
import java.util.StringTokenizer;

class Main {

        static int[][] d, w;
        static Hashtable cods;

    public static void main(String[] args) {

        int count = 1;

        String line = readLn();
        while(!line.equals("0 0")) {

                StringTokenizer st = new StringTokenizer(line);
                int nodes = Integer.parseInt(st.nextToken());
                int edges = Integer.parseInt(st.nextToken());

                cods = new Hashtable();
                d = new int[nodes][nodes];
                w = new int[nodes][nodes];
                matrix(nodes);
                readEdges(edges);

                initialization(nodes);
                floydwarshall(nodes);

                st = new StringTokenizer(readLn());
                String sta = st.nextToken(); sta = sta.toLowerCase();
                String end = st.nextToken(); end = end.toLowerCase();

                int n1 = ((Integer)cods.get(sta)).intValue();
                int n2 = ((Integer)cods.get(end)).intValue();

                System.out.println("Scenario #" + (count++));
                System.out.println( d[n1][n2] + " tons\n" );

                line = readLn();
        }

    }

    static void floydwarshall(int n) {
        for(int k = 0; k < n; k++)
                for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                d[i][j] = Math.max( d[i][j], Math.min(d[i][k], d[k][j]) );
    }

    static void initialization(int n) {
        for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                d[i][j] = w[i][j];
        for(int i = 0; i < n; i++)
                d[i][i] = 0;
    }

    static void readEdges(int n) {
        for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(readLn());
                String s1 = st.nextToken(); s1 = s1.toLowerCase();
                String s2 = st.nextToken(); s2 = s2.toLowerCase();
                int s = Integer.parseInt(st.nextToken());

                Integer int1 = (Integer)cods.get(s1);
                if(int1 == null) {
                        int1 = new Integer( cods.size() );
                        cods.put(s1, int1);
                }

                Integer int2 = (Integer)cods.get(s2);
                if(int2 == null) {
                        int2 = new Integer( cods.size() );
                        cods.put(s2, int2);
                }

                int n1 = int1.intValue();
                int n2 = int2.intValue();

                w[n1][n2] = s;
                w[n2][n1] = s;
        }
    }

    static void matrix(int n) {
        for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                w[i][j] = Integer.MIN_VALUE;
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

