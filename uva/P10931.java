class Main {

    public static void main(String[] args) {
            while(true) {
                    String line = readLn();
                    if(line.equals("0")) break;
                    int num = Integer.parseInt(line);
                    String result = Integer.toBinaryString(num);
                    int count = 0;
                    for(int i = 0; i < result.length(); i++) {
                            if(result.charAt(i) == '1')
                                    count++;
                    }
                    System.out.println("The parity of " + result + " is " + count + " (mod 2).");
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

