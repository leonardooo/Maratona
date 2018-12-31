import java.util.*;
public class Prefixes {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Word[] words = new Word[2*n-2];
        Word[] sorted = new Word[words.length];
        for(int i = 0; i < words.length; i++) {
            words[i] = new Word(sc.next());
            sorted[i] = words[i];
        }

        Arrays.sort(sorted);
        // System.out.println(Arrays.toString(words));
        // System.out.println(Arrays.toString(sorted));

        boolean solved = true;

        sorted[0].x = 'P';
        sorted[1].x = 'S';
        solved = solve(sorted, sorted[0].xfix, sorted[1].xfix);

        if(!solved) {
            sorted[0].x = 'S';
            sorted[1].x = 'P';
            solved = solve(sorted, sorted[1].xfix, sorted[0].xfix);
        }

        if(solved) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < words.length; i++) {
                sb.append(words[i].x);
            }

            System.out.println(sb.toString());

        } else {
            System.exit(1);
        }
    }

    static boolean solve(Word[] sorted, String prefix, String sufix) {
        for(int i = 2; i < sorted.length; i+=2) {
            if( prefix.startsWith( sorted[i].xfix ) && sufix.endsWith( sorted[i+1].xfix ) ) {
                sorted[i].x = 'P';
                sorted[i+1].x = 'S';
            } else if( sufix.endsWith( sorted[i].xfix ) && prefix.startsWith( sorted[i+1].xfix ) ) {
                sorted[i].x = 'S';
                sorted[i+1].x = 'P';
            } else {
                return false;
            }
        }
        return true;
    }
}

class Word implements Comparable {
    String xfix;
    char x;

    Word(String s) {
        xfix = s;
    }

    public int compareTo(Object o) {
        Word d = (Word)o;
        return d.xfix.length() - xfix.length();
    }

    public String toString() {
        return xfix;
    }
}