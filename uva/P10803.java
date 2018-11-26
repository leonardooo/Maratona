import java.util.StringTokenizer;

class Main {

        static double[][] w, d;
        static Point[] points;

    public static void main(String[] args) {
        Scanner scanner = new Scanner();

        int n = scanner.getInt();
        for(int i = 1; i <= n; i++) {

                int nodes = scanner.getInt();
                points = new Point[nodes];
                w = new double[nodes][nodes];
                d = new double[nodes][nodes];

                matrix(nodes);
                readPoints(nodes, scanner);
                readEdges(nodes);

                initialization(nodes);
                floydwarshall(nodes);

                double res = getmax(nodes);
                System.out.println("Case #" + i + ":");
                if(res >= 1000000) System.out.println("Send Kurdy\n");
                else System.out.println( round(res,4) + "\n");

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

    static void readEdges(int n) {
        for(int i = 0; i < n-1; i++) {
                for(int j = i+1; j < n; j++) {
                        double dist = points[i].distance(points[j]);
                        if(Math.pow(dist,2) <= 100) {
                                w[i][j] = dist;
                                w[j][i] = dist;
                        }
                }
        }
    }

    static void matrix(int n) {
        for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                w[i][j] = 1000000;
    }

    static void readPoints(int n, Scanner scanner) {
        for(int i = 0; i < n; i++) {
                points[i] = new Point(scanner.getDouble(), scanner.getDouble());
        }
    }

    static double getmax(int n) {
        double res = Double.MIN_VALUE;
        for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                if( d[i][j] > res )
                                        res = d[i][j];
        return res;
    }

    static String round(double valor, int casas) {
        long numero = Math.round(valor * Math.pow(10, casas));

        String retorno;
        if (numero < 0) retorno = "-";
        else retorno = "";
        retorno += (Math.abs(numero) / (long) Math.pow(10, casas));
        retorno += ".";
        String resto = "" + (Math.abs(numero) % (long) Math.pow(10, casas));
        resto = str('0', casas - resto.length()) + resto;
        retorno += resto;

        return retorno;
    }

    static String str(char ch, int n) {
        String resultado = "";
        for (int i = 0; i < n; i++) {
            resultado += ch;
        }
        return resultado;
    }

}

class Point {
        double x,y;
        Point(double x, double y) {
                this.x = x;
                this.y = y;
        }

        double distance(Point p) {
                return Math.sqrt( Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2) );
        }
}

class Scanner {

    StringTokenizer st = null;

    int getInt() {
            if(st == null) st = new StringTokenizer(Reader.readLn());
            while(! st.hasMoreTokens()) st = new StringTokenizer(Reader.readLn());
            return Integer.parseInt(st.nextToken());
    }

    double getDouble() {
        if(st == null) st = new StringTokenizer(Reader.readLn());
        while(! st.hasMoreTokens()) st = new StringTokenizer(Reader.readLn());
        return new Double(st.nextToken()).doubleValue();
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

