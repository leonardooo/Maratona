class Main {

    public static void main(String[] args) {

            while(true) {
                    String line = readLn();
                    if(line.equals("0")) break;

                    int par = 0;
                    int impar = 0;
                    for(int i = 0; i < line.length(); i++) {
                            int n = Integer.parseInt( String.valueOf(line.charAt(i)) );
                            if((i+1) % 2 == 0) par += n;
                            else impar += n;
                    }

                    int result = Math.abs(par - impar);
                    if(result % 11 == 0) System.out.println(line + " is a multiple of 11.");
                    else System.out.println(line + " is not a multiple of 11.");

            }

    }

    static String readLn() {
            String newLine = System.getProperty("line.separator");
            StringBuffer buffer = new StringBuffer();
            int car = -1;
            try {
                    car = System.in.read();
                    while ((car > 0) && (car != newLine.charAt(0))) {
                            buffer.append((char)car); car = System.in.read();
                    }

                    if (car == newLine.charAt(0))
                            System.in.skip(newLine.length() - 1); }
            catch (java.io.IOException e) { return (null); }

            if ((car < 0) && (buffer.length() == 0))
                    return (null);

            return (buffer.toString()).trim();
    }

}

