import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

class Main {

        static final int MAX = 32650;
        static boolean[] primes = new boolean[MAX];
        static int[] primes_iterator = new int[MAX];

    public static void main(String[] args) {

        sieve();

        int num = Integer.parseInt(readLn());
        for(int i = 0; i < num; i++) {

                StringTokenizer st = new StringTokenizer(readLn());
                long beg = Long.parseLong(st.nextToken());
                long end = Long.parseLong(st.nextToken());

                int max_divs = -1;
                long max_num = -1;
                for(long k = beg; k <= end; k++) {

                        Hashtable primes_factors = new Hashtable();
                        int index = 0;
                        int count = 0;
                        long temp = k;
                        while(temp > 1) {
                                if(primes_iterator[index] == 0) break;
                                if( temp % primes_iterator[index] == 0 ) {
                                        count++;
                                        temp /= primes_iterator[index];
                                        primes_factors.put( new Integer(primes_iterator[index]), new Integer(count) );
                                } else {
                                        count = 0;
                                        index++;
                                }
                        }

                        int temp_divs = 1;
                        Enumeration enu = primes_factors.elements();
                        while(enu.hasMoreElements()) {
                                Integer number = (Integer)enu.nextElement();
                                temp_divs *= (number.intValue() + 1);
                        }

                        if(temp_divs > max_divs) {
                                max_divs = temp_divs;
                                max_num = k;
                        }

                        //System.out.println(k + " tem " + temp_divs + " divisores");
                }

                System.out.println("Between " + beg + " and " + end + ", " + max_num + " has a maximum of " + max_divs + " divisors.");

        }

    }

    static void sieve() {
           int i, j;
           primes[2] = true;
           primes[3] = true;
           for(i = 5; i < MAX; i+=2) {
               for(j = 3; j*j <=i ; j+=2)
                   if(i % j == 0) break;
               if(i % j != 0 ) primes[i] = true;
           }
           j = 0;
           for(i = 0; i < MAX; i++)
                   if(primes[i]) primes_iterator[j++] = i;
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

