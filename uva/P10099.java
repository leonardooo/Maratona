import java.util.StringTokenizer;

class Main {

        static int[][] d, w;

    public static void main(String[] args) {

        int count = 1;

        String line = readLn();
        while(!line.equals("0 0")) {

                StringTokenizer st = new StringTokenizer(line);
                int nodes = Integer.parseInt(st.nextToken());
                int edges = Integer.parseInt(st.nextToken());

                d = new int[nodes][nodes];
                w = new int[nodes][nodes];
                matrix(nodes);
                readEdges(edges);

                initialization(nodes);
                floydwarshall(nodes);

                st = new StringTokenizer(readLn());

                System.out.println("Scenario #" + (count++));
                int sta = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int tou = Integer.parseInt(st.nextToken());
                int res = tou / d[sta-1][end-1];
                if( tou % d[sta-1][end-1] > 0 )
                        res++;
                System.out.println("Minimum Number of Trips = " + res + "\n");

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
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                w[n1-1][n2-1] = s - 1;
                w[n2-1][n1-1] = s - 1;
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