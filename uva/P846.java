import java.util.StringTokenizer;


class Main {

        public static void main(String[] args) {

                Scanner sc = new Scanner();
                int n = sc.getInt();

                for(int i = 0; i < n; i++) {

                        int x = sc.getInt();
                        int y = sc.getInt();

                        int dif = y-x;

                        if(dif == 0) {
                                System.out.println("0");
                                continue;
                        }

                        double k = (int)Math.sqrt(dif);
                        double ceil = Math.ceil( ((double)dif - (k*k)) / k );
                        int res = (int)k + (int)k - 1 + (int)ceil;

                        System.out.println( res );

                }

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

   long getLong() {
       read();
       return Long.parseLong(st.nextToken());
   }

   double getDouble() {
       read();
       return new Double(st.nextToken()).doubleValue();
   }

   String getString() {
       read();
       return st.nextToken();
   }

   boolean isEOF() {
       return (st == null && next == null) || (st != null && !st.hasMoreTokens() && next == null);
   }

   private void read() {
       if(st == null) {
           st = new StringTokenizer(next);
           next = Reader.readLn();
       }
       while(! st.hasMoreTokens()) {
           st = new StringTokenizer(next);
           next = Reader.readLn();
       }
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
       } catch (java.io.IOException e) { return (null); }
       if ((car < 0) && (buffer.length() == 0)) return (null);
       return (buffer.toString()).trim();
   }
}