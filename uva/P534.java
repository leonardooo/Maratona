import java.util.Hashtable;
import java.util.StringTokenizer;

class Main {

        static double[][] d, w;
        static Point[] points;

    public static void main(String[] args) {

        int count = 1;

        String line = readLn();
        while(!line.equals("0")) {

                int nodes = Integer.parseInt(line);

                d = new double[nodes][nodes];
                w = new double[nodes][nodes];
                points = new Point[nodes];
                matrix(nodes);
                readPoints(nodes);
                readEdges(nodes);

                initialization(nodes);
                floydwarshall(nodes);

                System.out.println("Scenario #" + (count++));
                System.out.println("Frog Distance = " + round(d[0][1],3) + "\n" );

                readLn();
                line = readLn();
        }

    }

    static void floydwarshall(int n) {
        for(int k = 0; k < n; k++)
                for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                d[i][j] = Math.min( d[i][j], Math.max(d[i][k], d[k][j]) );
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
                        w[i][j] = dist;
                        w[j][i] = dist;
                }
        }
    }

    static void matrix(int n) {
        for(int i = 0; i < n; i++)
                        for(int j = 0; j < n; j++)
                                w[i][j] = Double.MAX_VALUE;
    }

    static void readPoints(int n) {
        for(int i = 0; i < n; i++) {
                points[i] = new Point(readLn());
        }
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

class Point {
        double x,y;
        Point(String s) {
                StringTokenizer st = new StringTokenizer(s);
                x = new Double(st.nextToken()).doubleValue();
                y = new Double(st.nextToken()).doubleValue();
        }

        double distance(Point p) {
                return Math.sqrt( Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2) );
        }
}