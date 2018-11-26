import java.util.StringTokenizer;
import java.util.Vector;

class Main {

    public static void main(String[] args) {

        Vector words = new Vector();
        Vector sorted = new Vector();

        String line = readLn();
        while(!line.equals("#")) {

                StringTokenizer st = new StringTokenizer(line);
                while(st.hasMoreTokens()) {
                        String s = st.nextToken();
                        String sor = sort(s);
                        words.addElement(s);
                        sorted.addElement(sor);
                }

                line = readLn();
        }

        for(int i = 0; i < sorted.size()-1; i++) {
                boolean anagram = false;
                String s1 = (String)sorted.elementAt(i);
                for(int j = i+1; j < sorted.size(); j++) {
                        String s2 = (String)sorted.elementAt(j);
                        if(s1 != null && s2 != null && s1.equals(s2)) {
                                anagram = true;
                                sorted.setElementAt(null,j); //System.out.println( words.elementAt(i) + " == " + words.elementAt(j) );
                        }
                }
                if(anagram)     sorted.setElementAt(null,i);
        }

        Vector ananagrams = new Vector();
        for(int i = 0; i < sorted.size(); i++) {
                String s1 = (String)sorted.elementAt(i);
                if(s1 != null) {
                        ananagrams.addElement( words.elementAt(i) );
                         //System.out.println(">>> " + words.elementAt(i));
                }
        }

        for(int i = 0; i < ananagrams.size()-1; i++) {
                for(int j = i+1; j < ananagrams.size(); j++) {
                        String s1 = (String)ananagrams.elementAt(i);
                        String s2 = (String)ananagrams.elementAt(j);
                        if(s1.compareTo(s2) > 0) {
                                ananagrams.setElementAt(s1,j);
                                ananagrams.setElementAt(s2,i);
                        }
                }
        }

        for(int i = 0; i < ananagrams.size(); i++) {
                String s = (String)ananagrams.elementAt(i);
                System.out.println(s);
        }

    }

    static String sort(String s) {
        String temp = s.toLowerCase();
        char[] array = temp.toCharArray();
        for(int i = 0; i < array.length-1; i++) {
                for(int j = i+1; j < array.length; j++) {
                        if(array[i] > array[j]) {
                                char c = array[i];
                                array[i] = array[j];
                                array[j] = c;
                        }
                }
        }
        temp = new String(array);
        return temp;
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

