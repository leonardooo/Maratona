import java.util.*;
public class Diverse {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++) {
            boolean ans = true;
            boolean[] dyn = reset();
            String word = sc.nextLine();

            int min = ((int)'z')+1;
            int max = ((int)'a')-1;

            for(int j = 0; j < word.length(); j++) {
                if( dyn[ word.charAt(j) ] ) {
                    ans = false;
                    break;
                } else {
                    dyn[ word.charAt(j) ] = true;
                    if( ((int)word.charAt(j)) < min ) min = ((int)word.charAt(j));
                    if( ((int)word.charAt(j)) > max ) max = ((int)word.charAt(j));
                }
            }

            // System.out.println(word);
            // System.out.println("min=" + min);
            // System.out.println("max=" + max);

            if(!ans) {
                System.out.println("No");
            } else if( check(dyn, min, max) ) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean check(boolean[] dyn, int min, int max) {
        for(int i = min; i <= max; i++) {
            if(!dyn[i]) return false;
        }
        return true;
    }

    private static boolean[] reset() {
        boolean[] ret = new boolean[((int)'z')+1];
        return ret;
    }
}