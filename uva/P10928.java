import java.util.StringTokenizer;
import java.util.Vector;


class Main {


        public static void main(String[] args) {

                int cases = Integer.parseInt(readLn()); //System.out.println("numero de casos = " + cases);
                for(int i = 0; i < cases; i++) {

                        int nNeightbors = Integer.MAX_VALUE;
                        Vector bestPlaces = new Vector();

                        int places = Integer.parseInt(readLn()); //System.out.println("numero de lugares = " + places);
                        for(int k = 1; k <= places; k++) {
                                String linha = readLn();
                                StringTokenizer st = new StringTokenizer(linha); //System.out.println("vizinhos = " + linha);
                                int nTemp = st.countTokens(); //System.out.println("numero de vizinhos = " + nTemp);
                                if(nTemp < nNeightbors) { //System.out.println("menor = " + k);
                                        bestPlaces = new Vector();
                                        bestPlaces.addElement(new Integer(k));
                                        nNeightbors = nTemp;
                                } else if(      nTemp == nNeightbors) {
                                        bestPlaces.addElement(new Integer(k)); //System.out.println("igual = " + k);
                                }

                        }

                        StringBuffer sb = new StringBuffer();
                        for(int k = 0 ; k < bestPlaces.size(); k++) {
                                if(k > 0) sb.append(" ");
                                sb.append( bestPlaces.elementAt(k) );
                        }

                        System.out.println(sb.toString());
                        readLn();
                }

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