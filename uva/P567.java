import java.util.StringTokenizer;

class Main {

        static int[][] w, d;

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        int index = 1;
        while(!scanner.isEOF()) {

                w = new int[20][20];
                d = new int[20][20];
                matrix(20);

                for(int i = 0; i < 19; i++) {
                        int n_edges = scanner.getInt();
                        for(int j = 0; j < n_edges; j++) {
                                int end = scanner.getInt() - 1;
                                w[i][end] = 1;
                                w[end][i] = 1;
                        }
                }

                initialization(20);
                floydwarshall(20);

                int n_queries = scanner.getInt();
                System.out.println("Test Set #" + (index++));
                for(int i = 0; i < n_queries; i++) {
                        int sta = scanner.getInt();
                        int end = scanner.getInt();
                        System.out.println(format(sta) + " to " + format(end) + ": " + d[sta-1][end-1]);
                }
                System.out.println();

        }

    }

    static void floydwarshall(int n) {
        for(int k = 0; k < n; k++)
                for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                if( d[i][k] + d[k][j] < d[i][j] )
                                        d[i][j] = d[i][k] + d[k][j];
    }

    static void initialization(int n) {
        for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                d[i][j] = w[i][j];
        for(int i = 0; i < n; i++)
                d[i][i] = 0;
    }

    static void matrix(int n) {
        for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                w[i][j] = 1000000;
    }

    static String format(int n) {
        String res = String.valueOf(n);
        if(res.length() == 1) res = " ".concat(res);
        return res;
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

